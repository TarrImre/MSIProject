package hu.unideb.inf;

import hu.unideb.inf.Controller.MsiGuiController;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginpage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("MSI Projekt");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("/fxml/img/windowsicon.png"));

        ((MsiGuiController)loader.getController()).init(stage);
        stage.show();

        /*Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();*/
    }



    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
