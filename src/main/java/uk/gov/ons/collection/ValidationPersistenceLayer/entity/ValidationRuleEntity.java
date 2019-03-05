package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor(force= true)
@Table(name = "validationrule", schema = "dev01")
@ApiModel(value = "ValidationRule", description = "A ValidationRule entity, maps ValidationRule table to object")
public class ValidationRuleEntity {

    @Id
    @Column(name = "validationrule", length = 16, nullable = false)
    private final @NonNull String validationRule;

    @Column(name = "name", length = 32, nullable = false)
    private final @NonNull String name;

    @Column(name = "description", length = 128, nullable = false)
    private final @NonNull String description;

    @Column(name = "createdby", length = 16, nullable = false)
    private final @NonNull String createdBy;

    @Column(name = "createddate", length = 7, nullable = false)
    private final @NonNull Timestamp createdDate;

    @Column(name = "lastupdatedby", length = 16)
    private String lastUpdatedBy;

    @Column(name = "lastupdateddate", length = 7)
    private Timestamp lastUpdatedDate;

}
