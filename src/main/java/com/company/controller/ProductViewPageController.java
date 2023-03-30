package com.company.controller;

import com.company.Main;
import com.company.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProductViewPageController implements Initializable {
    @FXML
    private VBox root;

    @FXML
    private TextField sellerTXT;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField categoryTXT;

    @FXML
    private TextField priceTXT;

    @FXML
    private Button viewBTN;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sellerTXT.setEditable(false);
        sellerTXT.setText(Main.productSelected.getSeller().getCompanyName());
        nameTXT.setEditable(false);
        nameTXT.setText(Main.productSelected.getName());
        categoryTXT.setEditable(false);
        categoryTXT.setText(Main.productSelected.getCategory().getTitle());
        priceTXT.setEditable(false);
        priceTXT.setText(String.valueOf(Main.productSelected.getPrice()));

    }


}
