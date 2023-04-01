package com.company.controller;

import com.company.Main;
import com.company.model.RequestWalletCharge;
import com.company.model.Status;
import com.company.model.User;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserEditPageController implements Initializable {
    @FXML
    private TextField phoneTXT;

    @FXML
    private Label usernameLBL;

    @FXML
    private Label walletLBL;

    @FXML
    private TextField amountTXT;

    @FXML
    private Button addBTN;

    @FXML
    private Label incWalletLBL;



   /* @FXML
    private Button walletBTN;*/

    @FXML
    private TextArea addressTXT;

   /* @FXML
    private TextField walletTXT;

    @FXML
    private TextField incWalletTXT;
*/
    @FXML
    private TextField passTXT;

    @FXML
    private Button editBTN;

    @FXML
    private TextField usernameTXT;

    @FXML
    private BorderPane paneId;

    @FXML
    private TextField emailTXT;

    @FXML
    private Hyperlink exitLNK;

    @FXML
    private Button exitBTN;

    Stage stage;
    private MainPageController mainPageController;

    public void setStage() {
        stage = (Stage) paneId.getScene().getWindow();
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user= Main.shopService.getUser();
        Long amount=Main.shopService.getRequestWalletChargeArrayList()
                .stream().filter(c->c.getUser().equals(Main.shopService.getUser())
                && c.getStatus().equals(Status.CREATE))
                .mapToLong(RequestWalletCharge::getAmount).sum();
        usernameTXT.setText(user.getUsername());
        usernameTXT.setEditable(false);
        passTXT.setText(user.getPassword());
        phoneTXT.setText(user.getPhone());
        addressTXT.setText(user.getAddress());
        emailTXT.setText(user.getEmail());
        usernameLBL.setText(user.getUsername());
        walletLBL.setText(String.valueOf(user.getWallet().getBalance()));

        incWalletLBL.setText(String.valueOf(amount));


        editBTN.setOnAction(c->{
            user.setPassword(passTXT.getText());
            user.setPhone(phoneTXT.getText());
            user.setAddress(addressTXT.getText());
            user.setEmail(emailTXT.getText());
            Main.shopService.updateUser(user);

            paneId.getScene().getWindow().hide();

        });

        /*walletBTN.setOnAction(c->{
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
        });*/

        exitBTN.setOnAction(c->{
            paneId.getScene().getWindow().hide();

        });
        exitLNK.setOnAction(c->{
            mainPageController.logOut();
            paneId.getScene().getWindow().hide();
        });
        addBTN.setOnAction(c->{
            Main.shopService.createChargeWallet(Main.shopService.getUser(),Long.parseLong(amountTXT.getText()));
            calcIncreaseWallet();
        });

    }

    public void calcIncreaseWallet(){
        Long amount=Main.shopService.getRequestWalletChargeArrayList()
                .stream().filter(c->c.getUser().equals(Main.shopService.getUser())
                && c.getStatus().equals(Status.CREATE))
                .mapToLong(RequestWalletCharge::getAmount).sum();
        incWalletLBL.setText(String.valueOf(amount));

    }
}
