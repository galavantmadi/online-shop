package com.company.controller;

import com.company.Main;
import com.company.model.AccountType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button loginBTN;

    @FXML
    private VBox root;

    @FXML
    private Label resultLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBTN.setOnKeyPressed(s->{

            try {
                checkLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        loginBTN.setOnAction(s->{

            try {
                checkLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void checkLogin() throws IOException {

        AccountType accountType=Main.shopService.login(usernameField.getText(),passField.getText());
        if(accountType==AccountType.ADMIN){
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AdminPage2.fxml"));
            Parent  parent =loader.load();

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        }else if(accountType==AccountType.SELLER){
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("SellerPage.fxml"));
            Parent  parent =loader.load();

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        }
        else {
            resultLBL.setTextFill(Color.RED);
            resultLBL.setText("Not Valid");
        }
    }


}
