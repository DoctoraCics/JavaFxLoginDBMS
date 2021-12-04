package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginUi.fxml"));
        primaryStage.setTitle("Database Login");
        primaryStage.setScene(new Scene(root, 580, 400));
        primaryStage.getIcons().add(new Image("file:src/Pictures/5.png"));
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
