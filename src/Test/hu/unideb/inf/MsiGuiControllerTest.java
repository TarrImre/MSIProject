package hu.unideb.inf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.unideb.inf.Modell.Model;
import hu.unideb.inf.Modell.Patient;
import hu.unideb.inf.Modell.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MsiGuiControllerTest {


    @Test
    void testConstructor() {
        MsiGuiController actualMsiGuiController = new MsiGuiController();
        Model model = new Model();
        actualMsiGuiController.setModel(model);
        assertNull(actualMsiGuiController.ExitOverlayButtonHide);
        assertNull(actualMsiGuiController.zipcodeInputField);
        assertNull(actualMsiGuiController.streetNumberInputField);
        assertNull(actualMsiGuiController.streetInputField);
        assertNull(actualMsiGuiController.searchElementInput);
        assertNull(actualMsiGuiController.radioMale);
        assertNull(actualMsiGuiController.nameInputField);
        assertNull(actualMsiGuiController.motherNameInputField);
        assertNull(actualMsiGuiController.modifyZipcodeInputField);
        assertNull(actualMsiGuiController.modifyStreetNumberInputField);
        assertNull(actualMsiGuiController.modifyStreetInputField);
        assertNull(actualMsiGuiController.modifyNameInputField);
        assertNull(actualMsiGuiController.modifyMotherNameInputField);
        assertNull(actualMsiGuiController.modifyInsuranceIdInputField);
        assertNull(actualMsiGuiController.modifyDiagnoseInputField);
        assertNull(actualMsiGuiController.modifyCityInputField);
        assertNull(actualMsiGuiController.modifyCardnumInputField);
        assertNull(actualMsiGuiController.modifyBirthDateInputField);
        assertNull(actualMsiGuiController.insuranceIdInputField);
        assertNull(actualMsiGuiController.exitOverlay);
        assertNull(actualMsiGuiController.diagnoseInputField);
        assertNull(actualMsiGuiController.cityInputField);
        assertNull(actualMsiGuiController.cardnumToRemove);
        assertNull(actualMsiGuiController.cardnumInputField);
        assertNull(actualMsiGuiController.birthdateInputField);
        assertNull(actualMsiGuiController.RandomNumberButton);
        assertNull(actualMsiGuiController.ExitOverlayButtonShow);
        Patient patient = model.getPatient();
        assertEquals(0, patient.getZipCode());
        User user = model.getUser();
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
    void testIsValidBirthDate() {
        assertTrue((new MsiGuiController()).isValidBirthDate("2020-03-01"));
        assertFalse((new MsiGuiController()).isValidBirthDate("foo"));
    }


    @Test
    void testIsValidBirthDateFails() {
        assertFalse((new MsiGuiController()).isValidBirthDate("2020-15-01"));
        assertTrue((new MsiGuiController()).isValidBirthDate("2020-02-02"));
    }
}