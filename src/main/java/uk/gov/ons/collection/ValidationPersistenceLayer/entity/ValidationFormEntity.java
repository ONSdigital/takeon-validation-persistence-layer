package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "validationform", schema = "dev01")
@ApiModel(value = "ValidationForm", description = "A ValidationForm entity, maps ValidationForm table to object")
public class ValidationFormEntity {

    @JsonManagedReference
    @OneToOne
    @JoinColumn(updatable = false, insertable = false, name = "validationcode", referencedColumnName = "validationrule")
    private ValidationRuleEntity validationRuleEntity;

    @Id
    @Column(name = "validationid", nullable = false)
    private Integer validationid;

    @Column(name = "formid", nullable = false)
    private Integer formID;

    @Column(name = "validationcode", length = 16, nullable = false)
    private String validationCode;

    @Column(name = "questioncode", length = 4, nullable = false)
    private String questionCode;

    @Column(name = "precalculationformula", length = 256, nullable = false)
    private String preCalculationFormula;

    @Column(name = "severity", length = 16, nullable = false)
    private String severity;

    @Column(name = "createdby", length = 16, nullable = false)
    private String createdBy;

    @Column(name = "createddate", length = 7, nullable = false)
    private Timestamp createdDate;

    @Column(name = "lastupdatedby", length = 16)
    private String lastUpdatedBy;

    @Column(name = "lastupdateddate", length = 7)
    private Timestamp lastUpdatedDate;

    public Integer getValidationid() {
        return validationid;
    }

    public ValidationRuleEntity getValidationRuleEntity() {
        return validationRuleEntity;
    }

    public void setValidationRuleEntity(ValidationRuleEntity validationRuleEntity) {
        this.validationRuleEntity = validationRuleEntity;
    }

    public void setValidationid(Integer validationid) {
        this.validationid = validationid;
    }

    public Integer getValidationID() {
        return validationid;
    }

    public void setValidationID(Integer validationID) {
        this.validationid = validationID;
    }

    public Integer getFormID() {
        return formID;
    }

    public void setFormID(Integer formID) {
        this.formID = formID;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getPreCalculationFormula() {
        return preCalculationFormula;
    }

    public void setPreCalculationFormula(String preCalculationFormula) {
        this.preCalculationFormula = preCalculationFormula;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
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
        return "ValidationFormEntity{" +
                "validationid=" + validationid +
                ", formID=" + formID +
                ", validationCode='" + validationCode + '\'' +
                ", questionCode='" + questionCode + '\'' +
                ", preCalculationFormula='" + preCalculationFormula + '\'' +
                ", severity='" + severity + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
