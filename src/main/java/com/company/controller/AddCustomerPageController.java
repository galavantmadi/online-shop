package com.company.controller;

import com.company.Main;
import com.company.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AddCustomerPageController implements Initializable {


    @FXML
    private TextField phoneTXT;

    @FXML
    private TextField addressTXT;

    @FXML
    private Label resultLBL;

    @FXML
    private TextField passTXT;

    @FXML
    private TextField usernameTXT;

    @FXML
    private VBox root;

    @FXML
    private TextField emailTXT;

    @FXML
    private Button addBTN;

    private static AtomicInteger at = new AtomicInteger(0);
    public int getNextCountValue() {
        return at.incrementAndGet();
    }


    public void saveUser(){
        String username=usernameTXT.getText();
        String password=passTXT.getText();
        String phone=phoneTXT.getText();
        String email=emailTXT.getText();
        String address=addressTXT.getText();

        User user=new User(getNextCountValue(),username,password,phone, AccountType.USER,true,"",email,
                address,new Wallet(WalletType.USER,0),new ShoppingCart(new Date(),new ArrayList<>()),
                new ArrayList<>(),new ArrayList<>());
        String result= createUser(user);
        if(!result.equals("Success")){
            resultLBL.setText(result);
            resultLBL.setTextFill(Color.RED);
        }else {
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("MainPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        }

    }

    public String createUser(User user){
        String strValue= Main.shopService.createUser(user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail(),user.getAddress());
        return strValue;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBTN.setOnAction(c->{
            saveUser();
        });
    }
}
