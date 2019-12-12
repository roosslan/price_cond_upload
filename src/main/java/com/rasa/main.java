package com.rasa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main extends Application {
    private static ApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Price conditions upload - sign in");
        primaryStage.setScene(new Scene(root, 360, 275));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext("com.rasa");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
