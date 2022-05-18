package hu.unideb.inf.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.unideb.inf.Modell.Patient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FilePatientDAOTest {

    @Test
    void testConstructor() throws Exception {
        FilePatientDAO actualFilePatientDAO = new FilePatientDAO();
        actualFilePatientDAO.close();
        assertTrue(actualFilePatientDAO.getPatients().isEmpty());
    }

    @Test
    void testConstructor2() {
        assertTrue((new FilePatientDAO()).getPatients().isEmpty());
    }


    @Test
    void testSavePatient() {

        FilePatientDAO filePatientDAO = new FilePatientDAO();

        Patient patient = new Patient();
        patient.setBirthDate("2020-03-01");
        patient.setCardNumber(10);
        patient.setCity("Oxford");
        patient.setDiagnose("Diagnose");
        patient.setGender("Gender");
        patient.setId(1);
        patient.setName("Name");
        patient.setNameOfMother("Name Of Mother");
        patient.setSocialInsuranceId(123);
        patient.setStreet("Street");
        patient.setStreetNumber("42");
        patient.setZipCode(1);
        filePatientDAO.savePatient(patient);
    }

    @Test
    void testDeletePatient() {
        FilePatientDAO filePatientDAO = new FilePatientDAO();

        Patient patient = new Patient();
        patient.setBirthDate("2020-03-01");
        patient.setCardNumber(10);
        patient.setCity("Oxford");
        patient.setDiagnose("Diagnose");
        patient.setGender("Gender");
        patient.setId(1);
        patient.setName("Name");
        patient.setNameOfMother("Name Of Mother");
        patient.setSocialInsuranceId(123);
        patient.setStreet("Street");
        patient.setStreetNumber("42");
        patient.setZipCode(1);
        filePatientDAO.deletePatient(patient);
        assertEquals("2020-03-01", patient.getBirthDate());
        assertEquals(1, patient.getZipCode());
        assertEquals("42", patient.getStreetNumber());
        assertEquals("Street", patient.getStreet());
        assertEquals(123, patient.getSocialInsuranceId());
        assertEquals("Name Of Mother", patient.getNameOfMother());
        assertEquals("Name", patient.getName());
        assertEquals(1, patient.getId());
        assertEquals("Gender", patient.getGender());
        assertEquals("Diagnose", patient.getDiagnose());
        assertEquals("Oxford", patient.getCity());
        assertEquals(10, patient.getCardNumber());
        assertTrue(filePatientDAO.getPatients().isEmpty());
    }


    @Test
    void testUpdatePatient() {

        FilePatientDAO filePatientDAO = new FilePatientDAO();

        Patient patient = new Patient();
        patient.setBirthDate("2020-03-01");
        patient.setCardNumber(10);
        patient.setCity("Oxford");
        patient.setDiagnose("Diagnose");
        patient.setGender("Gender");
        patient.setId(1);
        patient.setName("Name");
        patient.setNameOfMother("Name Of Mother");
        patient.setSocialInsuranceId(123);
        patient.setStreet("Street");
        patient.setStreetNumber("42");
        patient.setZipCode(1);
        filePatientDAO.updatePatient(patient);
    }
}

