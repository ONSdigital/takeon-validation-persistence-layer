package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "validationoutput", schema = "dev01")
@ApiModel(value = "ValidationOutput", description = "A ValidationOutput entity, maps ValidationOutput table to object")
public class ValidationOutputEntityShort {

    @Id
    @Column(name = "validationoutputid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer validationOutputID;

    @Column(name = "reference", length = 11, nullable = false)
    private String reference;

    @Column(name = "period", length = 6, nullable = false)
    private String period;

    @Column(name = "survey", length = 6, nullable = false)
    private String survey;

    @Column(name = "validationid", nullable = false)
    private Integer validationID;

    @Column(name = "instance", nullable = false)
    private Integer instance;

    @Column(name = "primaryvalue", length = 128, nullable = false)
    private String primaryValue;

    @Column(name = "formula", length = 128, nullable = false)
    private String formula;

    @Column(name = "createdby", length = 16, nullable = false)
    private String createdBy;

    @Column(name = "createddate", length = 7, nullable = false)
    private Timestamp createdDate;

    @Column(name = "lastupdatedby", length = 16)
    private String lastUpdatedBy;

    @Column(name = "lastupdateddate", length = 7)
    private Timestamp lastUpdatedDate;
}
