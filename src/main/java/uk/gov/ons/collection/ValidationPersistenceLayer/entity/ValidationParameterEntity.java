package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor(force= true)
@Table(name = "validationparameter", schema = "dev01")
@ApiModel(value = "ValidationParameter", description = "A ValidationParameter entity")
public class ValidationParameterEntity {

    @Id
    @Column(name = "validationid", nullable = false)
    private final @NonNull Integer validationID;

    @Column(name = "attributevalue", length = 32, nullable = false)
    private final @NonNull String attributeValue;

    @Column(name = "parameter", length = 32, nullable = false)
    private final @NonNull String parameter;

    @Column(name = "value", length = 32, nullable = false)
    private final @NonNull String value;

    @Column(name = "createdby", length = 16, nullable = false)
    private final @NonNull String createdBy;

    @Column(name = "createddate", length = 7, nullable = false)
    private final @NonNull Timestamp createdDate;

    @Column(name = "lastupdatedby", length = 16)
    private String lastUpdatedBy;

    @Column(name = "lastupdateddate", length = 7)
    private Timestamp lastUpdatedDate;

}
