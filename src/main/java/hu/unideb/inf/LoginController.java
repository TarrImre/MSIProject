package hu.unideb.inf;
import hu.unideb.inf.DAO.FileUserDAO;
import hu.unideb.inf.DAO.JPAUserDAO;
import hu.unideb.inf.DAO.UserDAO;
import hu.unideb.inf.Modell.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginController implements Serializable {

    @FXML
    private Pane topPane;
    private double x,y;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

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
    private Label errorLabel;

    @FXML
    void loginButtonPushed(ActionEvent event) {

        try(JPAUserDAO userDAO = new JPAUserDAO()) {

            if (!isAllFilled()){
                errorLabel.setStyle("" +
                        "-fx-font-weight:bold;\n" +
                        "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                        "\t-fx-border-color: red;\n" +
                        "\t-fx-border-width:2px;");
                errorLabel.setText("Minden mezőt kötelező kitölteni!");
                return;
            }

            if (!userDAO.validate(username.getText(), password.getText())){
                clearTexts();
                errorLabel.setStyle("" +
                        "-fx-font-weight:bold;\n" +
                        "\t-fx-background-color:rgba(215, 117, 117, 0.8);\n" +
                        "\t-fx-border-color: red;\n" +
                        "\t-fx-border-width:2px;");
                errorLabel.setText("A felhasználó nem létezik!");
            }
            else{

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


        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    public void openUserRegistration(ActionEvent event) {
        try {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearTexts(){
        username.setText("");
        password.setText("");
    }

    private boolean isAllFilled() {
        if(username.getText() == null || username.getText().trim().isEmpty()) return false;
        if(password.getText() == null || password.getText().trim().isEmpty()) return false;

        return true;
    }
}
