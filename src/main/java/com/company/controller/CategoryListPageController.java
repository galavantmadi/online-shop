package com.company.controller;


import com.company.Main;
import com.company.model.Category;
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

public class CategoryListPageController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button removeBTN;

    @FXML
    private Button existBTN;

    @FXML
    private Button addBTN;

    @FXML
    private TableView<Category> categoryListTBL;

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
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AddCategoryPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            AddCategoryPageController controller = loader.<AddCategoryPageController>getController();
            controller.setStage();
            controller.setCategoryListPageController(this);
            stage.show();
        });

        TableColumn<Category,Integer> idCol=new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(150);

        TableColumn<Category,String> titleCol=new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idCol.setPrefWidth(150);

        categoryListTBL.getColumns().addAll(idCol,titleCol);

        loadTable();
    }

    public String addCategoryToTable(Category category){
        String strValue= Main.shopService.createCategory(category.getTitle());
        return strValue;
    }

    public void loadTable(){
        categoryListTBL.getItems().clear();
        // Copies data into the TableView's items list
        categoryListTBL.getItems().addAll(Main.shopService.getCategoryList());

    }
}
