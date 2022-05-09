package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAUserDAO;
import hu.unideb.inf.Modell.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistrationController implements Initializable{

    @FXML
    private TextField usernameReg;

    @FXML
    private TextField emailReg;

    @FXML
    private TextField emailRegSecond;

    @FXML
    private TextField passwordReg;

    @FXML
    private TextField passwordRegSecond;

    @FXML
    private Button registerButton;

    @FXML
    private Label registerMessageLabel;

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
    private void userRegisterButtonPushed(ActionEvent event) {

        if (!isAllFilled()){
            registrationMessage("Minden mezőt kötelező kitölteni!");
            clearTexts();
            return;
        }

        if (!isValidEmailAddress(emailReg.getText())){
            registrationMessage("Helyes e-mail formátumot adjon meg!");
            clearTexts();
            return;
        }

        if(!isValidUsername(usernameReg.getText())){
            registrationMessage("Helytelen felhasználónév formátum!\n6-30 Karakter\nElső betű karakter betű\nCsak '_' spec. karakter!");
            clearTexts();
            return;
            //6 - 30 Karakter, Első karakter betű, Speciális karakter csak '_' szerepelhet benne
        }

        if (!isValidPassword(passwordReg.getText())){
            registrationMessage("Helytelen jelszó formátum!\n-Legalább 1 kis- és nagybetű, szám\n8-20 Karakter");
            clearTexts();
            return;
            //Minimum 1 kis- és 1 nagy betű valamint szám, 8-20 karakter white space nélkül
        }

        try(JPAUserDAO userDAO = new JPAUserDAO()) {

            User user = new User();
            user.setUsername(usernameReg.getText());
            user.setEmail(emailReg.getText());
            user.setPassword(passwordReg.getText());

            if (userDAO.usernameAlreadyExists(user.getUsername()) || userDAO.emailAlreadyExists(user.getEmail())){
                registrationMessage("Ez a felhasználónév/email már foglalt!");
                clearTexts();
                return;
            }
            
            if (emailRegSecond.getText().matches(emailReg.getText()) && passwordReg.getText().matches(passwordRegSecond.getText())){
                registerButton.setText("Sikeres regisztráció!");
                user.setPassword(MD5Encryption(passwordReg.getText()));
                userDAO.saveUser(user);
                openSuccessRegistrationWindowEvent(event);

            }else{
                registrationMessage("A jelszó/email mezők nem egyeznek!");
                clearTexts();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void backToLoginButtonPushed(ActionEvent event) throws IOException {
        openLoginWindowEvent(event);
    }

    @FXML
    private void successRegistrationEvent(ActionEvent event)  throws IOException {
        openLoginWindowEvent(event);
    }

    private void openSuccessRegistrationWindowEvent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/error.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/img/windowsicon.png"));
        ((UserRegistrationController)fxmlLoader.getController()).init(stage);
        stage.show();
    }

    private void openLoginWindowEvent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginpage.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/img/windowsicon.png"));
        ((UserRegistrationController)fxmlLoader.getController()).init(stage);
        Close(event);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void clearTexts() {
        usernameReg.setText("");
        emailReg.setText("");
        emailRegSecond.setText("");
        passwordReg.setText("");
        passwordRegSecond.setText("");
    }

    private boolean isAllFilled() {
        if(usernameReg.getText() == null || usernameReg.getText().trim().isEmpty()) return false;
        if(emailReg.getText() == null || emailReg.getText().trim().isEmpty()) return false;
        if(emailRegSecond.getText() == null || emailRegSecond.getText().trim().isEmpty()) return false;
        if(passwordReg.getText() == null || passwordReg.getText().trim().isEmpty()) return false;
        return passwordRegSecond.getText() != null && !passwordRegSecond.getText().trim().isEmpty();
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z\\d.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private void registrationMessage(String message){
        registerMessageLabel.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                "\t-fx-border-color: red;\n" +
                "\t-fx-border-width:2px;");
        registerMessageLabel.setText(message);
    }

    private static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,20}$";


        Pattern p = Pattern.compile(regex);

        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();
    }

    private static String MD5Encryption(String s) throws Exception {

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());

        return new BigInteger(1,m.digest()).toString(16);
    }

    private static boolean isValidUsername(String name) {
        String regex = "^[A-Za-z]\\w{2,29}$";

        Pattern p = Pattern.compile(regex);

        if (name == null) {
            return false;
        }

        Matcher m = p.matcher(name);

        return m.matches();
    }
}
