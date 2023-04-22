package com.company.controller;

import com.company.Main;
import com.company.model.RequestWalletCharge;
import com.company.model.Seller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerListController implements Initializable {

    @FXML
    private TableView<Seller> sellerListTBL;

    @FXML
    private BorderPane root;

    @FXML
    private Button removeBTN;

    @FXML
    private Button existBTN;

    @FXML
    private Button addBTN;

    @FXML
    private Button activeBTN;

    @FXML
    private Label resultLBL;






    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resultLBL.setText("");
        resultLBL.setTextFill(Color.BLACK);

        existBTN.setOnAction(x->{
            resultLBL.setText("");
            resultLBL.setTextFill(Color.BLACK);
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AdminPage2.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        });

        addBTN.setOnAction(c->{
            resultLBL.setText("");
            resultLBL.setTextFill(Color.BLACK);
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AddSellerPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            AddSellerPageController controller = loader.<AddSellerPageController>getController();
            controller.setStage();
            controller.setSellerListController(this);
            stage.show();
        });

        removeBTN.setOnAction(c->{
            resultLBL.setText("");
            resultLBL.setTextFill(Color.BLACK);
            Seller seller=sellerListTBL.getSelectionModel().getSelectedItem();
            if(seller!=null){
                sellerListTBL.getItems().remove(seller);
            }
        });

        activeBTN.setOnAction(c->{
            Seller seller=sellerListTBL.getSelectionModel().getSelectedItem();
            if(seller!=null){
                if(!seller.isActive()){
                    Main.shopService.getSellerList().forEach(d->{
                        if(d.equals(seller)){
                            d.setActive(true);
                        }
                    });
                    loadTable();
                    resultLBL.setText("Success");
                    resultLBL.setTextFill(Color.GREEN);
                }else {
                    resultLBL.setText("Seller Is Active");
                    resultLBL.setTextFill(Color.RED);
                }
            }

        });

        TableColumn<Seller,Integer> idCol=new TableColumn<>("ردیف");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);

        TableColumn<Seller,String> usernameCol=new TableColumn<>("نام کاربری");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setPrefWidth(150);

        TableColumn<Seller,String> passeCol=new TableColumn<>("رمز عبور");
        passeCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passeCol.setPrefWidth(150);

        TableColumn<Seller,String> phoneCol=new TableColumn<>("تلفن");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(150);

        TableColumn<Seller,String> companyNameCol=new TableColumn<>("نام شرکت");
        companyNameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyNameCol.setPrefWidth(150);

        TableColumn<Seller,Boolean> statusCol=new TableColumn<>("وضعیت");
        statusCol.setCellValueFactory(c-> new SimpleObjectProperty<Boolean>(c.getValue().isActive()));
        statusCol.setPrefWidth(100);

        TableColumn<Seller,String> walletCol=new TableColumn<>("موجودی");
        walletCol.setCellValueFactory(c-> new SimpleObjectProperty<String>(String.valueOf(c.getValue().getWallet().getBalance())));
        statusCol.setPrefWidth(150);

        sellerListTBL.getColumns().addAll(idCol,usernameCol,passeCol,phoneCol,companyNameCol,walletCol,statusCol);

        loadTable();

    }

    public void loadTable(){
        sellerListTBL.getItems().clear();
        // Copies data into the TableView's items list
        sellerListTBL.getItems().addAll(Main.shopService.getSellerList());

    }

    public String addSellerToTable(Seller seller){
        String strValue= Main.shopService.createSeller(seller.getUsername(),seller.getPassword(),seller.getPhone(),seller.getCompanyName());
        return strValue;
    }
}
