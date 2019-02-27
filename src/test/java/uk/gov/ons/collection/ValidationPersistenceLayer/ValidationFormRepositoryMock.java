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

        ValidationRuleEntity validationRule = new ValidationRuleEntity();
        validationRule.setValidationRule("VP");
        validationRule.setName("Value Present");
        validationRule.setDescription("Trigger if value is present");
        validationRule.setCreatedBy("Ryan");
        validationRule.setCreatedDate(new Timestamp(new Date().getTime()));

        ValidationParameterEntity validationParameter = new ValidationParameterEntity();
        validationParameter.setValidationID(1);
        validationParameter.setAttributeValue("90123");
        validationParameter.setParameter("Value");
        validationParameter.setValue("146");
        validationParameter.setCreatedBy("Ryan");
        validationParameter.setCreatedDate(new Timestamp(new Date().getTime()));

        ValidationFormEntity validationForm = new ValidationFormEntity();
        validationForm.setValidationid(1);
        validationForm.setFormID(1);
        validationForm.setValidationCode("VP");
        validationForm.setQuestionCode("146");
        validationForm.setPreCalculationFormula("Q146!=");
        validationForm.setSeverity("E");
        validationForm.setCreatedBy("Ryan");
        validationForm.setCreatedDate(new Timestamp(new Date().getTime()));

        ValidationOutputEntity validationOutput = new ValidationOutputEntity();
        validationOutput.setValidationOutputID(1);
        validationOutput.setReference("49900000000");
        validationOutput.setPeriod("201709");
        validationOutput.setSurvey("066");
        validationOutput.setValidationID(1);
        validationOutput.setInstance(0);
        validationOutput.setPrimaryValue("146");
        validationOutput.setFormula("146!=");
        validationOutput.setCreatedBy("Ryan");
        validationOutput.setCreatedDate(new Timestamp(new Date().getTime()));

        entityManager.persist(validationRule);
        entityManager.persist(validationParameter);
        entityManager.persist(validationForm);
        entityManager.persist(validationOutput);
        entityManager.flush();


    }

    @Test
    public void whenFindByValidationIdAndValidationCode_thenReturnConfig() {
        Integer validationId = 1;
        String validationCode = "VP";

        // When
        List<ValidationFormEntity> validRuleSearch = validationFormRepo.findByValidationidAndValidationCode(validationId, validationCode);

        // Then
        assertThat(validRuleSearch.get(0).getValidationid())
                .isEqualTo(1);


    }



}
