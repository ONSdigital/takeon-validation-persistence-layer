package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReturnedValidationOutputs {

    private String triggered;
    private String valueFormula;
    private Map<String, String> metaData;
    private String error;

}
