package com.company.controller;

import com.company.Main;
import com.company.model.Admin;
import com.company.model.Product;
import com.company.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class MainPageController implements Initializable {


    @FXML
    private BorderPane root;

    @FXML
    private Hyperlink enterLNK;

    @FXML
    private Hyperlink registerLNK;
    @FXML
    private Label lblUser;

    @FXML
    private Label welcomeLBL;

    @FXML
    private ToolBar toolbarId;

    @FXML
    private ListView productLSV;

    @FXML
    private Label categoryLBL;
    @FXML private Pane topPanelId;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        init();

        enterLNK.setOnAction(c->{
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("LoginPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            LoginController controller = loader.<LoginController>getController();
            controller.setStage();
            controller.setMainPageController(this);

            stage.show();
        });

        registerLNK.setOnAction(c->{
            root.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AddCustomerPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            AddCustomerPageController controller = loader.<AddCustomerPageController>getController();
            controller.setStage();
            controller.setMainPageController(this);
            stage.show();
        });
    }

    public void init(){
        categoryLBL.layoutXProperty().bind(topPanelId.widthProperty().subtract(categoryLBL.widthProperty()).divide(1));
        if(Main.shopService.getUser().getId()==0){
            lblUser.setText("");
            lblUser.setVisible(false);
            welcomeLBL.setVisible(false);
            enterLNK.setVisible(true);
            registerLNK.setVisible(true);
        }else {
            lblUser.setText(Main.shopService.getUser().getUsername());
            lblUser.setVisible(true);
            welcomeLBL.setVisible(true);
            lblUser.setTextFill(Color.BLUE);
            welcomeLBL.setTextFill(Color.BLUE);
            enterLNK.setVisible(false);
            registerLNK.setVisible(false);
        }
        Main.shopService.getCategoryList().forEach(c->{
            Hyperlink hyperlink=new Hyperlink(c.getTitle());
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Hyperlink source=(Hyperlink) event.getSource();
                    loadMAinProduct(source.getText());
                    System.out.println(source.getText());
                }
            });
            toolbarId.getItems().add(hyperlink);
        });


        loadMAinProduct("");

        productLSV.setCellFactory(param -> new ProductListCell());
    }

    public void loadMAinProduct(String name){
        productLSV.getItems().clear();
        ArrayList<Product> products;
        if(name.equals("")){
            products=Main.shopService.getProductList();
        }else {
            products=Main.shopService.getProductList().stream()
                    .filter(c->c.getCategory().getTitle().equals(name)).collect(Collectors.toCollection(ArrayList::new));
        }

        // Copies data into the TableView's items list
        productLSV.getItems().addAll(products);
    }

    private static class ProductListCell extends ListCell<Product>{
        private final Label title = new Label();
        private final Label detail = new Label();
        private final VBox layout = new VBox(title, detail);

        public ProductListCell() {
            super();
            title.setStyle("-fx-font-size: 20px;");
        }

        @Override
        protected void updateItem(Product item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);

            if (empty || item == null || item.getId() == 0) {
                title.setText(null);
                detail.setText(null);
                setGraphic(null);
            } else {
                title.setText(item.getName());
                detail.setText(item.getPrice()+" "+item.getSeller().getCompanyName());

                setGraphic(layout);
            }
        }
    }
}
