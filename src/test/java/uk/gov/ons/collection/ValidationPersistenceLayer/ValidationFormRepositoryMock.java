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


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("mocking")
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {uk.gov.ons.collection.ValidationPersistenceLayer.ValidationPersistenceLayerApp.class})
public class ValidationFormRepositoryMock {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ValidationFormRepo validationFormRepo;


    @Before
    public void setup(){

        ValidationRuleEntity validationRule = new ValidationRuleEntity("VP", "Value Present",
                "Trigger if value is present",
                "Ryan", new Timestamp(new Date().getTime()));

        ValidationParameterEntity validationParameter = new ValidationParameterEntity(1, "90123",
                "Value", "146", "Ryan", new Timestamp(new Date().getTime()));


        ValidationFormEntity validationForm = new ValidationFormEntity(1, 1, "VP",
                "146", "Q146!=", "E", "Ryan",
                new Timestamp(new Date().getTime()));


        ValidationOutputEntity validationOutput = new ValidationOutputEntity("49900000000", "201709",
                "066", 1, 0, "146", "146!=",
                "Ryan", new Timestamp(new Date().getTime()));

        entityManager.persist(validationRule);
        entityManager.persist(validationParameter);
        entityManager.persist(validationForm);
        entityManager.persist(validationOutput);
        entityManager.flush();


    }

    @Test
    public void whenFindByValidationId_thenReturnConfig() {
        Integer validationId = 1;

        // When
        List<ValidationFormEntity> validRuleSearch = validationFormRepo.findByValidationid(validationId);

        // Then
        assertThat(validRuleSearch.get(0).getValidationid())
                .isEqualTo(1);

    }

    @Test
    public void whenFindByFormId_thenReturnFormConfig() {
        Integer testFormID= 1;

        // When
        List<ValidationFormEntity> formSearch = validationFormRepo.findByFormID(testFormID);

        // Then
        assertThat(formSearch.get(0).getFormID())
                .isEqualTo(1);



    }


}
