package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAUserDAO;
import hu.unideb.inf.Modell.User;
import javafx.animation.FillTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    @FXML private Ellipse ellipse;

    void BgEventSuccess(){
        FillTransition ft = new FillTransition(Duration.millis(500), ellipse);
        ft.setFromValue(Color.rgb(24,119,242));
        ft.setToValue(Color.rgb(91,181,106));
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }
    void BgEventError(){
        FillTransition ft = new FillTransition(Duration.millis(500), ellipse);
        ft.setFromValue(Color.rgb(24,119,242));
        ft.setToValue(Color.rgb(181,91,91));
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
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
            BgEventError();
            registrationMessage("Minden mezőt kötelező kitölteni!");
            clearTexts();
            return;
        }

        if (!isValidEmailAddress(emailReg.getText())){
            BgEventError();
            registrationMessage("Helyes e-mail formátumot adjon meg!");
            clearTexts();
            return;
        }

        if(!isValidUsername(usernameReg.getText()) || usernameReg.getText().length() < 3 || usernameReg.getText().length() > 30 ){
            BgEventError();
            registrationMessage("Helytelen felhasználónév formátum!\n3-30 Karakter, első karakter betű\nÉkezetet nem tartalmazhat!");
            clearTexts();
            return;
            //6 - 30 Karakter, Első karakter betű, Speciális karakter csak '_' szerepelhet benne
        }

        if (!isValidPassword(passwordReg.getText())){
            BgEventError();
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
            user.setRadius(true);
            user.setTheme("light");

            if (userDAO.usernameAlreadyExists(user.getUsername()) || userDAO.emailAlreadyExists(user.getEmail())){
                BgEventError();
                registrationMessage("Ez a felhasználónév/email már foglalt!");
                clearTexts();
                return;
            }
            
            if (emailRegSecond.getText().matches(emailReg.getText()) && passwordReg.getText().matches(passwordRegSecond.getText())){
                BgEventSuccess();
                registrationMessageSuccess("Sikeres regisztráció!");
                user.setPassword(MD5Encryption(passwordReg.getText()));
                userDAO.saveUser(user);
                LoginController.delay(1500, () -> {
                    try {
                        openLoginWindowEvent(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });



            }else{
                BgEventError();
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
    private void openLoginWindowEvent(ActionEvent event) throws IOException {
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
        registerMessageLabel.setStyle("-fx-text-fill:#b55b5b;-fx-font-weight:bold;");
        registerMessageLabel.setText(message);
    }
    private void registrationMessageSuccess(String message){
        registerMessageLabel.setStyle("-fx-text-fill:#46a356;-fx-font-weight:bold;");
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
        String regex = "^[a-zA-Z]\\w{2,29}$";

        Pattern p = Pattern.compile(regex);

        if (name == null) {
            return false;
        }

        Matcher m = p.matcher(name);

        return m.matches();
    }
}
