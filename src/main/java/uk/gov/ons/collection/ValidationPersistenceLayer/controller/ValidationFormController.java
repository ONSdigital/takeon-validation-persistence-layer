package uk.gov.ons.collection.ValidationPersistenceLayer.controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationFormEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationFormRepo;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationOutputRepo;

import java.util.Map;

@Api(value = "Validation  Entity", description = "offers CRUD operations for the Validation entity")
@RestController
@RequestMapping(value = "/validation-pl", method = RequestMethod.GET)
public class ValidationFormController {

    @Autowired
    private ValidationFormRepo validationFormRepo;



    @ApiOperation(value = "Returns Validation Config for a Validation ID",
            notes = "Returns all configuration data for all Validation rules - using validation ID")
    @GetMapping(value = "/validations/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Validation Config", response = ValidationFormEntity.class),
            @ApiResponse(code = 404, message = "No Validation rule exists"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public Iterable<ValidationFormEntity> getConfig(
            @ApiParam(value = "Unique Validation ID", name = "validationId", required = true, example = "1") @RequestParam("validationId") Integer validationId) {
        return validationFormRepo.findByValidationid(validationId);
    }

    @ApiOperation(value = "Returns Validation Config for a form",
            notes = "Returns all configuration data for all Validation rules - using Form ID")
    @GetMapping(value = "/validations/configuration/form/{params}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Validation Config", response = ValidationFormEntity.class),
            @ApiResponse(code = 404, message = "No Validation rule exists"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public Iterable<ValidationFormEntity> getFormConfig(
            @ApiParam(value = "Unique Form ID", name = "formID", required = true, example = "1") @MatrixVariable Map<String, String> params) {
        System.out.println(params.get("FormID"));
        return validationFormRepo.findByFormID(Integer.parseInt(params.get("FormID")));
    }
}