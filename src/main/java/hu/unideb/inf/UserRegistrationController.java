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
    public void UserRegisterButtonPushed(ActionEvent event) {
        try(JPAUserDAO userDAO = new JPAUserDAO()) {
            User user = new User();
            user.setUsername(usernameReg.getText());
            user.setEmail(emailReg.getText());
            user.setEmail(emailRegSecond.getText());
            user.setPassword(passwordReg.getText());
            user.setPassword(passwordRegSecond.getText());
            
            if (isEqual(emailRegSecond.getText(), emailReg.getText()) && isEqual(passwordReg.getText(), passwordRegSecond.getText())){
                userDAO.saveUser(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("A regisztráció sikeres! Jelentkezz be!");
                alert.showAndWait();
                //VISSZA LOGIN GUIRA
                //MsiGuiController.loginwindow();
            }else{
                textNotMatchesError();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        //ellenorzes
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

        //ATIRNI ERROR GUIRA
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hiba! A jelszo/email mezok nem egyeznek!");
        alert.showAndWait();
        clearTexts();
    }
}
