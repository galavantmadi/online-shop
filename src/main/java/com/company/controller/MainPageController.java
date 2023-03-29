package com.company.controller;

import com.company.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
    @FXML
    private Label lblUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
init();

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
            LoginController controller = loader.<LoginController>getController();
            controller.setStage();
            controller.setMainPageController(this);

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
            AddCustomerPageController controller = loader.<AddCustomerPageController>getController();
            controller.setStage();
            controller.setMainPageController(this);
            stage.show();
        });
    }

    public void init(){
        if(Main.shopService.getUser().getId()==0){
            lblUser.setText("");
            lblUser.setVisible(false);
            enterLNK.setVisible(true);
            registerLNK.setVisible(true);
        }else {
            lblUser.setText("  خوش آمدید "+Main.shopService.getUser().getUsername());
            lblUser.setVisible(true);
            lblUser.setTextFill(Color.BLUE);
            enterLNK.setVisible(false);
            registerLNK.setVisible(false);
        }
    }
}
