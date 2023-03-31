package com.company.controller;

import com.company.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IncreaseWalletUserPageController implements Initializable {

    @FXML
    private Label resultLBL;

    @FXML
    private TextField amountTXT;

    @FXML
    private VBox root;

    @FXML
    private Button addBTN;

    Stage stage;
    private UserEditPageController userEditPageController;

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setCategoryListPageController(UserEditPageController userEditPageController) {
        this.userEditPageController = userEditPageController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBTN.setOnAction(c->{
            Main.shopService.createChargeWallet(Main.shopService.getUser(),Long.parseLong(amountTXT.getText()));
            root.getScene().getWindow().hide();

            userEditPageController.calIncreaseWallet();
        });
    }
}
