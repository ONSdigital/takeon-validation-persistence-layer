package uk.gov.ons.collection.ValidationPersistenceLayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntity;

import java.util.List;

@Repository
public interface ValidationOutputRepo extends JpaRepository<ValidationOutputEntity, Integer>,
        JpaSpecificationExecutor<ValidationOutputEntity> {

    List<ValidationOutputEntity> findByReferenceAndPeriodAndSurvey(String reference, String period, String survey);
}
