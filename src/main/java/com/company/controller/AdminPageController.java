package com.company.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {


    @FXML
    private Hyperlink adminListLNK;

    @FXML
    private Hyperlink sellerListLNK;

    @FXML
    private Hyperlink customerListLNK;

    @FXML
    private Button existBTN;

    @FXML
    private BorderPane rootId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        existBTN.setOnAction(s->{
            rootId.getScene().getWindow().hide();
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

        adminListLNK.setOnAction(s->{
            rootId.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("UserListPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setTitle("صفحه کاربران");
            stage.show();
        });

        sellerListLNK.setOnAction(s->{
            rootId.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("SellerListPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setTitle("صفحه فروشندگان");
            stage.show();
        });

        customerListLNK.setOnAction(s->{
            rootId.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("CustomerListPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setTitle("صفحه خریداران");
            stage.show();
        });
    }
}
