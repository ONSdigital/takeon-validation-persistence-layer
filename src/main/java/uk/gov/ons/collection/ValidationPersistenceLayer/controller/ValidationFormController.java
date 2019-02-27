package uk.gov.ons.collection.ValidationPersistenceLayer.controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationFormEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationFormRepo;

@Api(value = "Validation  Entity", description = "offers CRUD operations for the Validation entity")
@RestController
@RequestMapping(value = "/validation-pl", method = RequestMethod.GET)
public class ValidationFormController {

    @Autowired
    private ValidationFormRepo validationFormRepo;

    @ApiOperation(value = "Returns Validation Config for a form",
            notes = "Returns all configuration data for all Validation rules - using validation ID and validation code")
    @GetMapping(value = "/validations/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Validation Config", response = ValidationFormEntity.class),
            @ApiResponse(code = 404, message = "No Validation rule exists"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public Iterable<ValidationFormEntity> getConfig(
            @ApiParam(value = "Unique Validation ID", name = "validationId", required = true, example = "1") @RequestParam("validationId") Integer validationId,
            @ApiParam(value = "Validation Code - VP, NV etc", name = "validationCode", required = true, example = "VP") @RequestParam("validationCode") String validationCode) {
        return validationFormRepo.findByValidationidAndValidationCode(validationId, validationCode);
    }

}
