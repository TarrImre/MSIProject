package hu.unideb.inf.Controller;

import hu.unideb.inf.DAO.JPAPatientDAO;
import hu.unideb.inf.DAO.PatientDAO;
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
    private TextField varos_input;


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
    private TextField utca_input;

    @FXML
    private TextField hazszam_input;

    //@FXML
    //private RadioButton nem_input;


    public void setModel(Model model) {
        this.model = model;
    }

    @FXML private Pane topPane;
    private double x,y;

    public void init(Stage stage){

        topPane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        topPane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getSceneX()-x);
            stage.setY(mouseEvent.getSceneY()-y);

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
    void Settings(ActionEvent event) {
        //DARK THEME
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void PatientRegisterButtonPushed(ActionEvent event) {

        /**/

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
            aDAO.savePatient(patient);
        }catch(Exception e){
            e.printStackTrace();
        }

        //ELLENORZES?
    }
}
