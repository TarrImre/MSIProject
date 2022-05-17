package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAPatientDAO;
import hu.unideb.inf.DAO.JPAUserDAO;
import hu.unideb.inf.Modell.Model;
import hu.unideb.inf.Modell.Patient;
import hu.unideb.inf.Modell.User;
import javafx.animation.FillTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

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
    private TextField nameInputField;

    @FXML
    private TextField cardnumInputField;

    @FXML
    private TextField motherNameInputField;

    @FXML
    private TextField insuranceIdInputField;

    @FXML
    private TextField birthdateInputField;

    @FXML
    private TextArea diagnoseInputField;

    @FXML
    private TextField cityInputField;

    @FXML
    private TextField streetInputField;

    @FXML
    private TextField streetNumberInputField;

    @FXML
    private TextField zipcodeInputField;

    @FXML
    private TextField modiyfNameInputField;

    @FXML
    private TextField modifyCardnumInputField;

    @FXML
    private TextField modifyMotherNameInputField;

    @FXML
    private TextField modifyInsuranceIdInputField;

    @FXML
    private TextField modifyBirthDateInputField;

    @FXML
    private TextArea modifyDiagnoseInputField;

    @FXML
    private TextField modifyZipcodeInputField;

    @FXML
    private TextField modifyCityInputField;

    @FXML
    private TextField modifyStreetInputField;

    @FXML
    private TextField modifyStreetNumberInputField;

    @FXML
    private TextField searchElementInput;

    @FXML
    private RadioButton radioMale;

    @FXML
    private RadioButton radioFemale;

    @FXML
    private Label foundElementsNumber;

    @FXML Pane exitOverlay;

    @FXML Button ExitOverlayButtonShow,ExitOverlayButtonHide;

    @FXML private Pane topPane;

    @FXML private AnchorPane parent;

    @FXML private Pane overlay;

    @FXML private Button overlayButton, overlayHideButton;

    @FXML private Pane themeOverlay;

    @FXML private Button themeOverlayButton, themeOverlayButtonHide;

    @FXML private Pane overlay_help;

    @FXML private Button helpButton,helpButtonHide;

    @FXML private ChoiceBox<String> myChoiceBox;

    @FXML Button RandomNumberButton;

    @FXML private Pane foundElementsNumberID;

    @FXML private Label SelectPatientLabel,DiagnoseLabel,Diagnoses;

    @FXML private Button ConfirmButton_SelectPatientLabel;

    private final String[] searchElements ={"Név","Város","Kartonszám","TAJ/Azonosító"};

    private double x,y;

    public void setModel(Model model) {
        this.model = model;
    }

    public void init(Stage stage){

        topPane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        topPane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);

        });
        LoginAsName.setText(LoginController.GlobalUsername);
    }

    @FXML
    void Github(ActionEvent event) throws URISyntaxException, IOException{
        Desktop.getDesktop().browse(new URI("https://github.com/DMartin20/MSIProject"));
    }
    @FXML
    void Trello(ActionEvent event) throws URISyntaxException, IOException{
        Desktop.getDesktop().browse(new URI("https://trello.com/b/HSbKUsTV/msi-projekt"));
    }



    @FXML private Label LoginAsName;


    @FXML
    void themeWithRadius(ActionEvent event) {
        parent.getStylesheets().remove("/fxml/withoutradius.css");
        parent.getStylesheets().add("/fxml/withradius.css");
        setRadiusForUser(true);
    }

    @FXML
    void themeWithoutRadius(ActionEvent event){
        parent.getStylesheets().remove("/fxml/withradius.css");
        parent.getStylesheets().add("/fxml/withoutradius.css");
        setRadiusForUser(false);
    }

    public void WinterButton(ActionEvent event){
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/autumn.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/light.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/dark.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/razer.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/green.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/pink.css").toExternalForm());
        parent.getScene().getStylesheets().add(getClass().getResource("/fxml/Winter.css").toExternalForm());
        setThemeForUser("Winter");
    }

    public void AutumnButton(ActionEvent event){
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/Winter.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/light.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/dark.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/razer.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/green.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/pink.css").toExternalForm());
        parent.getScene().getStylesheets().add(getClass().getResource("/fxml/autumn.css").toExternalForm());
        setThemeForUser("autumn");
    }

    public void LightButton(ActionEvent event){
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/autumn.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/Winter.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/dark.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/razer.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/green.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/pink.css").toExternalForm());
        parent.getScene().getStylesheets().add(getClass().getResource("/fxml/light.css").toExternalForm());
        setThemeForUser("light");
    }

    public void DarkButton(ActionEvent event){
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/autumn.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/light.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/Winter.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/razer.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/green.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/pink.css").toExternalForm());
        parent.getScene().getStylesheets().add(getClass().getResource("/fxml/dark.css").toExternalForm());
        setThemeForUser("dark");
    }

    public void RazerButton(ActionEvent event){
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/autumn.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/light.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/dark.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/Winter.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/green.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/pink.css").toExternalForm());
        parent.getScene().getStylesheets().add(getClass().getResource("/fxml/razer.css").toExternalForm());
        setThemeForUser("razer");
    }

    public void GreenButton(ActionEvent event){
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/autumn.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/light.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/dark.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/razer.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/Winter.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/pink.css").toExternalForm());
        parent.getScene().getStylesheets().add(getClass().getResource("/fxml/green.css").toExternalForm());
        setThemeForUser("green");
    }

    public void PinkButton(ActionEvent event){
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/autumn.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/light.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/dark.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/razer.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/green.css").toExternalForm());
        parent.getScene().getStylesheets().remove(getClass().getResource("/fxml/Winter.css").toExternalForm());
        parent.getScene().getStylesheets().add(getClass().getResource("/fxml/pink.css").toExternalForm());
        setThemeForUser("pink");
    }

    @FXML
    void overlayAction(ActionEvent event) {
        if (event.getSource() == overlayButton)
        {
            overlay.toFront();
        }
    }

    @FXML
    void overlayActionHide(ActionEvent event) {
        if (event.getSource() == overlayHideButton)
        {
            overlay.toBack();
        }
    }

    @FXML
    void ExitOverlayButtonAction(ActionEvent event) {
        if (event.getSource() == ExitOverlayButtonShow)
        {
            exitOverlay.toFront();
        }
    }

    @FXML
    void ExitOverlayButtonActionHide2(ActionEvent event) {
        if (event.getSource() == ExitOverlayButtonHide)
        {
            exitOverlay.toBack();
        }
    }

    //THEME

    @FXML
    void themeOverlayAction(ActionEvent event) {

        if (event.getSource() == themeOverlayButton)
        {
            themeOverlay.toFront();
        }

    }

    @FXML
    void themeOverlayActionHide(ActionEvent event) {
        if (event.getSource() == themeOverlayButtonHide)
        {
            themeOverlay.toBack();
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
        backToLoginInterfaceEvent(event);
    }

    @FXML
    private void backToLoginInterfaceEvent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginpage.fxml"));
        Parent root5 = (Parent) fxmlLoader.load();
        Stage stage5 = new Stage();
        stage5.initModality(Modality.APPLICATION_MODAL);
        stage5.initStyle(StageStyle.UNDECORATED);
        stage5.setTitle("MSI Projekt");
        stage5.setScene(new Scene(root5));
        stage5.getIcons().add(new Image("/fxml/img/windowsicon.png"));
        ((LoginController)fxmlLoader.getController()).init(stage5);
        Close(event);
        stage5.show();
    }

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
        myChoiceBox.getItems().addAll(searchElements);
        try {
            setRandomPatientDataToList();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                cardnumInputField.setText(Integer.toString(n));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML void DisableMouse2(MouseEvent event) {
        cardnumInputField.setEditable(false);
    }


    ObservableList<Patient> listPatientsForSearching(String elementToSearch, String searchBarText){
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){
            List<Patient> listOfPatients = aDAO.getPatients();

            for (Patient p : listOfPatients){
                if (elementToSearch.contains("Név")){
                    if (p.getName().toLowerCase().contains(searchBarText.toLowerCase())){
                        patients.add(p);
                    }
                }
                else if(elementToSearch.matches("Kartonszám")){
                    if (Integer.parseInt(searchBarText) == p.getCardNumber()){
                        patients.add(p);
                    }
                }
                else if(elementToSearch.contains("Város")){
                    if (p.getCity().toLowerCase().contains(searchBarText.toLowerCase())){
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

    @FXML private Label SuccessRemove;

    @FXML
    public void PatientRemoveButtonPushed(ActionEvent event){

        if(cardnumToRemove.getText().isEmpty()){
            SuccessRemove.setText("A törléshez töltse ki a mezőt!");
            return;
        }

        if (!cardnumToRemove.getText().matches("[0-9]+")){
            SuccessRemove.setText("A kartonszám csak számot tartalmaz!");
            return;
        }

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){

            if (!aDAO.cardnumberAlreadyExists(Integer.parseInt(cardnumToRemove.getText()))){
                SuccessRemove.setText("A kartonszám nem létezik!");
                return;
            }

            List<Patient> patients = aDAO.getPatients();

            for (Patient p : patients){
                if (p.getCardNumber() == Integer.parseInt(cardnumToRemove.getText())){
                    SuccessRemove.setText("Sikeresen törölve!");
                    aDAO.deletePatient(p);
                }
            }

            cardnumToRemove.setText("");

            patientsTable.setItems(listPatientsToUI());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML public void PatientRegisterButtonPushed(ActionEvent event) {

        if (!isAllFilled()){
            overlayErrorMessage("Minden mezőt kötelező kitölteni!",0);
            return;
        }
        if (!zipcodeInputField.getText().matches("[0-9]+") || !insuranceIdInputField.getText().matches("[0-9]+") || !streetNumberInputField.getText().matches("[0-9]+")){
            overlayErrorMessage("Az irányítószám, házszám és tajszám mezők\n csak számokat tartalmazhatnak!",0);
            return;
        }
        if(insuranceIdInputField.getText().length() != 9){
            overlayErrorMessage("A tajszám 9 számot tartalmaz!",0);
            return;
        }
        if(!isValidBirthDate(birthdateInputField.getText())){
            overlayErrorMessage("Helytelen születési dátum!\nHelyes formátum: ÉÉÉÉ-HH-NN",0);
            return;
        }
        if (!nameInputField.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !motherNameInputField.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !cityInputField.getText().matches("[[a-zA-Z]+ÉÁÖÜÓŐÚŰÍéáöüóőúűí]+") || !streetInputField.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+"))
        {
            overlayErrorMessage("A Név, Anyja neve, Város és Utca mezők\n csak betűket tartalmazhatnak!",0);
            return;
        }

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){

            Patient patient = new Patient();
            patient.setName(nameInputField.getText());
            patient.setCity(cityInputField.getText());
            patient.setBirthDate(birthdateInputField.getText());
            patient.setCardNumber(Integer.parseInt(cardnumInputField.getText()));
            patient.setDiagnose(diagnoseInputField.getText());
            patient.setNameOfMother(motherNameInputField.getText());
            patient.setStreetNumber(streetNumberInputField.getText());
            patient.setZipCode(Integer.parseInt(zipcodeInputField.getText()));
            patient.setStreet(streetInputField.getText());
            patient.setSocialInsuranceId(Integer.parseInt(insuranceIdInputField.getText()));

            if (radioMale.isSelected()){
                patient.setGender("MALE");
            }else {
                patient.setGender("FEMALE");
            }

            aDAO.savePatient(patient);

            clearTexts();

            overlayErrorMessage("A beteg sikeresen felvételre került!",1);
            patientsTable.setItems(listPatientsToUI());

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void SearchButtonPushed(ActionEvent event){

        String elementToSearch = searchElementInput.getText();
        String choiceBoxValue = myChoiceBox.getValue();

        if (elementToSearch.isEmpty()){
            overlayErrorMessage("Írjon be keresendő szöveget!",0);
            foundElementsNumberID.setOpacity(0);
            return;
        }
        if (choiceBoxValue == null){
            overlayErrorMessage("Válassza ki mi alapján szeretne keresni!",0);
            foundElementsNumberID.setOpacity(0);
            return;
        }

        int foundPatientsLength = 0;

        if (choiceBoxValue.matches("Név")){
            if (!elementToSearch.matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+")){
                overlayErrorMessage("A név csak betűt tartalmazhat!",0);
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("Név",elementToSearch));
            foundPatientsLength = listPatientsForSearching("Név",elementToSearch).size();
        }else if(choiceBoxValue.matches("Kartonszám")){
            if (!elementToSearch.matches("[0-9]+")){
                overlayErrorMessage("A kartonszám csak számot tartalmazhat!",0);
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("Kartonszám",elementToSearch));
            foundPatientsLength = listPatientsForSearching("Kartonszám",elementToSearch).size();
        }else if(choiceBoxValue.matches("Város")){
            if (!elementToSearch.matches("[[a-zA-Z]+ÉÁÖÜÓŐÚŰÍéáöüóőúűí]+")){
                overlayErrorMessage("A város csak betűt tartalmazhat!",0);
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("Város",elementToSearch));
            foundPatientsLength = listPatientsForSearching("Város",elementToSearch).size();
        }else if(choiceBoxValue.matches("TAJ/Azonosító")){
            if (!elementToSearch.matches("[0-9]+") || elementToSearch.length() !=9){
                overlayErrorMessage("A tajszám csak számot tartalmazhat,\n és 9 szám lehet!",0);
                foundElementsNumberID.setOpacity(0);
                return;
            }
            patientsTable.setItems(listPatientsForSearching("TAJ/Azonosító",elementToSearch));
            foundPatientsLength = listPatientsForSearching("TAJ/Azonosító",elementToSearch).size();
        }
        foundElementsNumberID.setOpacity(1);
        foundElementsNumber.setText("" + foundPatientsLength);
        overlayErrorMessage("Sikeres Keresés",1);
        searchElementInput.setText("");
    }

    @FXML
    public void ListAllPatientButtonPushed(ActionEvent event){
        patientsTable.setItems(listPatientsToUI());
        foundElementsNumberID.setOpacity(0);
        overlayErrorMessage("Betegek listázva.",1);
    }

    @FXML private Label Name_shortinfo, Cardnum_shortinfo, Mname_shortinfo, Id_shortinfo;

    @FXML
    public void clickTable(MouseEvent event){
        if (event.getClickCount() == 1){
            SelectPatientLabel.setText("");
            DiagnoseLabel.setText("Diagnózis:");
            Name_shortinfo.setText(patientsTable.getSelectionModel().getSelectedItem().getName());
            Cardnum_shortinfo.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getCardNumber()));
            Mname_shortinfo.setText(patientsTable.getSelectionModel().getSelectedItem().getNameOfMother());
            Id_shortinfo.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getSocialInsuranceId()));
            Diagnoses.setText(patientsTable.getSelectionModel().getSelectedItem().getDiagnose());
            ConfirmButton_SelectPatientLabel.setOpacity(1);
            modifyCardnumInputField.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getCardNumber()));
            modiyfNameInputField.setText(patientsTable.getSelectionModel().getSelectedItem().getName());
            modifyMotherNameInputField.setText(patientsTable.getSelectionModel().getSelectedItem().getNameOfMother());
            modifyInsuranceIdInputField.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getSocialInsuranceId()));
            modifyBirthDateInputField.setText(patientsTable.getSelectionModel().getSelectedItem().getBirthDate());
            modifyZipcodeInputField.setText(String.valueOf(patientsTable.getSelectionModel().getSelectedItem().getZipCode()));
            modifyCityInputField.setText(patientsTable.getSelectionModel().getSelectedItem().getCity());
            modifyStreetInputField.setText(patientsTable.getSelectionModel().getSelectedItem().getStreet());
            modifyStreetNumberInputField.setText(patientsTable.getSelectionModel().getSelectedItem().getStreetNumber());
            modifyDiagnoseInputField.setText(patientsTable.getSelectionModel().getSelectedItem().getDiagnose());
        }
    }
    @FXML private Pane overlayError;
    @FXML private Button overlayErrorHide_Button;
    @FXML private Label errormsg,errormsgBG;
    @FXML
    void overlayErrorHide_Action(ActionEvent event) {
        if (event.getSource() == overlayErrorHide_Button)
        {
            overlayError.toBack();
        }
    }


    void overlayErrorMessage(String errormessage, int num){
        if(num == 0){
            errormsgBG.setStyle(""+
                    "-fx-background-color:#eda4a4;\n"+
                    "\t-fx-opacity:1;");
            errormsg.setStyle(""+
                    "-fx-font-weight:bold;\n" +
                    //RED
                    "\t-fx-text-fill: black;\n" +
                    "\t-fx-font-size: 14;\n"+
                    "\t-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 4, 0, 0, 0);\n");
            errormsg.setText(errormessage);
            overlayError.toFront();
        }
        else{
            errormsgBG.setStyle(""+
                    "-fx-background-color:#a4edab;\n"+
                    "\t-fx-opacity:1;");
            errormsg.setStyle(""+
                    "-fx-font-weight:bold;\n" +
                    //GREEN
                    "\t-fx-text-fill: black;\n" +
                    "\t-fx-font-size: 14;\n"+
                    "\t-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 4, 0, 0, 0);\n");
            errormsg.setText(errormessage);
            overlayError.toFront();
        }
    }


    @FXML
    public void modifyButtonPushed(ActionEvent event){
        if (!isAllFilledModify()){
            overlayErrorMessage("Minden mezőt kötelező kitölteni!",0);
            return;
        }

        if (!modifyZipcodeInputField.getText().matches("[0-9]+") || !modifyInsuranceIdInputField.getText().matches("[0-9]+") || !modifyStreetNumberInputField.getText().matches("[0-9]+")){
            overlayErrorMessage("Az irányítószám, házszám és tajszám\n mezők csak számokat tartalmazhatnak!",0);
            return;
        }
        if(modifyInsuranceIdInputField.getText().length() != 9){
            overlayErrorMessage("A tajszám 9 számot tartalmaz!",0);
            return;
        }
        if(!isValidBirthDate(modifyBirthDateInputField.getText())){
            overlayErrorMessage("Helytelen születési dátum!\nHelyes formátum: ÉÉÉÉ-HH-NN",0);
            return;
        }
        if (!modiyfNameInputField.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !modifyMotherNameInputField.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+") || !modifyCityInputField.getText().matches("[[a-zA-Z]+ÉÁÖÜÓŐÚŰÍéáöüóőúűí]+") || !modifyStreetInputField.getText().matches("[/^[a-zA-ZáéíöüóőúűÉÁÖÜÓŐÚŰÍ ,.'-]+$/u]+"))
        {
            overlayErrorMessage("A Név, Anyja neve, Város és Utca mezők\n csak betűket tartalmazhatnak!",0);
            return;
        }

        try(JPAPatientDAO aDAO = new JPAPatientDAO()){

            List<Patient> patients = aDAO.getPatients();
            boolean modify = false;
            for (Patient p : patients){
                if (p.getCardNumber() == Integer.parseInt(modifyCardnumInputField.getText())){

                    if (!(p.getName().matches(modiyfNameInputField.getText()))) {
                        p.setName(modiyfNameInputField.getText());
                        modify = true;
                    }

                    if (!(p.getNameOfMother().matches(modifyMotherNameInputField.getText()))) {
                        p.setNameOfMother(modifyMotherNameInputField.getText());
                        modify = true;
                    }

                    if (p.getSocialInsuranceId() != Integer.parseInt(modifyInsuranceIdInputField.getText())) {
                        p.setSocialInsuranceId(Integer.parseInt(modifyInsuranceIdInputField.getText()));
                        modify = true;
                    }

                    if (!(p.getBirthDate().matches(modifyBirthDateInputField.getText()))) {
                        p.setBirthDate(modifyBirthDateInputField.getText());
                        modify = true;
                    }

                    if (!(p.getDiagnose().matches(modifyDiagnoseInputField.getText()))) {
                        p.setDiagnose(modifyDiagnoseInputField.getText());
                        modify = true;
                    }

                    if (p.getZipCode() != Integer.parseInt(modifyZipcodeInputField.getText())) {
                        p.setZipCode(Integer.parseInt(modifyZipcodeInputField.getText()));
                        modify = true;
                    }

                    if (!(p.getCity().matches(modifyCityInputField.getText()))) {
                        p.setCity(modifyCityInputField.getText());
                        modify = true;
                    }

                    if (!(p.getStreet().matches(modifyStreetInputField.getText()))) {
                        p.setStreet(modifyStreetInputField.getText());
                        modify = true;
                    }

                    if (!(p.getStreetNumber().matches(modifyStreetNumberInputField.getText()))) {
                        p.setStreetNumber(modifyStreetNumberInputField.getText());
                        modify = true;
                    }

                    if (!modify){
                        overlayErrorMessage("Nem történt módosítás!",1);
                        clearTexts();
                        return;
                    }

                    aDAO.updatePatient(p);
                }
            }

            patientsTable.setItems(listPatientsToUI());
            overlayErrorMessage("Sikeres módosítás!",1);
            clearTexts();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private boolean isAllFilledModify() {
        if(modiyfNameInputField.getText() == null || modiyfNameInputField.getText().trim().isEmpty()) return false;
        if(modifyCityInputField.getText() == null || modifyCityInputField.getText().trim().isEmpty()) return false;
        if(modifyZipcodeInputField.getText() == null || modifyZipcodeInputField.getText().trim().isEmpty()) return false;
        if(modifyStreetInputField.getText() == null || modifyStreetInputField.getText().trim().isEmpty()) return false;
        if(modifyInsuranceIdInputField.getText() == null || modifyInsuranceIdInputField.getText().trim().isEmpty()) return false;
        if(modifyCardnumInputField.getText() == null || modifyCardnumInputField.getText().trim().isEmpty()) return false;
        if(modifyDiagnoseInputField.getText() == null || modifyDiagnoseInputField.getText().trim().isEmpty()) return false;
        if(modifyBirthDateInputField.getText() == null || modifyBirthDateInputField.getText().trim().isEmpty()) return false;
        if(modifyMotherNameInputField.getText() == null || modifyMotherNameInputField.getText().trim().isEmpty()) return false;
        if(modifyStreetNumberInputField.getText() == null || modifyStreetNumberInputField.getText().trim().isEmpty()) return false;

        return true;

    }

    public void ConfirmButton_SelectPatientLabel_Action(ActionEvent event){
        SelectPatientLabel.setText("A lekérdezéshez válasszon ki egy beteget!");
        DiagnoseLabel.setText("");
        Diagnoses.setText("");
        Name_shortinfo.setText("");
        Cardnum_shortinfo.setText("");
        Mname_shortinfo.setText("");
        Id_shortinfo.setText("");
        ConfirmButton_SelectPatientLabel.setOpacity(0);
    }

    private void clearTexts() {
        nameInputField.setText("");
        cityInputField.setText("");
        zipcodeInputField.setText("");
        streetInputField.setText("");
        insuranceIdInputField.setText("");
        cardnumInputField.setText("");
        diagnoseInputField.setText("");
        birthdateInputField.setText("");
        motherNameInputField.setText("");
        streetNumberInputField.setText("");
        radioMale.setSelected(false);
        radioFemale.setSelected(false);
        //modify
        modiyfNameInputField.setText("");
        modifyCityInputField.setText("");
        modifyZipcodeInputField.setText("");
        modifyStreetInputField.setText("");
        modifyInsuranceIdInputField.setText("");
        modifyCardnumInputField.setText("");
        modifyDiagnoseInputField.setText("");
        modifyBirthDateInputField.setText("");
        modifyMotherNameInputField.setText("");
        modifyStreetNumberInputField.setText("");

    }

    private boolean isAllFilled(){
        if(nameInputField.getText() == null || nameInputField.getText().trim().isEmpty()) return false;
        if(cityInputField.getText() == null || cityInputField.getText().trim().isEmpty()) return false;
        if(zipcodeInputField.getText() == null || zipcodeInputField.getText().trim().isEmpty()) return false;
        if(streetInputField.getText() == null || streetInputField.getText().trim().isEmpty()) return false;
        if(insuranceIdInputField.getText() == null || insuranceIdInputField.getText().trim().isEmpty()) return false;
        if(cardnumInputField.getText() == null || cardnumInputField.getText().trim().isEmpty()) return false;
        if(diagnoseInputField.getText() == null || diagnoseInputField.getText().trim().isEmpty()) return false;
        if(birthdateInputField.getText() == null || birthdateInputField.getText().trim().isEmpty()) return false;
        if(motherNameInputField.getText() == null || motherNameInputField.getText().trim().isEmpty()) return false;
        if(streetNumberInputField.getText() == null || streetNumberInputField.getText().trim().isEmpty()) return false;
        if (!radioMale.isSelected() && !radioFemale.isSelected()) return false;

        return true;
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

    private void setRadiusForUser(Boolean radius){
        try(JPAUserDAO userDAO = new JPAUserDAO()) {
            String name = LoginController.GlobalUsername;
            List<User> Users = userDAO.getUsers();
            for (User u: Users) {
                if (u.getUsername().matches(name)){
                    u.setRadius(radius);
                    userDAO.updateUser(u);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setThemeForUser(String theme){
        try(JPAUserDAO userDAO = new JPAUserDAO()) {
            String name = LoginController.GlobalUsername;
            List<User> Users = userDAO.getUsers();
            for (User u: Users) {
                if (u.getUsername().matches(name)){
                    u.setTheme(theme);
                    userDAO.updateUser(u);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setRandomPatientDataToList() throws IOException {



        File dataIn = new File("src/main/java/hu/unideb/inf/patients.txt");
        BufferedReader br = new BufferedReader(new FileReader(dataIn));
        String st;

        List<Patient> randomPatients = new ArrayList<>();

        while ((st = br.readLine()) != null){
            StringTokenizer string = new StringTokenizer(st,";");
            while (string.hasMoreTokens()){
                Patient p = new Patient();
                p.setName(string.nextToken());
                p.setGender(string.nextToken());
                p.setCardNumber(Integer.parseInt(string.nextToken()));
                p.setNameOfMother(string.nextToken());
                p.setSocialInsuranceId(Integer.parseInt(string.nextToken()));
                p.setBirthDate(string.nextToken());
                p.setZipCode(Integer.parseInt(string.nextToken()));
                p.setCity(string.nextToken());
                p.setStreet(string.nextToken());
                p.setStreetNumber(string.nextToken());
                p.setDiagnose(string.nextToken());
                randomPatients.add(p);
            }
            System.out.println("\n");
        }

        try(JPAPatientDAO aDAO = new JPAPatientDAO()) {
            List<Patient> deleteRandomPatients = aDAO.getPatients();
            for (Patient p : deleteRandomPatients){
                aDAO.deletePatient(p);
            }
            for (Patient p : randomPatients){
                aDAO.savePatient(p);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
