package com.company.controller;

import com.company.model.RequestWalletCharge;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RequestIncreaseWalletConfirmController implements Initializable {

    @FXML
    private TextField amountTXT;

    @FXML
    private TextField dateTXT;

    @FXML
    private VBox root;

    @FXML
    private TextField userTXT;

    @FXML
    private Button saveBTN;

    @FXML
    private Label resultLBL;

    Stage stage;
    private RequestIncreaseWalletListController requestIncreaseWalletListController;
    private RequestWalletCharge requestWalletCharge;

    public void setRequestWalletCharge(RequestWalletCharge requestWalletCharge) {
        this.requestWalletCharge = requestWalletCharge;
    }

    public RequestWalletCharge getRequestWalletCharge() {
        return requestWalletCharge;
    }

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setRequestIncreaseWalletListController(RequestIncreaseWalletListController requestIncreaseWalletListController) {
        this.requestIncreaseWalletListController = requestIncreaseWalletListController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        amountTXT.setEditable(false);
        dateTXT.setEditable(false);
        userTXT.setEditable(false);
        /*amountTXT.setText(String.valueOf(requestWalletCharge.getAmount()));
        dateTXT.setText(new SimpleDateFormat("dd/MM/yyyy").format(requestWalletCharge.getCreateTime()));
        userTXT.setText(String.valueOf(requestWalletCharge.getUser().getUsername()));*/
        saveBTN.setOnAction(c->{
           String result= requestIncreaseWalletListController.confirmRequest();
           if(result.equals("Success")){
               requestIncreaseWalletListController.loadTable();
               resultLBL.setText(" ");
               root.getScene().getWindow().hide();
           }else {
               resultLBL.setText(result);
               resultLBL.setTextFill(Color.RED);
           }
        });
    }

    public void fillController(){
        amountTXT.setText(String.valueOf(requestWalletCharge.getAmount()));
        dateTXT.setText(new SimpleDateFormat("dd/MM/yyyy").format(requestWalletCharge.getCreateTime()));
        userTXT.setText(String.valueOf(requestWalletCharge.getUser().getUsername()));
    }
}
