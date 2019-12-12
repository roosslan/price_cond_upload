package com.rasa;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fxLoginController {
    public TextField textfBenutzer;
    public PasswordField textfKennwort;
    public static Stage secondStage;
    public void initialize(){
            textfBenutzer.getText();
        }

    public void onLoginBtnClick(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent rootP = null;
        try {
            rootP = (Parent) fxmlLoader.load();
            secondStage = new Stage();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setTitle("Price conditions: select XLSX");
            secondStage.setResizable(false);
            secondStage.setScene(new Scene(rootP));
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}