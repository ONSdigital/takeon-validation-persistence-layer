package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "validationrule", schema = "dev01")
@ApiModel(value = "ValidationRule", description = "A ValidationRule entity, maps ValidationRule table to object")
public class ValidationRuleEntity {

    @Id
    @Column(name = "validationrule", length = 16, nullable = false)
    private String validationRule;

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "description", length = 128, nullable = false)
    private String description;

    @Column(name = "createdby", length = 16, nullable = false)
    private String createdBy;

    @Column(name = "createddate", length = 7, nullable = false)
    private Timestamp createdDate;

    @Column(name = "lastupdatedby", length = 16)
    private String lastUpdatedBy;

    @Column(name = "lastupdateddate", length = 7)
    private Timestamp lastUpdatedDate;

}
