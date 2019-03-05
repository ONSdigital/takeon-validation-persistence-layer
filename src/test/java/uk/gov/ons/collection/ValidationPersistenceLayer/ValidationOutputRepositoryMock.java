package uk.gov.ons.collection.ValidationPersistenceLayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationFormEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationOutputEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationParameterEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.entity.ValidationRuleEntity;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationFormRepo;
import uk.gov.ons.collection.ValidationPersistenceLayer.repository.ValidationOutputRepo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("mocking")
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {ValidationPersistenceLayerApp.class})
public class ValidationOutputRepositoryMock {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ValidationOutputRepo validationOutputRepo;

    @Before
    public void setup(){

        ValidationRuleEntity validationRule = new ValidationRuleEntity("VP", "Value Present",
                "Trigger if value is present",
                "Ryan", new Timestamp(new Date().getTime()));

        ValidationParameterEntity validationParameter = new ValidationParameterEntity(1, "90123",
                "Value", "146", "Ryan", new Timestamp(new Date().getTime()));

        ValidationFormEntity validationForm = new ValidationFormEntity(1, 1,
                "VP", "146", "Q146!=", "E", "Ryan",
                new Timestamp(new Date().getTime()));

        entityManager.persist(validationRule);
        entityManager.persist(validationParameter);
        entityManager.persist(validationForm);
        entityManager.flush();
    }

    @Test
    public void whenValidationOutput_thenSave() {

        // When
        ValidationOutputEntity testSaveOutput = new ValidationOutputEntity("49900000000", "201709",
                "066", 1, 0, "146", "146!=",
                "Ryan", new Timestamp(new Date().getTime()));
        validationOutputRepo.save(testSaveOutput);

        // Then
        assertThat(validationOutputRepo.getOne(1).getValidationOutputID())
                .isEqualTo(1);
    }
}
