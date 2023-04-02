package com.company.controller;

import com.company.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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
    private Hyperlink categoryListLNK;

    @FXML
    private Button existBTN;

    @FXML
    private BorderPane rootId;

    @FXML
    private Hyperlink walletIncreaseLNK;

    @FXML
    private Hyperlink userOrderLNK;
    @FXML
    private Label totalAmountLBL;


    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        totalAmountLBL.setText(String.valueOf(Main.shopService.getShopList().get(0).getTotalProfit()));

        existBTN.setOnAction(s->{
            rootId.getScene().getWindow().hide();
            Main.shopService.logOut(username);
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("MainPage.fxml"));
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

        categoryListLNK.setOnAction(s->{
            rootId.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("CategoryListPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setTitle("صفحه دسته بندی ها");
            stage.show();
        });

        walletIncreaseLNK.setOnAction(c->{
            rootId.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("RequestIncreaseWalletListPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setTitle("لیست درخواست های شارژ");
            stage.show();
            System.out.println(Main.shopService.getUser().getOrderList());
        });

        userOrderLNK.setOnAction(c->{
            rootId.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("UserOrderListPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setTitle("لیست سفارشات مشتریان");
            UserOrderListPageController controller = loader.<UserOrderListPageController>getController();
            controller.setStage();
            controller.setAdminPageController(this);
            stage.show();
            System.out.println(Main.shopService.getUser().getOrderList());
        });
    }
}
