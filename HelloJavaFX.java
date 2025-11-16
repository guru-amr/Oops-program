package oop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX!"); // simple label
        Scene scene = new Scene(label, 300, 200); // width=300, height=200

        primaryStage.setTitle("JavaFX Test Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // launch JavaFX app
    }
}
