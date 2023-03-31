package com.company.controller;

import com.company.Main;
import com.company.model.RequestWalletCharge;
import com.company.model.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserEditPageController implements Initializable {
    @FXML
    private TextField phoneTXT;

    @FXML
    private Button walletBTN;

    @FXML
    private TextField addressTXT;

    @FXML
    private TextField walletTXT;

    @FXML
    private TextField incWalletTXT;

    @FXML
    private TextField passTXT;

    @FXML
    private Button editBTN;

    @FXML
    private TextField usernameTXT;

    @FXML
    private VBox root;

    @FXML
    private TextField emailTXT;

    Stage stage;
    private MainPageController mainPageController;

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user= Main.shopService.getUser();
        Long amount=Main.shopService.getRequestWalletChargeArrayList()
                .stream().filter(c->c.getUser().equals(Main.shopService.getUser()))
                .mapToLong(RequestWalletCharge::getAmount).sum();
        usernameTXT.setText(user.getUsername());
        usernameTXT.setEditable(false);
        passTXT.setText(user.getPassword());
        phoneTXT.setText(user.getPhone());
        addressTXT.setText(user.getAddress());
        emailTXT.setText(user.getEmail());
        walletTXT.setText(String.valueOf(user.getWallet().getBalance()));
        walletTXT.setEditable(false);
        incWalletTXT.setText(String.valueOf(amount));
        incWalletTXT.setEditable(false);

        editBTN.setOnAction(c->{
            user.setPassword(passTXT.getText());
            user.setPhone(phoneTXT.getText());
            user.setAddress(addressTXT.getText());
            user.setEmail(emailTXT.getText());
            Main.shopService.updateUser(user);

            root.getScene().getWindow().hide();

        });

        walletBTN.setOnAction(c->{
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("IncreaseWalletUserPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            IncreaseWalletUserPageController controller=loader.<IncreaseWalletUserPageController>getController();
            controller.setStage();
            controller.setCategoryListPageController(this);
            stage.show();
        });

    }

    public void calIncreaseWallet(){
        Long amount=Main.shopService.getRequestWalletChargeArrayList()
                .stream().filter(c->c.getUser().equals(Main.shopService.getUser()))
                .mapToLong(RequestWalletCharge::getAmount).sum();
        incWalletTXT.setText(String.valueOf(amount));
        incWalletTXT.setEditable(false);
    }
}
