package uk.gov.ons.collection.ValidationPersistenceLayer.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntityShort;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationOutputRepo;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationOutputRepoShort;

import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


@Api(value = "Validation Entity", description = "offers CRUD operations for the Validation entity")
@RestController
@RequestMapping(value = "/validation-pl", method = RequestMethod.GET)
public class ValidationOutputController {

    @Autowired
    private ValidationOutputRepo validationOutputRepo;

    @Autowired
    private ValidationOutputRepoShort validationOutputRepoShort;

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
    @PostMapping(value="/validations/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Saved Validations", response = ValidationOutputEntityShort.class),
            @ApiResponse(code = 404, message = "No Validation exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public void saveValidations(@RequestBody ValidationOutputEntityShort validationOutputEntityShort) {
        validationOutputRepoShort.save(validationOutputEntityShort);
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
