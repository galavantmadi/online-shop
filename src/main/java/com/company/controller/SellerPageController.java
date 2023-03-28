package com.company.controller;

import com.company.Main;
import com.company.model.Product;
import com.company.model.Seller;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class SellerPageController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button removeBTN;

    @FXML
    private Button existBTN;

    @FXML
    private Button addBTN;

    @FXML
    private TableView<Product> productSellerTBL;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Product,Integer> idCol=new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(150);

        TableColumn<Product,String> nameCol=new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        TableColumn<Product,Integer> qtyCol=new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        qtyCol.setPrefWidth(150);

        TableColumn<Product,String> priceCol=new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);

        productSellerTBL.getColumns().addAll(idCol,nameCol,qtyCol,priceCol);

        loadTable();
    }
    public void loadTable(){
        productSellerTBL.getItems().clear();
        // Copies data into the TableView's items list
        productSellerTBL.getItems().addAll(Main.shopService.getSeller().getProductList());

    }
}
