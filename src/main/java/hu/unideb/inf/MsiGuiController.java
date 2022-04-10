package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAPatientDAO;
import hu.unideb.inf.Modell.Model;
import hu.unideb.inf.Modell.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MsiGuiController implements Initializable {

    private Model model;



    @FXML
    private TextField nev_input;

    @FXML
    private TextField kartonszam_input;

    @FXML
    private TextField anyjaneve_input;

    @FXML
    private TextField taj_input;

    @FXML
    private TextField szuletesidatum_input;

    @FXML
    private TextArea betegsegek_input;

    @FXML
    private TextField iranyitoszam_input;

    @FXML
    private TextField varos_input;

    @FXML
    private TextField utca_input;

    @FXML
    private TextField hazszam_input;

    @FXML
    private RadioButton radioMale;

    @FXML
    private RadioButton radioFemale;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML private Pane topPane;
    @FXML private double x,y;

    public void init(Stage stage){
        topPane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        topPane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);

        });

    }


    @FXML
    private void Close(ActionEvent event) {
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void Talca(ActionEvent event) {
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    void mainwindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MsiGui.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MSI Projekt");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image("/fxml/img/windowsicon.png"));
            ((MsiGuiController)fxmlLoader.getController()).init(stage);
            Close(event);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerwindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registerpage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MSI Projekt");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image("/fxml/img/windowsicon.png"));
            ((MsiGuiController)fxmlLoader.getController()).init(stage);
            Close(event);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loginwindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginpage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MSI Projekt");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image("/fxml/img/windowsicon.png"));
            ((MsiGuiController)fxmlLoader.getController()).init(stage);
            Close(event);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void Exit(ActionEvent event) {
        loginwindow(event);
    }

    @FXML
    void Settings(ActionEvent event) {
        //DARK THEME
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void PatientRegisterButtonPushed(ActionEvent event) {

        try(JPAPatientDAO aDAO = new JPAPatientDAO();){
            Patient patient = new Patient();
            patient.setName(nev_input.getText());
            patient.setCity(varos_input.getText());
            patient.setBirthDate(szuletesidatum_input.getText());
            patient.setCardNumber(Integer.parseInt(kartonszam_input.getText()));
            patient.setDiagnose(betegsegek_input.getText());
            patient.setNameOfMother(anyjaneve_input.getText());
            patient.setStreetNumber(Integer.parseInt(hazszam_input.getText()));
            patient.setZipCode(Integer.parseInt(iranyitoszam_input.getText()));
            patient.setStreet(utca_input.getText());
            patient.setSocialInsuranceId(Integer.parseInt(taj_input.getText()));

            if (radioMale.isSelected()){
                patient.setGender("MALE");
            }else {
                patient.setGender("FEMALE");
            }

            aDAO.savePatient(patient);

            clearTexts();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Betegfelvétel Sikeres");
            alert.setContentText("A beteg sikeresen felvételre került!");
            alert.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }

        //ELLENORZES?
    }

    private void clearTexts() {
        nev_input.setText("");
        varos_input.setText("");
        iranyitoszam_input.setText("");
        utca_input.setText("");
        iranyitoszam_input.setText("");
        taj_input.setText("");
        kartonszam_input.setText("");
        betegsegek_input.setText("");
        szuletesidatum_input.setText("");
        anyjaneve_input.setText("");
        hazszam_input.setText("");
        radioMale.setSelected(false);
        radioFemale.setSelected(false);
    }
}
