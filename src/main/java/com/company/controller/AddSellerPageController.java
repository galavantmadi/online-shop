package com.company.controller;

import com.company.Main;
import com.company.model.AccountType;
import com.company.model.Seller;
import com.company.model.Wallet;
import com.company.model.WalletType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AddSellerPageController implements Initializable {

    @FXML
    private TextField phoneTXT;

    @FXML
    private Label resultLBL;

    @FXML
    private TextField passTXT;

    @FXML
    private TextField usernameTXT;

    @FXML
    private VBox root;

    @FXML
    private TextField companyTXT;

    @FXML
    private Button addBTN;

    Stage stage;
    private SellerListController sellerListController;

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setSellerListController(SellerListController sellerListController) {
        this.sellerListController = sellerListController;
    }

    private static AtomicInteger at = new AtomicInteger(0);
    public int getNextCountValue() {
        return at.incrementAndGet();
    }



    public void saveSeller(){
        String username=usernameTXT.getText();
        String password=passTXT.getText();
        String phone=phoneTXT.getText();
        String company=companyTXT.getText();

        Seller seller=new Seller(getNextCountValue(),username,password,phone, AccountType.SELLER,true,"",company,new Wallet(WalletType.SELLER,0)
                ,new ArrayList<>());
        String result=sellerListController.addSellerToTable(seller);
        if(result.equals("Success")){
            sellerListController.loadTable(Main.shopService.getSellerList());
        }else {
            resultLBL.setText(result);
            resultLBL.setTextFill(Color.RED);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBTN.setOnAction(c->{

            saveSeller();
        });
    }
}
