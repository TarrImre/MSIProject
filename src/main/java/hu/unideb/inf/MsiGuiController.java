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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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


    @FXML Pane exitoverlay;
    @FXML Button ExitOverlayButtonShow,ExitOverlayButtonHide;
    @FXML
    void ExitOverlayButtonAction(ActionEvent event) {
        if (event.getSource() == ExitOverlayButtonShow)
        {
            exitoverlay.toFront();
        }
    }
    @FXML
    void ExitOverlayButtonActionHide2(ActionEvent event) {
        if (event.getSource() == ExitOverlayButtonHide)
        {
            exitoverlay.toBack();
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
    public void loginwindow(ActionEvent event) {
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
    void LogoutButton(ActionEvent event) {
        loginwindow(event);
        //BIZTOSAN KILEP?
    }


    @FXML
    void Settings(ActionEvent event) {
        //DARK THEME
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @FXML Button RandomNumberButton;
    @FXML
    void RandomNumberAction(ActionEvent event) {
        Random rand = new Random();
        int n = rand.nextInt(1000);
        n += 1;

        if (event.getSource() == RandomNumberButton)
        {
            cardnum_input.setText(Integer.toString(n));
        }
    }
    @FXML
    void DisableMouse2(MouseEvent event) {
        cardnum_input.setEditable(false);
    }


    @FXML
    private Label SuccesPatient;

    @FXML
    public void PatientRegisterButtonPushed(ActionEvent event) {

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){

            if (!isAllFilled()){
                Message("Minden mezőt kötelező kitölteni!");
                return;
            }
            if (!zipcode_input.getText().matches("[0-9]+") || !taj_input.getText().matches("[0-9]+")){
                Message("Az irányítószám és tajszám mezők\n csak számokat tartalmazhatnak!");
                return;
            }

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

            Message("A beteg sikeresen felvételre került!");

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
        taj_input.setText("");
        cardnum_input.setText("");
        diagnose_input.setText("");
        birthdate_input.setText("");
        mothersname_input.setText("");
        housenum_input.setText("");
        radioMale.setSelected(false);
        radioFemale.setSelected(false);
    }

    private boolean isAllFilled(){
        if(name_input.getText() == null || name_input.getText().trim().isEmpty()) return false;
        if(city_input.getText() == null || city_input.getText().trim().isEmpty()) return false;
        if(zipcode_input.getText() == null || zipcode_input.getText().trim().isEmpty()) return false;
        if(street_input.getText() == null || street_input.getText().trim().isEmpty()) return false;
        if(taj_input.getText() == null || taj_input.getText().trim().isEmpty()) return false;
        if(cardnum_input.getText() == null || cardnum_input.getText().trim().isEmpty()) return false;
        if(diagnose_input.getText() == null || diagnose_input.getText().trim().isEmpty()) return false;
        if(birthdate_input.getText() == null || birthdate_input.getText().trim().isEmpty()) return false;
        if(mothersname_input.getText() == null || mothersname_input.getText().trim().isEmpty()) return false;
        if(housenum_input.getText() == null || housenum_input.getText().trim().isEmpty()) return false;
        if (!radioMale.isSelected() && !radioFemale.isSelected()) return false;

        return true;
    }


    private void Message(String message){
        SuccesPatient.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                "\t-fx-border-color: red;\n" +
                "\t-fx-border-width:2px;");
        SuccesPatient.setText(message);
    }
}
