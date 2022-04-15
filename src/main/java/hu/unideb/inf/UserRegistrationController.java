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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Button registerButton;

    @FXML
    private Label registerErrorLabel;

    @FXML
    public void UserRegisterButtonPushed(ActionEvent event) {
        try(JPAUserDAO userDAO = new JPAUserDAO()) {

            if (!isAllFilled()){
                registerErrorLabel.setStyle("" +
                        "-fx-font-weight:bold;\n" +
                        "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                        "\t-fx-border-color: red;\n" +
                        "\t-fx-border-width:2px;");
                registerErrorLabel.setText("Minden mezőt kötelező kitölteni!");
                return;
            }

            User user = new User();
            user.setUsername(usernameReg.getText());
            user.setEmail(emailReg.getText());
            user.setEmail(emailRegSecond.getText());
            user.setPassword(passwordReg.getText());
            user.setPassword(passwordRegSecond.getText());
            
            if (isEqual(emailRegSecond.getText(), emailReg.getText()) && isEqual(passwordReg.getText(), passwordRegSecond.getText())){
                registerButton.setText("Sikeres regisztráció!");
                userDAO.saveUser(user);
                //Thread.sleep(2000);
                loginwindow(event);

            }else{
                textNotMatchesError();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        //ellenorzes
    }

    @FXML
    void backtologin2(ActionEvent event) throws IOException {
        loginwindow(event);
    }

    private void loginwindow(ActionEvent event) throws IOException {
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

    private boolean isEqual(String textOne, String textTwo){

        return textOne.equals(textTwo);
    }

    private void clearTexts() {
        usernameReg.setText("");
        emailReg.setText("");
        emailRegSecond.setText("");
        passwordReg.setText("");
        passwordRegSecond.setText("");
    }


    private void textNotMatchesError(){
        registerErrorLabel.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                "\t-fx-border-color: red;\n" +
                "\t-fx-border-width:2px;\n" +
                "\t-fx-min-width:100px;");
        registerErrorLabel.setText("Hiba! A jelszo/email mezok nem egyeznek!");
        clearTexts();
    }

    private boolean isAllFilled() {
        if(usernameReg.getText() == null || usernameReg.getText().trim().isEmpty()) return false;
        if(emailReg.getText() == null || emailReg.getText().trim().isEmpty()) return false;
        if(emailRegSecond.getText() == null || emailRegSecond.getText().trim().isEmpty()) return false;
        if(passwordReg.getText() == null || passwordReg.getText().trim().isEmpty()) return false;
        if(passwordRegSecond.getText() == null || passwordRegSecond.getText().trim().isEmpty()) return false;
        return true;
    }
}
