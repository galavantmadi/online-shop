package com.company.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainPageController implements Initializable {


    @FXML
    private BorderPane root;

    @FXML
    private Hyperlink enterLNK;

    @FXML
    private Hyperlink registerLNK;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enterLNK.setOnAction(c->{
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("LoginPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        });

        registerLNK.setOnAction(c->{
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AddCustomerPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        });
    }
}
