package hu.unideb.inf;

import hu.unideb.inf.DAO.JPAUserDAO;
import hu.unideb.inf.Modell.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

public class LoginController implements Serializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    @FXML
    private Pane topPane;

    public static String GlobalUsername;
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
    void loginButtonPushed(ActionEvent event) {

        if (!isAllFilled()){
            loginWindowMessage("Minden mezőt kötelező kitölteni!");
            return;
        }

        try(JPAUserDAO userDAO = new JPAUserDAO()) {

            if (!userDAO.validate(username.getText(), MD5Encryption(password.getText()))){
                loginWindowMessage("A felhasználó nem létezik!");
                return;
            }
            List<User> Users = userDAO.getUsers();
            for (User u: Users) {
                if (u.getUsername().matches(username.getText())){
                    GlobalUsername = username.getText();
                    openMsiUserInterfaceEvent(event, u.getTheme(), u.getRadius());
                }

            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void openUserRegistrationInterfaceEvent(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registerpage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("MSI Projekt");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image("/fxml/img/windowsicon.png"));
            ((UserRegistrationController)fxmlLoader.getController()).init(stage);
            Close(event);
            stage.show();
    }

    @FXML
    private void openMsiUserInterfaceEvent(ActionEvent event, String theme, Boolean radius) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MsiGui.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("MSI Projekt");
        stage.setScene(new Scene(root1));
        stage.getIcons().add(new Image("/fxml/img/windowsicon.png"));

        stage.getScene().setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.getScene().getStylesheets().add(getClass().getResource("/fxml/"+ theme +".css").toExternalForm());
        if (radius){
            stage.getScene().getStylesheets().add(getClass().getResource("/fxml/withradius.css").toExternalForm());
        }
        else {stage.getScene().getStylesheets().add(getClass().getResource("/fxml/withoutradius.css").toExternalForm());}

        ((MsiGuiController)fxmlLoader.getController()).init(stage);
        Close(event);
        stage.show();
    }



    private void clearTexts(){
        username.setText("");
        password.setText("");
    }

    private boolean isAllFilled() {
        if(username.getText() == null || username.getText().trim().isEmpty()) return false;
        return password.getText() != null && !password.getText().trim().isEmpty();
    }

    private static String MD5Encryption(String s) throws Exception {

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());

        return new BigInteger(1,m.digest()).toString(16);
    }

    private void loginWindowMessage(String message){
        errorLabel.setStyle("" +
                "-fx-font-weight:bold;\n" +
                "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                "\t-fx-border-color: red;\n" +
                "\t-fx-border-width:2px;");
        errorLabel.setText(message);
        clearTexts();
    }

}
