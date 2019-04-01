package uk.gov.ons.collection.ValidationPersistenceLayer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ReturnedValidationOutputs;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntityShort;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationOutputRepo;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationOutputRepoShort;
import org.json.JSONArray;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value = "Validation Entity", description = "offers CRUD operations for the Validation entity")
@RestController
@RequestMapping(value = "/validation-pl", method = {RequestMethod.GET, RequestMethod.PUT})
public class ValidationOutputController {

    @Autowired
    private ValidationOutputRepo validationOutputRepo;

    @Autowired
    private ValidationOutputRepoShort validationOutputRepoShort;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @ApiOperation(value = "Returns all Validation Outputs", notes = "Returns all columns for all triggered Validations")
    @GetMapping(value = "/validations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Validations", response = ValidationOutputEntity.class),
            @ApiResponse(code = 404, message = "No Validation exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public Iterable<ValidationOutputEntity> getAll() {
        return this.validationOutputRepo.findAll();
    }

    @ApiOperation(value = "Returns Validation Outputs for a form",
                  notes = "Returns all columns for all Validation errors on a form - using reference, period, survey parameters to identify a form")
    @GetMapping(value = "/validations/return", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Validations for a form", response = ValidationOutputEntity.class),
            @ApiResponse(code = 404, message = "No Validation errors exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public Iterable<ValidationOutputEntity> getForForm(
            @ApiParam(value = "11-digit RU reference number", name = "reference", required = true, example = "49900000000") @RequestParam("reference") String reference,
            @ApiParam(value = "Surveyed period - YYYYMM", name = "period", required = true, example = "201709") @RequestParam("period") String period,
            @ApiParam(value = "3-digit Unique survey code", name = "survey", required = true, example = "066") @RequestParam("survey") String survey) {
        return validationOutputRepo.findByReferenceAndPeriodAndSurvey(reference, period, survey);
    }

    //Example test record to save (Can run in Postman)
//    {
//          "reference": "49900000008",
//          "period": "201712",
//          "survey": "066",
//          "validationID": 1,
//          "instance": 0,
//          "primaryValue": "146",
//          "formula": "146!=",
//          "createdBy": "Ryan",
//          "createdDate": "1551368332019",
//          "lastUpdatedBy":"Sufyan",
//          "lastUpdatedDate":"1551368332019"
//    }
    @ApiOperation(value = "Saves all Validation Outputs", notes = "Saves all columns for all triggered Validations")
    @PutMapping(value="/validations/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Saved Validations", response = ValidationOutputEntityShort.class),
            @ApiResponse(code = 404, message = "No Validation exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    @Transactional
    public void saveValidations(@RequestBody String body) {
        ValidationOutputEntityShort outputsToUpdate = new ValidationOutputEntityShort();

        List<ReturnedValidationOutputs> returnedValidation = new ArrayList<>();
        try {
            returnedValidation = new ObjectMapper().readValue(body, new TypeReference<List<ReturnedValidationOutputs>>(){});
        }
        catch(IOException error){
            System.out.println(error);
        }

        System.out.println(returnedValidation.toString());

        Map<String, String> contributorKey = new HashMap<String, String>();

        Map referenceElement = returnedValidation.get(0).getMetaData();

        contributorKey.put("reference", referenceElement.get("reference").toString());
        contributorKey.put("period", referenceElement.get("period").toString());
        contributorKey.put("survey", referenceElement.get("survey").toString());

                // Create an EntityManger
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Begin a transaction
        entityManager.getTransaction().begin();
        try {
            deleteValidationsByMatrix(contributorKey);
            for (ReturnedValidationOutputs element : returnedValidation) {
                if (element.getTriggered().equals("true")) {
                    outputsToUpdate.setCreatedBy("fisdba");
                    outputsToUpdate.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    outputsToUpdate.setInstance(Integer.parseInt(element.getMetaData().get("instance")));
                    outputsToUpdate.setReference(element.getMetaData().get("reference"));
                    outputsToUpdate.setPeriod(element.getMetaData().get("period"));
                    outputsToUpdate.setSurvey(element.getMetaData().get("survey"));
                    outputsToUpdate.setFormula(element.getValueFormula());
                    outputsToUpdate.setValidationID(Integer.parseInt(element.getMetaData().get("validationId")));
                    outputsToUpdate.setPrimaryValue(element.getMetaData().get("primaryValue"));
                    entityManager.merge(outputsToUpdate);
                }
            }
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
        }
        finally {
            if(entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().commit();
            }
            entityManager.close();
        }
    }

    @ApiOperation(value = "Deletes Validation by ValidationOutputId", notes = "Deletes Validations")
    @DeleteMapping(value="/validations/delete/{Id}")
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted Validations", response = ValidationOutputEntityShort.class),
            @ApiResponse(code = 404, message = "No Validations exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public void deleteValidations(@PathVariable Integer Id) {
        validationOutputRepo.deleteByValidationOutputID(Id);
    }

    @ApiOperation(value = "Deletes Validation by Reference, Survey, Period", notes = "Deletes Validations")
    @DeleteMapping(value="/validations/remove/{matrixVars}")
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted Validations", response = ValidationOutputEntityShort.class),
            @ApiResponse(code = 404, message = "No Validations exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public void deleteValidationsByMatrix(@MatrixVariable Map<String, String> matrixVars) {

        String reference=matrixVars.get("reference");
        String period=matrixVars.get("period");
        String survey=matrixVars.get("survey");

        validationOutputRepo.deleteByReferenceAndPeriodAndSurvey(reference, period, survey);
    }
}
