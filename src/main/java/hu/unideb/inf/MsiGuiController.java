package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAPatientDAO;
import hu.unideb.inf.Modell.Model;
import hu.unideb.inf.Modell.Patient;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    private TextField city_input;

    @FXML
    private TextField street_input;

    @FXML
    private TextField housenum_input;

    @FXML
    private TextField zipcode_input;

    @FXML
    private TextField name_modify;

    @FXML
    private TextField cardnum_modify;

    @FXML
    private TextField mothersname_modify;

    @FXML
    private TextField taj_modify;

    @FXML
    private TextField birthdate_modify;

    @FXML
    private TextArea diagnose_modify;

    @FXML
    private TextField zipcode_modify;

    @FXML
    private TextField city_modify;

    @FXML
    private TextField street_modify;

    @FXML
    private TextField housenum_modify;

    @FXML
    private TextField searchElementInput;

    @FXML
    private RadioButton radioMale;

    @FXML
    private RadioButton radioFemale;

    @FXML
    private Label foundElementsNumber;

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


    @FXML
    private ChoiceBox<String> myChoiceBox;

    private final String[] searchelements={"Név","Város","Kartonszám","TAJ/Azonosító"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(searchelements);

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
    private Label SuccesPatient,RemoveSuccesPatient,ModifyMessage;

    ObservableList<Patient> listPatientsForSearching(String elementToSearch, String searchBarText){
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){
            List<Patient> listOfPatients = aDAO.getPatients();

            for (Patient p : listOfPatients){
                if (elementToSearch.contains("Név")){
                    if (p.getName().contains(searchBarText)){
                        patients.add(p);
                    }
                }
                else if(elementToSearch.matches("Kartonszám")){
                    if (Integer.parseInt(searchBarText) == p.getCardNumber()){
                        patients.add(p);
                    }
                }
                else if(elementToSearch.contains("Város")){
                    if (p.getCity().matches(searchBarText)){
                        patients.add(p);
                    }
                }
                else if(elementToSearch.matches("TAJ/Azonosító")){
                    if (Integer.parseInt(searchBarText) == p.getSocialInsuranceId()){
                        patients.add(p);
                    }
                }
            }

        }catch (Exception e){

        }

        return patients;
    }

    ObservableList<Patient> listPatientsToUI(){
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){
            List<Patient> listOfPatients = aDAO.getPatients();

            for (Patient p : listOfPatients){
                patients.add(p);
            }

        }catch (Exception e){

        }

        return patients;
    }

    @FXML
    public void PatientRemoveButtonPushed(ActionEvent event){

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){
            if(cardnumToRemove.getText().isEmpty()){
                SearchPatientFailed("A törléshez ki kell tölteni a kartonszám mezőt!");
                return;
            }

            if (!cardnumToRemove.getText().matches("[0-9]+")){
                SearchPatientFailed("A kartonszám csak számot tartalmaz!");
                return;
            }

            if (!aDAO.cardnumberAlreadyExists(Integer.parseInt(cardnumToRemove.getText()))){
                SearchPatientFailed("A kartonszám nem létezik!");
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
            if (!zipcode_input.getText().matches("[0-9]+") || !taj_input.getText().matches("[0-9]+") || !housenum_input.getText().matches("[0-9]+")){
                Message("Az irányítószám, házszám és tajszám mezők\n csak számokat tartalmazhatnak!");
                return;
            }
            if(taj_input.getText().length() != 9){
                Message("A tajszám 9 számot tartalmaz!");
                return;
            }
            if(!isValidBirthDate(birthdate_input.getText())){
                Message("Helytelen születési dátum!\nHelyes formátum: ÉÉÉÉ-HH-NN");
                return;
            }
            if (!name_input.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !mothersname_input.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !city_input.getText().matches("[[a-zA-Z]+ÉÁÖÜÓŐÚŰÍéáöüóőúűí]+") || !street_input.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+"))
            {
                Message("A Név, Anyja neve, Város és Utca mezők\n csak betűket tartalmazhatnak!");
                return;
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

    }

    @FXML private Pane foundElementsNumberID;
    @FXML
    public void SearchButtonPushed(ActionEvent event){

        String elementToSearch = searchElementInput.getText();
        String choiceBoxValue = myChoiceBox.getValue();
        if (elementToSearch.isEmpty()){
            SearchPatientFailed("Írjon be keresendő szöveget!");
            foundElementsNumberID.setOpacity(0);
            return;
        }
        if (choiceBoxValue == null){
            SearchPatientFailed("Válassza ki mi alapján szeretne keresni!");
            foundElementsNumberID.setOpacity(0);
            return;
        }

        int foundPatientsLength = 0;

        if (choiceBoxValue.matches("Név")){
            if (!elementToSearch.matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+")){
                SearchPatientFailed("A név csak betűt tartalmazhat!");
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("Név",elementToSearch));
            foundPatientsLength = listPatientsForSearching("Név",elementToSearch).size();
        }else if(choiceBoxValue.matches("Kartonszám")){
            if (!elementToSearch.matches("[0-9]+")){
                SearchPatientFailed("A kartonszám csak számot tartalmazhat!");
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("Kartonszám",elementToSearch));
            foundPatientsLength = listPatientsForSearching("Kartonszám",elementToSearch).size();
        }else if(choiceBoxValue.matches("Város")){
            if (!elementToSearch.matches("[[a-zA-Z]+ÉÁÖÜÓŐÚŰÍéáöüóőúűí]+")){
                SearchPatientFailed("A város csak betűt tartalmazhat!");
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("Város",elementToSearch));
            foundPatientsLength = listPatientsForSearching("Város",elementToSearch).size();
        }else if(choiceBoxValue.matches("TAJ/Azonosító")){
            if (!elementToSearch.matches("[0-9]+") || elementToSearch.length() !=9){
                SearchPatientFailed("A tajszám csak számot tartalmazhat,\n és 9 szám lehet!");
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("TAJ/Azonosító",elementToSearch));
            foundPatientsLength = listPatientsForSearching("TAJ/Azonosító",elementToSearch).size();
        }
        foundElementsNumberID.setOpacity(1);
        foundElementsNumber.setText("" + foundPatientsLength);
        SearchPatientSuccess("Sikeres Keresés");
        searchElementInput.setText("");
    }

    @FXML
    public void ListAllPatientPushed(ActionEvent event){
        patientsTable.setItems(listPatientsToUI());
        foundElementsNumberID.setOpacity(0);
        SearchPatientSuccess("Betegek listázva.");
    }

    @FXML private Label SelectPatientLabel,DiagnoseLabel,Diagnoses;
    @FXML private Button ConfirmButton_SelectPatientLabel;

    @FXML
    public void clickTable(MouseEvent event){
        if (event.getClickCount() == 1){
            SelectPatientLabel.setText("");
            DiagnoseLabel.setText("Diagnózis:");
            Diagnoses.setText(patientsTable.getSelectionModel().getSelectedItem().getDiagnose());
            ConfirmButton_SelectPatientLabel.setOpacity(1);
            cardnum_modify.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getCardNumber()));
            name_modify.setText(patientsTable.getSelectionModel().getSelectedItem().getName());
            mothersname_modify.setText(patientsTable.getSelectionModel().getSelectedItem().getNameOfMother());
            taj_modify.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getSocialInsuranceId()));
            birthdate_modify.setText(patientsTable.getSelectionModel().getSelectedItem().getBirthDate());
            zipcode_modify.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getZipCode()));
            city_modify.setText(patientsTable.getSelectionModel().getSelectedItem().getCity());
            street_modify.setText(patientsTable.getSelectionModel().getSelectedItem().getStreet());
            housenum_modify.setText(patientsTable.getSelectionModel().getSelectedItem().getStreetNumber());
            diagnose_modify.setText(patientsTable.getSelectionModel().getSelectedItem().getDiagnose());
        }
    }

    @FXML
    public void modifyButtonPushed(ActionEvent event){
        if (!isAllFilledModify()){
            ModifyMessageFailed("Minden mezőt kötelező kitölteni!");
            return;
        }

        if (!zipcode_modify.getText().matches("[0-9]+") || !taj_modify.getText().matches("[0-9]+") || !housenum_modify.getText().matches("[0-9]+")){
            ModifyMessageFailed("Az irányítószám, házszám és tajszám\n mezők csak számokat tartalmazhatnak!");
            return;
        }
        if(taj_modify.getText().length() != 9){
            ModifyMessageFailed("A tajszám 9 számot tartalmaz!");
            return;
        }
        if(!isValidBirthDate(birthdate_modify.getText())){
            ModifyMessageFailed("Helytelen születési dátum!\nHelyes formátum: ÉÉÉÉ-HH-NN");
            return;
        }
        if (!name_modify.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !mothersname_modify.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !city_modify.getText().matches("[[a-zA-Z]+ÉÁÖÜÓŐÚŰÍéáöüóőúűí]+") || !street_modify.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+"))
        {
            ModifyMessageFailed("A Név, Anyja neve, Város és Utca mezők\n csak betűket tartalmazhatnak!");
            return;
        }


        try(JPAPatientDAO aDAO = new JPAPatientDAO()){

            List<Patient> patients = aDAO.getPatients();
            boolean modify = false;
            for (Patient p : patients){
                if (p.getCardNumber() == Integer.parseInt(cardnum_modify.getText())){

                    if (!(p.getName().matches(name_modify.getText()))) {
                        p.setName(name_modify.getText());
                        modify = true;
                    }

                    if (!(p.getNameOfMother().matches(mothersname_modify.getText()))) {
                        p.setNameOfMother(mothersname_modify.getText());
                        modify = true;
                    }

                    if (p.getSocialInsuranceId() != Integer.parseInt(taj_modify.getText())) {
                        p.setSocialInsuranceId(Integer.parseInt(taj_modify.getText()));
                        modify = true;
                    }

                    if (!(p.getBirthDate().matches(birthdate_modify.getText()))) {
                        p.setBirthDate(birthdate_modify.getText());
                        modify = true;
                    }

                    if (!(p.getDiagnose().matches(diagnose_modify.getText()))) {
                        p.setDiagnose(diagnose_modify.getText());
                        modify = true;
                    }

                    if (p.getZipCode() != Integer.parseInt(zipcode_modify.getText())) {
                        p.setZipCode(Integer.parseInt(zipcode_modify.getText()));
                        modify = true;
                    }

                    if (!(p.getCity().matches(city_modify.getText()))) {
                        p.setCity(city_modify.getText());
                        modify = true;
                    }

                    if (!(p.getStreet().matches(street_modify.getText()))) {
                        p.setStreet(street_modify.getText());
                        modify = true;
                    }

                    if (!(p.getStreetNumber().matches(housenum_modify.getText()))) {
                        p.setStreetNumber(housenum_modify.getText());
                        modify = true;
                    }

                    if (!modify){
                        ModifyMessageSuccess("Nem történt módosítás!");
                        clearTexts();
                        return;
                    }

                    aDAO.updatePatient(p);
                }
            }

            patientsTable.setItems(listPatientsToUI());
            ModifyMessageSuccess("Sikeres módosítás!");
            clearTexts();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private boolean isAllFilledModify() {
        if(name_modify.getText() == null || name_modify.getText().trim().isEmpty()) return false;
        if(city_modify.getText() == null || city_modify.getText().trim().isEmpty()) return false;
        if(zipcode_modify.getText() == null || zipcode_modify.getText().trim().isEmpty()) return false;
        if(street_modify.getText() == null || street_modify.getText().trim().isEmpty()) return false;
        if(taj_modify.getText() == null || taj_modify.getText().trim().isEmpty()) return false;
        if(cardnum_modify.getText() == null || cardnum_modify.getText().trim().isEmpty()) return false;
        if(diagnose_modify.getText() == null || diagnose_modify.getText().trim().isEmpty()) return false;
        if(birthdate_modify.getText() == null || birthdate_modify.getText().trim().isEmpty()) return false;
        if(mothersname_modify.getText() == null || mothersname_modify.getText().trim().isEmpty()) return false;
        if(housenum_modify.getText() == null || housenum_modify.getText().trim().isEmpty()) return false;

        return true;

    }


    public void ConfirmButton_SelectPatientLabel_Action(ActionEvent event){
        SelectPatientLabel.setText("A legkérdezéshez, válasszon ki egy beteget!");
        DiagnoseLabel.setText("");
        Diagnoses.setText("");
        ConfirmButton_SelectPatientLabel.setOpacity(0);
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
        //modify
        name_modify.setText("");
        city_modify.setText("");
        zipcode_modify.setText("");
        street_modify.setText("");
        taj_modify.setText("");  //9 SZÁM
        cardnum_modify.setText("");
        diagnose_modify.setText("");
        birthdate_modify.setText(""); //YYYY-MM-DD FORMÁTUM
        mothersname_modify.setText("");
        housenum_modify.setText("");

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

    private void SearchPatientSuccess(String messageRemove){
        RemoveSuccesPatient.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(116, 214, 137, 0.8);\n" +
                "\t-fx-border-color: green;\n" +
                "\t-fx-border-width:2px;");
        RemoveSuccesPatient.setText(messageRemove);
    }

    private void SearchPatientFailed(String messageRemove){
        RemoveSuccesPatient.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                "\t-fx-border-color: red;\n" +
                "\t-fx-border-width:2px;");
        RemoveSuccesPatient.setText(messageRemove);
    }

    private void MessageSuccess(String messageSuccess){
        SuccesPatient.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(116, 214, 137, 0.8);\n" +
                "\t-fx-border-color: green;\n" +
                "\t-fx-border-width:2px;");
        SuccesPatient.setText(messageSuccess);
    }
    private void ModifyMessageSuccess(String modifyMessage){
        ModifyMessage.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(116, 214, 137, 0.8);\n" +
                "\t-fx-border-color: green;\n" +
                "\t-fx-border-width:2px;");
        ModifyMessage.setText(modifyMessage);
    }
    private void ModifyMessageFailed(String modifyMessage){
        ModifyMessage.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                "\t-fx-border-color: red;\n" +
                "\t-fx-border-width:2px;");
        ModifyMessage.setText(modifyMessage);
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
