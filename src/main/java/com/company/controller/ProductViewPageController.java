package com.company.controller;

import com.company.Main;
import com.company.model.Item;
import com.company.model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProductViewPageController implements Initializable {
    @FXML
    private Button addToBasketId;

    @FXML
    private TextField sellerTXT;

    @FXML
    private TextField qtyTXT;

    @FXML
    private VBox root;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField categoryTXT;

    @FXML
    private Button addComment;

    @FXML
    private TextField priceTXT;
    @FXML
    private Label resultLBL;

    @FXML
    private TextField productQtyTXT;



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
        sellerTXT.setEditable(false);
        sellerTXT.setText(Main.productSelected.getSeller().getCompanyName());
        nameTXT.setEditable(false);
        nameTXT.setText(Main.productSelected.getName());
        categoryTXT.setEditable(false);
        categoryTXT.setText(Main.productSelected.getCategory().getTitle());
        priceTXT.setEditable(false);
        priceTXT.setText(String.valueOf(Main.productSelected.getPrice()));
        productQtyTXT.setText(String.valueOf(Main.productSelected.getQuantity()));
        productQtyTXT.setEditable(false);

        addToBasketId.setOnAction(c->{
            if(Main.shopService.getUser().getToken()==null){
                resultLBL.setText("شما هنوز لاگین نکرده اید");
                resultLBL.setTextFill(Color.RED);
                return;
            }
            if(qtyTXT.getText().equals("")){
                resultLBL.setText("مقدار ورودی صحیح نمی باشد");
                resultLBL.setTextFill(Color.RED);
                return;
            }else if(Integer.parseInt(qtyTXT.getText())<1){
                resultLBL.setText("مقدار ورودی صحیح نمی باشد");
                resultLBL.setTextFill(Color.RED);
                return;
            }else {
                resultLBL.setText("");
                Optional<Product> product=Main.shopService.getProductList().stream().filter(
                        d->d.getName().equals(nameTXT.getText())).findAny();
                if(product.isPresent()){
                    ArrayList<Item> items=Main.shopService.
                            createShoppingCart(product.get(),Integer.parseInt(qtyTXT.getText()));
                    if(items.size()>0){
                        resultLBL.setText("کالا به سبد خرید افزوده شد");
                        resultLBL.setTextFill(Color.GREEN);
                       mainPageController.countShoppingCart();
                       root.getScene().getWindow().hide();
                    }
                }else {
                    resultLBL.setText("کالا معتبر نمی باشد");
                    resultLBL.setTextFill(Color.RED);
                }

            }
        });
        qtyTXT.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    qtyTXT.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }


}
