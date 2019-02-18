package uk.gov.ons.collection.ValidationPersistenceLayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationParameterEntity;

public interface ValidationParameterRepo extends JpaRepository<ValidationParameterEntity,Integer>,
        JpaSpecificationExecutor<ValidationParameterEntity> {
}
