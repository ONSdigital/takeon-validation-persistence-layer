package uk.gov.ons.collection.ValidationPersistenceLayer.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "validationparameter", schema = "dev01")
@ApiModel(value = "ValidationParameter", description = "A ValidationParameter entity")
public class ValidationParameterEntity {

    @Id
    @Column(name = "validationid", nullable = false)
    private @NonNull Integer validationID;

    @Column(name = "attributevalue", length = 32, nullable = false)
    private @NonNull String attributeValue;

    @Column(name = "parameter", length = 32, nullable = false)
    private @NonNull String parameter;

    @Column(name = "value", length = 32, nullable = false)
    private @NonNull String value;

    @Column(name = "createdby", length = 16, nullable = false)
    private @NonNull String createdBy;

    @Column(name = "createddate", length = 7, nullable = false)
    private @NonNull Timestamp createdDate;

    @Column(name = "lastupdatedby", length = 16)
    private String lastUpdatedBy;

    @Column(name = "lastupdateddate", length = 7)
    private Timestamp lastUpdatedDate;

}
