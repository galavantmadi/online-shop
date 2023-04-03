package com.company.controller;

import com.company.Main;
import com.company.model.Product;
import com.company.model.RequestWalletCharge;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.ResourceBundle;


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
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Product,Integer> idCol=new TableColumn<>("ردیف");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(150);

        TableColumn<Product,String> nameCol=new TableColumn<>("نام کالا");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        TableColumn<Product,Integer> qtyCol=new TableColumn<>("تعداد");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        qtyCol.setPrefWidth(150);

        TableColumn<Product,String> priceCol=new TableColumn<>("قیمت کالا");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);

        TableColumn<Product,String> categoryCol=new TableColumn<>("نوع کالا");
        categoryCol.setCellValueFactory(c-> new SimpleObjectProperty<String>(c.getValue().getCategory().getTitle()));
        categoryCol.setPrefWidth(150);

        productSellerTBL.getColumns().addAll(idCol,nameCol,qtyCol,priceCol,categoryCol);

        loadTable();

        existBTN.setOnAction(x->{
            root.getScene().getWindow().hide();
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

        addBTN.setOnAction(c->{
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AddProductSellerPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            AddProductSellerPageController controller = loader.<AddProductSellerPageController>getController();
            controller.setStage();
            controller.setSellerPageController(this);
            stage.show();
        });
    }
    public void loadTable(){
        productSellerTBL.getItems().clear();
        // Copies data into the TableView's items list
        productSellerTBL.getItems().addAll(Main.shopService.getSeller().getProductList());

    }
    public String addProductToTable(Product product){
        String strValue= Main.shopService.createProduct(product);
        return strValue;
    }
}
