package com.company.controller;

import com.company.Main;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private Hyperlink userLNK;

    @FXML
    private Label welcomeLBL;

    @FXML
    private ToolBar toolbarId;

    @FXML
    private ListView<Product> productLSV;

    @FXML
    private Label categoryLBL;

    @FXML
    private Pane topPanelId;

    @FXML
    private Label countLBL;

    @FXML
    private Label showCountLBL;

    @FXML
    private ImageView imgID;




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

        userLNK.setOnAction(c->{
            loadFXMLUserPage();
        });

        countLBL.setOnMouseClicked(c->{
            if(Main.shopService.getUser().getToken()!=null){
                System.out.println("ppppppppp");
                loadFXMLUserPageWitSelectedTab();
            }

        });
        showCountLBL.setOnMouseClicked(c->{
            if(Main.shopService.getUser().getToken()!=null){

                loadFXMLUserPageWitSelectedTab();
            }
        });
        imgID.setOnMouseClicked(d->{
            if(Main.shopService.getUser().getToken()!=null){
                loadFXMLUserPageWitSelectedTab();
            }
        });


    }

    public void init(){
        showCountLBL.setTextFill(Color.BLACK);
        categoryLBL.layoutXProperty().bind(topPanelId.widthProperty().subtract(categoryLBL.widthProperty()).divide(1));
        if(Main.shopService.getUser().getId()==0){
            userLNK.setText("");
            userLNK.setVisible(false);
            welcomeLBL.setVisible(false);
            enterLNK.setVisible(true);
            registerLNK.setVisible(true);
        }else {
            userLNK.setText(Main.shopService.getUser().getUsername());
            userLNK.setVisible(true);
            welcomeLBL.setVisible(true);
            userLNK.setTextFill(Color.BLUE);
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
                    loadMainProduct(source.getText());
                    System.out.println(source.getText());
                }
            });
            toolbarId.getItems().add(hyperlink);
        });


        loadMainProduct("");

        //productLSV.setCellFactory(param -> new ProductListCell());
        productLSV.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new  ListCell<Product>(){
                    private final Label title = new Label();
                    private final Label detail = new Label();
                    private final VBox layout = new VBox(title, detail);
                  /*  private final Hyperlink viewLNK=new Hyperlink("نمایش");*/
                   // private final HBox hBox=new HBox(layout);



                    @Override
                    protected void updateItem(Product item, boolean empty) {
                       setOnMouseClicked(event -> {
                           Main.productSelected=item;

                           loadFXML();
                       });
                       //setOnmo
                        if (empty || item == null || item.getId() == 0) {
                            title.setText(null);
                            detail.setText(null);
                            setGraphic(null);
                        } else {
                            title.setText(item.getName());
                            detail.setText(item.getPrice()+" تومان ");

                            setGraphic(layout);

                        }
                    }


                };
            }
        });



    }

    public void loadMainProduct(String name){
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

    public void logOut(){
        Main.shopService.setUser(new User());
        userLNK.setText("");
        userLNK.setVisible(false);
        welcomeLBL.setVisible(false);
        enterLNK.setVisible(true);
        registerLNK.setVisible(true);
    }

    private void loadFXML() {
        FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("ProductViewPage.fxml"));
        try {
            Parent parent =loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage=new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        ProductViewPageController controller = loader.<ProductViewPageController>getController();
        controller.setStage();
        controller.setMainPageController(this);
        stage.show();
    }

    private void loadFXMLUserPage(){
        FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("UserEditPage2.fxml"));
        try {
            Parent parent =loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage=new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        UserEditPageController controller = loader.<UserEditPageController>getController();
        controller.setStage();
        controller.setMainPageController(this);
        stage.show();
    }

    private void loadFXMLUserPageWitSelectedTab(){
        FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("UserEditPage2.fxml"));
        try {
            Parent parent =loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage=new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        UserEditPageController controller = loader.<UserEditPageController>getController();
        controller.tabSelected();
        controller.setStage();
        controller.setMainPageController(this);
        stage.show();
    }

    public void countShoppingCart(){
        countLBL.setText(String.valueOf(Main.shopService.getUser().getShoppingCart().getItemList().size()));
        countLBL.setTextFill(Color.GREEN);
        showCountLBL.setTextFill(Color.GREEN);
    }

}
