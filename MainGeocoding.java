package MainGeocod;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by 1234 on 4/8/2017.
 */

public class MainGeocoding  extends Application {


        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("MainGeocodUI.fxml"));
            primaryStage.setTitle("Geocoding");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.centerOnScreen();
            primaryStage.show();

            MainController mainController = new MainController();





        }
    }








