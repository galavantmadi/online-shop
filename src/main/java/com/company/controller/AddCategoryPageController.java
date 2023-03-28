package com.company.controller;

import com.company.Main;
import com.company.model.*;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddCategoryPageController implements Initializable {

    @FXML
    private TextField titleTXT;

    @FXML
    private Label resultLBL;

    @FXML
    private VBox root;

    @FXML
    private Button addBTN;

    Stage stage;
    private CategoryListPageController categoryListPageController;

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setCategoryListPageController(CategoryListPageController categoryListPageController) {
        this.categoryListPageController = categoryListPageController;
    }

    private static AtomicInteger at = new AtomicInteger(0);
    public int getNextCountValue() {
        return at.incrementAndGet();
    }

    public void saveCategory() {
        String title = titleTXT.getText();

        Category category = new Category(getNextCountValue(), title);
        String result = categoryListPageController.addCategoryToTable(category);
        if (result.equals("Success")) {
            categoryListPageController.loadTable();
            resultLBL.setText(" ");
        } else {
            resultLBL.setText(result);
            resultLBL.setTextFill(Color.RED);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBTN.setOnAction(c->{

            saveCategory();
        });
    }
}
