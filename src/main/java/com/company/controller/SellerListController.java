package com.company.controller;

import com.company.Main;
import com.company.model.Seller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        existBTN.setOnAction(x->{
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
            Seller seller=sellerListTBL.getSelectionModel().getSelectedItem();
            if(seller!=null){
                sellerListTBL.getItems().remove(seller);
            }
        });

        TableColumn<Seller,Integer> idCol=new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(150);

        TableColumn<Seller,String> usernameCol=new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setPrefWidth(150);

        TableColumn<Seller,String> passeCol=new TableColumn<>("Password");
        passeCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passeCol.setPrefWidth(150);

        TableColumn<Seller,String> phoneCol=new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(150);

        TableColumn<Seller,String> companyNameCol=new TableColumn<>("Company");
        companyNameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyNameCol.setPrefWidth(150);

        sellerListTBL.getColumns().addAll(idCol,usernameCol,passeCol,phoneCol,companyNameCol);

        loadTable(Main.shopService.getSellerList());

    }

    public void loadTable(ArrayList<Seller> sellers){
        sellerListTBL.getItems().clear();
        // Copies data into the TableView's items list
        sellerListTBL.getItems().addAll(Main.shopService.getSellerList());

    }

    public String addSellerToTable(Seller seller){
        String strValue= Main.shopService.createSeller(seller.getUsername(),seller.getPassword(),seller.getPhone(),seller.getCompanyName());
        return strValue;
    }
}
