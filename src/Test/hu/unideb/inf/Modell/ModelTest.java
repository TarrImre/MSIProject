package hu.unideb.inf.Modell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ModelTest {

    @Test
    void testConstructor() {
        Model actualModel = new Model();
        Patient patient = actualModel.getPatient();
        assertEquals(0, patient.getZipCode());
        User user = actualModel.getUser();
        assertNull(user.getUsername());
        assertNull(user.getTheme());
        assertFalse(user.getRadius());
        assertNull(user.getPassword());
        assertEquals(0, user.getId());
        assertNull(user.getEmail());
        assertNull(patient.getStreetNumber());
        assertNull(patient.getStreet());
        assertEquals(0, patient.getSocialInsuranceId());
        assertNull(patient.getNameOfMother());
        assertNull(patient.getName());
        assertEquals(0, patient.getId());
        assertNull(patient.getGender());
        assertNull(patient.getDiagnose());
        assertNull(patient.getCity());
        assertEquals(0, patient.getCardNumber());
        assertNull(patient.getBirthDate());
    }


    @Test
    void testConstructor2() {
        Model actualModel = new Model();
        Patient patient = actualModel.getPatient();
        assertEquals(0, patient.getZipCode());
        User user = actualModel.getUser();
        assertNull(user.getUsername());
        assertNull(user.getTheme());
        assertFalse(user.getRadius());
        assertNull(user.getPassword());
        assertEquals(0, user.getId());
        assertNull(user.getEmail());
        assertNull(patient.getStreetNumber());
        assertNull(patient.getStreet());
        assertEquals(0, patient.getSocialInsuranceId());
        assertNull(patient.getNameOfMother());
        assertNull(patient.getName());
        assertEquals(0, patient.getId());
        assertNull(patient.getGender());
        assertNull(patient.getDiagnose());
        assertNull(patient.getCity());
        assertEquals(0, patient.getCardNumber());
        assertNull(patient.getBirthDate());
    }
}

