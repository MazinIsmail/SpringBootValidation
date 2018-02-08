package com.springvalidation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.springvalidation.domain.ARequest;
import com.springvalidation.domain.Document;
import com.springvalidation.domain.Treatment;

@RunWith(SpringRunner.class)
public class RequestValidationTest {

	@Test
	public void testAWDeClaimRequest_Success() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		ARequest aRequest = getARequest();
		Set<ConstraintViolation<ARequest>> result = validator.validate(aRequest);
		assertEquals(0, result.size());
		validator.destroy();
	}

	@Test
	public void testAWDeClaimRequest_TransactionDateTime() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		ARequest aRequest = getARequest();
		aRequest.setTransactionDateTime("2018-02-29 18:20:11");
		Set<ConstraintViolation<ARequest>> result = validator.validate(aRequest);
		assertEquals(1, result.size());
		validator.destroy();
	}

	private ARequest getARequest() {
		ARequest aWDeClaimRequest = new ARequest();
		List<Document> documentList = new ArrayList<>();
		Document document = new Document();
		document.setCurrency("HKD");
		document.setDocumentType("breakdown charge");
		document.setFileKey("OUOKDKJEIJLKDJFLKDJSF");
		document.setTransactionAmount("2000000");
		documentList.add(document);
		aWDeClaimRequest.setDocument(documentList);
		List<Treatment> treatmentList = new ArrayList<>();
		Treatment treatment = new Treatment();
		treatment.setTreatmentCategory("I");
		treatment.setTreatmentType("GP");
		treatmentList.add(treatment);
		aWDeClaimRequest.setTreatment(treatmentList);
		aWDeClaimRequest.setClientProfile("1");
		aWDeClaimRequest.setDateOfBirth("2017-08-21");
		aWDeClaimRequest.setGroupNumber("2890000001");
		aWDeClaimRequest.setHasSecondClaim("Y");
		aWDeClaimRequest.setInternalSecondClaimCertificateNumber("122152aa");
		aWDeClaimRequest.setInternalSecondClaimGroupNumber("123587");
		aWDeClaimRequest.setInternalSecondClaimSubGroupNumber("asd123");
		aWDeClaimRequest.setIsSecondClaim("Y");
		aWDeClaimRequest.setLegalDocumentId("aser2123sdfsdfatyfhfgdf");
		aWDeClaimRequest.setStaffIndicator("Y");
		aWDeClaimRequest.setSystemCode("CAS");
		aWDeClaimRequest.setTransactionDateTime("2017-08-21 18:20:11");
		aWDeClaimRequest.setTransactionReferenceNumber("1234567890123456");
		return aWDeClaimRequest;
	}
}
