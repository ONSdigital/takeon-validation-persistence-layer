package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "validationoutput", schema = "dev01")
@ApiModel(value = "ValidationOutput", description = "A ValidationOutput entity, maps ValidationOutput table to object")
public class ValidationOutputEntity {

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(updatable = false, insertable = false, name = "validationid", referencedColumnName = "validationid")
    private ValidationFormEntity validationFormEntity;

    public ValidationOutputEntity(String reference, String period, String survey, Integer validationID,
                                  Integer instance, String primaryValue, String formula, String createdBy, Timestamp createdDate) {
        this.reference = reference;
        this.period = period;
        this.survey = survey;
        this.validationID = validationID;
        this.instance = instance;
        this.primaryValue = primaryValue;
        this.formula = formula;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public ValidationOutputEntity(){

    }


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

    public ValidationFormEntity getValidationFormEntity() {
        return validationFormEntity;
    }

    public void setValidationFormEntity(ValidationFormEntity validationFormEntity) {
        this.validationFormEntity = validationFormEntity;
    }

    public Integer getValidationOutputID() {
        return validationOutputID;
    }

    public void setValidationOutputID(Integer validationOutputID) {
        this.validationOutputID = validationOutputID;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public Integer getValidationID() {
        return validationID;
    }

    public void setValidationID(Integer validationID) {
        this.validationID = validationID;
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(Integer instance) {
        this.instance = instance;
    }

    public String getPrimaryValue() {
        return primaryValue;
    }

    public void setPrimaryValue(String primaryValue) {
        this.primaryValue = primaryValue;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public String toString() {
        return "ValidationOutputEntity{" +
                //"validationFormEntity=" + validationFormEntity +
                ", validationOutputID=" + validationOutputID +
                ", reference='" + reference + '\'' +
                ", period='" + period + '\'' +
                ", survey='" + survey + '\'' +
                ", validationID=" + validationID +
                ", instance=" + instance +
                ", primaryValue='" + primaryValue + '\'' +
                ", formula='" + formula + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
