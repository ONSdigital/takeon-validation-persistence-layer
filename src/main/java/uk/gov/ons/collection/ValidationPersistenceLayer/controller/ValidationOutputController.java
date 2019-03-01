package uk.gov.ons.collection.ValidationPersistenceLayer.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationOutputRepo;

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
    @GetMapping(value = "/validations/findby", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "Saves all Validation Outputs", notes = "Saves all columns for all triggered Validations")
    @PutMapping(value = "/validations/output", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Saved Validations", response = ValidationOutputEntity.class),
            @ApiResponse(code = 404, message = "No Validation exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public void saveValidationOutput(
            @ApiParam(value = "11-digit RU reference number", name = "reference", required = true, example = "49900000000") @RequestParam("reference") String reference,
            @ApiParam(value = "Surveyed period - YYYYMM", name = "period", required = true, example = "201709") @RequestParam("period") String period,
            @ApiParam(value = "3-digit Unique survey code", name = "survey", required = true, example = "066") @RequestParam("survey") String survey,
            @ApiParam(value = "Validation ID", name = "validationid", required = true, example = "1")@RequestParam("validationid") Integer validationID,
            @ApiParam(value = "Instance", name = "instance", required = true, example = "0")@RequestParam("instance") Integer instance,
            @ApiParam(value = "Primary value", name = "primaryvalue", required = true, example = "146")@RequestParam("primaryvalue") String primaryvalue,
            @ApiParam(value = "Formula", name = "formula", required = true, example = "146!=")@RequestParam("formula") String formula,
            @ApiParam(value = "Created By", name = "createdby", required = true, example = "Ryan")@RequestParam("createdby") String createdby,
            @ApiParam(value = "Date Created", name = "createddate", required = true, example = "")@RequestParam("createddate") String createddate,
            @ApiParam(value = "Last Updated By", name = "lastupdatedby",example = "")@RequestParam(value = "lastupdatedby", required = false) String lastupdatedby,
            @ApiParam(value = "Last Updated Date", name = "lastupdateddate",example = "")@RequestParam(value = "lastupdateddate", required = false) String lastupdateddate){

        ValidationOutputEntity saveOutput = new ValidationOutputEntity(reference, period, survey, validationID, instance, primaryvalue, formula, createdby, Timestamp.valueOf(createddate));
        validationOutputRepo.save(saveOutput);

    }

}
