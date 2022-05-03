package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAPatientDAO;
import hu.unideb.inf.DAO.JPAUserDAO;
import hu.unideb.inf.Modell.Model;
import hu.unideb.inf.Modell.Patient;
import hu.unideb.inf.Modell.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;

public class MsiGuiController implements Initializable {

    private Model model;

    @FXML
    private TableView<Patient> patientsTable;

    @FXML
    private TableColumn<Patient, Integer> cardNumberCol;

    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TableColumn<Patient, String> mothersNameCol;

    @FXML
    private TableColumn<Patient, String> genderCol;

    @FXML
    private TableColumn<Patient, String> birthDateCol;

    @FXML
    private TableColumn<Patient, String> insuranceIDCol;

    @FXML
    private TableColumn<Patient, Integer> zipcodeCol;

    @FXML
    private TableColumn<Patient, String> cityCol;

    @FXML
    private TableColumn<Patient, String> streetCol;

    @FXML
    private TableColumn<Patient, String> streetNumberCol;

    @FXML
    private TextField cardnumToRemove;

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

    public void BlueButton(ActionEvent event){
        parent.getStylesheets().remove("fxml/dark.css");
        parent.getStylesheets().add("fxml/style.css");

    }

    public void AutumnButton(ActionEvent event){
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

//THEME
    @FXML
    private Pane themeoverlay;

    @FXML
    private Button themeoverlayButton,themeoverlayButtonHide;

    @FXML
    void themeoverlayAction(ActionEvent event) {

        if (event.getSource() == themeoverlayButton)
        {
            themeoverlay.toFront();
        }

    }

    @FXML
    void themeoverlayActionHide(ActionEvent event) {
        if (event.getSource() == themeoverlayButtonHide)
        {
            themeoverlay.toBack();
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
    public void LogoutButton(ActionEvent event) throws IOException {
        loginwindow_main(event);
        //BIZTOSAN KILEP?
    }


    private void loginwindow_main(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginpage.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/img/windowsicon.png"));
        ((MsiGuiController)fxmlLoader.getController()).init(stage);
        Close(event);
        stage.show();
    }

    @FXML
    private Pane overlay_help;

    @FXML
    private Button helpButton,helpButtonHide;

    @FXML
    void HelpAction(ActionEvent event) {

        if (event.getSource() == helpButton)
        {
            overlay_help.toFront();
        }

    }
    @FXML
    void helpOverlayActionHide(ActionEvent event) {
        if (event.getSource() == helpButtonHide)
        {
            overlay_help.toBack();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cardNumberCol.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mothersNameCol.setCellValueFactory(new PropertyValueFactory<>("nameOfMother"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        insuranceIDCol.setCellValueFactory(new PropertyValueFactory<>("socialInsuranceId"));
        zipcodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        streetNumberCol.setCellValueFactory(new PropertyValueFactory<>("streetNumber"));
        patientsTable.setItems(listPatientsToUI());
    }

    @FXML Button RandomNumberButton;

    @FXML
    void RandomNumberAction(ActionEvent event) {
        try (JPAPatientDAO patientDAO = new JPAPatientDAO()) {
            Random rand = new Random();
            int n = rand.nextInt(10000);
            n += 1;

            if (patientDAO.cardnumberAlreadyExists(n)){
                RandomNumberAction(event);
            }
            else if (event.getSource() == RandomNumberButton)
            {
                cardnum_input.setText(Integer.toString(n));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void DisableMouse2(MouseEvent event) {
        cardnum_input.setEditable(false);
    }

    @FXML
    private Label SuccesPatient;

    ObservableList<Patient> listPatientsToUI(){
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){
            List<Patient> listOfPatients = aDAO.getPatients();

            for (Patient p : listOfPatients){
                patients.add(p);
                System.out.println(p.toString());
            }

        }catch (Exception e){

        }

        return patients;
    }

    @FXML
    public void PatientRemoveButtonPushed(ActionEvent event){

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){
            if(cardnumToRemove.getText().isEmpty()){
                Message("A törléshez ki kell tölteni a kartonszám mezőt!"); //NEM JO HELYEN JÖN A MESSAGE IMI
                return;
            }

            if (!cardnumToRemove.getText().matches("[0-9]+")){
                Message("A kartonszám csak számot tartalmaz!"); //NEM JO HELYEN JÖN A MESSAGE IMI
                return;
            }

            if (!aDAO.cardnumberAlreadyExists(Integer.parseInt(cardnumToRemove.getText()))){
                Message("A kartonszám nem létezik!"); //NEM JO HELYEN JÖN A MESSAGE IMI
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("BIZTOSAN TÖLRI?"); // IGEN vagy NEM gui?
            alert.showAndWait();

            List<Patient> patients = aDAO.getPatients();

            for (Patient p : patients){
                if (p.getCardNumber() == Integer.parseInt(cardnumToRemove.getText())){
                    aDAO.deletePatient(p);
                }
            }

            patientsTable.setItems(listPatientsToUI());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

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
            if(taj_input.getText().length() != 9){
                Message("A tajszám 9 számot tartalmaz!");
                return;
            }
            if(!isValidBirthDate(birthdate_input.getText())){
                Message("Helytelen születési dátum!\nHelyes formátum: ÉÉÉÉ-HH-NN");
            }

            Patient patient = new Patient();
            patient.setName(name_input.getText());
            patient.setCity(city_input.getText());
            patient.setBirthDate(birthdate_input.getText());
            patient.setCardNumber(Integer.parseInt(cardnum_input.getText()));
            patient.setDiagnose(diagnose_input.getText());
            patient.setNameOfMother(mothersname_input.getText());
            patient.setStreetNumber(housenum_input.getText());
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

            MessageSuccess("A beteg sikeresen felvételre került!");
            patientsTable.setItems(listPatientsToUI());

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
        taj_input.setText("");  //9 SZÁM
        cardnum_input.setText("");
        diagnose_input.setText("");
        birthdate_input.setText(""); //YYYY-MM-DD FORMÁTUM
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

    private void MessageSuccess(String messageSuccess){
        SuccesPatient.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(116, 214, 137, 0.8);\n" +
                "\t-fx-border-color: green;\n" +
                "\t-fx-border-width:2px;");
        SuccesPatient.setText(messageSuccess);
    }

    private boolean isValidBirthDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate birthdate = LocalDate.parse(date, formatter);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
