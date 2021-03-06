package uk.gov.ons.collection.ValidationPersistenceLayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntityShort;

import java.util.List;

@Repository
public interface ValidationOutputRepoShort extends JpaRepository<ValidationOutputEntityShort, Integer>, JpaSpecificationExecutor<ValidationOutputEntityShort> {

    Iterable<ValidationOutputEntityShort> findByReferenceAndPeriodAndSurvey(String reference, String period, String survey);

}
