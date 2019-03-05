package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor(force= true)
@Table(name = "validationoutput", schema = "dev01")
@ApiModel(value = "ValidationOutput", description = "A ValidationOutput entity, maps ValidationOutput table to object")
public class ValidationOutputEntityShort {

    @Id
    @Column(name = "validationoutputid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer validationOutputID;

    @Column(name = "reference", length = 11, nullable = false)
    private final @NonNull String reference;

    @Column(name = "period", length = 6, nullable = false)
    private final @NonNull String period;

    @Column(name = "survey", length = 6, nullable = false)
    private final @NonNull String survey;

    @Column(name = "validationid", nullable = false)
    private final @NonNull Integer validationID;

    @Column(name = "instance", nullable = false)
    private final @NonNull Integer instance;

    @Column(name = "primaryvalue", length = 128, nullable = false)
    private final @NonNull String primaryValue;

    @Column(name = "formula", length = 128, nullable = false)
    private final @NonNull String formula;

    @Column(name = "createdby", length = 16, nullable = false)
    private final @NonNull String createdBy;

    @Column(name = "createddate", length = 7, nullable = false)
    private final @NonNull Timestamp createdDate;

    @Column(name = "lastupdatedby", length = 16)
    private String lastUpdatedBy;

    @Column(name = "lastupdateddate", length = 7)
    private Timestamp lastUpdatedDate;
}
