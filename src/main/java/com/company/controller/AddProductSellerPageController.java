package com.company.controller;

import com.company.Main;
import com.company.model.Category;
import com.company.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AddProductSellerPageController implements Initializable {

    @FXML
    private Label resultLBL;

    @FXML
    private TextField qtyTXT;

    @FXML
    private VBox root;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField quantityTXT;

    @FXML
    private TextField priceTXT;

    @FXML
    private Button addBTN;

    @FXML
    private ComboBox<String> categoryId;

    Stage stage;
    private SellerPageController sellerPageController;

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setSellerPageController(SellerPageController sellerPageController) {
        this.sellerPageController = sellerPageController;
    }

    private static AtomicInteger at = new AtomicInteger(0);
    public int getNextCountValue() {
        return at.incrementAndGet();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.shopService.getCategoryList().forEach(c->{
            categoryId.getItems().add(c.getTitle());
        });

        addBTN.setOnAction(c->{

            saveProductForSeller();
        });
    }

    public void saveProductForSeller(){
        String name=nameTXT.getText();
        String price=priceTXT.getText();
        String qty=quantityTXT.getText();
        String selectStr=categoryId.getSelectionModel().getSelectedItem();
        Optional<Category> category = Main.shopService.getCategoryList().stream().filter(c->c.getTitle().equals(selectStr)).findAny();
        Product product=new Product(getNextCountValue(),name,Integer.parseInt(qty),Integer.parseInt(price),
                new ArrayList<>(),"", category.orElseGet(Category::new), Main.shopService.getSeller());
        String result=sellerPageController.addProductToTable(product);
        if(result.equals("Success")){
            sellerPageController.loadTable();
        }else {
            resultLBL.setText(result);
            resultLBL.setTextFill(Color.RED);
        }
    }
}
