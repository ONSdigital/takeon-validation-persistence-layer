package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "validationform", schema = "dev01")
@ApiModel(value = "ValidationForm", description = "A ValidationForm entity, maps ValidationForm table to object")
public class ValidationFormEntity {

    @JsonManagedReference
    @OneToOne
    @JoinColumn(updatable = false, insertable = false, name = "validationcode", referencedColumnName = "validationrule")
    private ValidationRuleEntity validationRuleEntity;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(updatable = false, insertable = false, name = "validationid", referencedColumnName = "validationid")
    private ValidationParameterEntity validationParameterEntity;

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

}
