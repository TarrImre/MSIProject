package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAPatientDAO;
import hu.unideb.inf.DAO.JPAUserDAO;
import hu.unideb.inf.Modell.Model;
import hu.unideb.inf.Modell.Patient;
import hu.unideb.inf.Modell.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
    private TextField name_input;

    @FXML
    private TextField cardnum_input;

    @FXML
    private TextField mothersname_input;

    @FXML
    private TextField taj_input;

    @FXML
    private TextField birthdate_input;

    @FXML
    private TextArea diagnose_input;

    @FXML
    private TextField zipcode_input;

    @FXML
    private TextField city_input;

    @FXML
    private TextField street_input;

    @FXML
    private TextField housenum_input;

    @FXML
    private RadioButton radioMale;

    @FXML
    private RadioButton radioFemale;

    @FXML
    private TextField usernamereg;

    @FXML
    private TextField emailreg;

    @FXML
    private TextField passwordreg;


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
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);

        });

    }

    @FXML
    private AnchorPane parent;

    public void LightButton(ActionEvent event){
        parent.getStylesheets().remove("fxml/dark.css");
        parent.getStylesheets().add("fxml/style.css");

    }
    public void DarkButton(ActionEvent event){
        parent.getStylesheets().remove("fxml/style.css");
        parent.getStylesheets().add("fxml/dark.css");

    }


    @FXML
    private Pane overlay;
    @FXML
    private Button overlaybutton,overlayhidebutton;

    @FXML
    void overlayAction(ActionEvent event) {

        if (event.getSource() == overlaybutton)
        {
            overlay.toFront();
        }

    }
    @FXML
    void overlayActionHide(ActionEvent event) {
        if (event.getSource() == overlayhidebutton)
        {
            overlay.toBack();
        }
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void Tray(ActionEvent event) {
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
    void Helpp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ThemesSettings.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MSI Projekt");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image("/fxml/img/windowsicon.png"));
            ((MsiGuiController)fxmlLoader.getController()).init(stage);
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
    public void UserRegisterButtonPushed(ActionEvent event) {
        try(JPAUserDAO userDAO = new JPAUserDAO()) {
            User user = new User();
            user.setUsername(usernamereg.getText());
            user.setEmail(emailreg.getText());
            user.setPassword(passwordreg.getText());

            userDAO.saveUser(user);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A regisztráció sikeres!");
            alert.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }

        //ellenorzes
    }



    @FXML
    public void PatientRegisterButtonPushed(ActionEvent event) {

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){
            Patient patient = new Patient();
            patient.setName(name_input.getText());
            patient.setCity(city_input.getText());
            patient.setBirthDate(birthdate_input.getText());
            patient.setCardNumber(Integer.parseInt(cardnum_input.getText()));
            patient.setDiagnose(diagnose_input.getText());
            patient.setNameOfMother(mothersname_input.getText());
            patient.setStreetNumber(Integer.parseInt(housenum_input.getText()));
            patient.setZipCode(Integer.parseInt(zipcode_input.getText()));
            patient.setStreet(street_input.getText());
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
        //betegfelvetel
        name_input.setText("");
        city_input.setText("");
        zipcode_input.setText("");
        street_input.setText("");
        zipcode_input.setText("");
        taj_input.setText("");
        cardnum_input.setText("");
        diagnose_input.setText("");
        birthdate_input.setText("");
        mothersname_input.setText("");
        housenum_input.setText("");
        radioMale.setSelected(false);
        radioFemale.setSelected(false);

    }
}
