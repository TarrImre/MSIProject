package hu.unideb.inf.Modell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatientTest {


    @Test
    void testConstructor() {
        Patient actualPatient = new Patient();
        actualPatient.setBirthDate("2020-03-01");
        actualPatient.setCardNumber(10);
        actualPatient.setCity("Oxford");
        actualPatient.setDiagnose("Diagnose");
        actualPatient.setGender("Gender");
        actualPatient.setId(1);
        actualPatient.setName("Name");
        actualPatient.setNameOfMother("Name Of Mother");
        actualPatient.setSocialInsuranceId(123);
        actualPatient.setStreet("Street");
        actualPatient.setStreetNumber("42");
        actualPatient.setZipCode(1);
        assertEquals("2020-03-01", actualPatient.getBirthDate());
        assertEquals(10, actualPatient.getCardNumber());
        assertEquals("Oxford", actualPatient.getCity());
        assertEquals("Diagnose", actualPatient.getDiagnose());
        assertEquals("Gender", actualPatient.getGender());
        assertEquals(1, actualPatient.getId());
        assertEquals("Name", actualPatient.getName());
        assertEquals("Name Of Mother", actualPatient.getNameOfMother());
        assertEquals(123, actualPatient.getSocialInsuranceId());
        assertEquals("Street", actualPatient.getStreet());
        assertEquals("42", actualPatient.getStreetNumber());
        assertEquals(1, actualPatient.getZipCode());
    }
}

