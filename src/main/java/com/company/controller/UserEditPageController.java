package com.company.controller;

import com.company.Main;
import com.company.model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class UserEditPageController implements Initializable {
    @FXML
    private Button removeItemBTN;

    @FXML
    private Button editBTN;

    @FXML
    private Button exitBTN;

    @FXML
    private BorderPane paneId;

    @FXML
    private TextField emailTXT;


    @FXML
    private Button addBTN;

    @FXML
    private Label walletLBL;

    @FXML
    private TextField phoneTXT;

    @FXML
    private TextArea addressTXT;

    @FXML
    private Label incWalletLBL;

    @FXML
    private TextField amountTXT;

    @FXML
    private TableView<Item> basketTBL;

    @FXML
    private TextField passTXT;

    @FXML
    private TextField usernameTXT;

    @FXML
    private Button saveBasket;

    @FXML
    private TabPane tabPaneId;

    @FXML
    private Label resultCartLBL;

    @FXML
    private TableView<Order> orderTBL;


    Stage stage;
    private MainPageController mainPageController;

    public void setStage() {
        stage = (Stage) paneId.getScene().getWindow();
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = Main.shopService.getUser();
        Long amount = Main.shopService.getRequestWalletChargeArrayList()
                .stream().filter(c -> c.getUser().equals(Main.shopService.getUser())
                        && c.getStatus().equals(Status.CREATE))
                .mapToLong(RequestWalletCharge::getAmount).sum();
        usernameTXT.setText(user.getUsername());
        usernameTXT.setEditable(false);
        passTXT.setText(user.getPassword());
        phoneTXT.setText(user.getPhone());
        addressTXT.setText(user.getAddress());
        emailTXT.setText(user.getEmail());
        walletLBL.setText(String.valueOf(user.getWallet().getBalance()));

        incWalletLBL.setText(String.valueOf(amount));


        editBTN.setOnAction(c -> {
            user.setPassword(passTXT.getText());
            user.setPhone(phoneTXT.getText());
            user.setAddress(addressTXT.getText());
            user.setEmail(emailTXT.getText());
            Main.shopService.updateUser(user);

            paneId.getScene().getWindow().hide();

        });

        exitBTN.setOnAction(c -> {
            paneId.getScene().getWindow().hide();

        });

        addBTN.setOnAction(c -> {
            Main.shopService.createChargeWallet(Main.shopService.getUser(), Long.parseLong(amountTXT.getText()));
            calcIncreaseWallet();
        });

        removeItemBTN.setOnAction(s -> {
            Item item = basketTBL.getSelectionModel().getSelectedItem();
            Main.shopService.removeItemFromShoppingCart(item.getProduct());
            loadShoppingCartTable();
        });

        saveBasket.setOnAction(c -> {
            String result = Main.shopService.confirmShoppingCart(Main.shopService.getUser());
            if (result.equals("Success")) {
                resultCartLBL.setText(result);
                resultCartLBL.setTextFill(Color.GREEN);
                loadShoppingCartTable();
                loadOrderTable();
                mainPageController.init();
                mainPageController.refreshBasketLBL();
            } else {
                resultCartLBL.setText(result);
                resultCartLBL.setTextFill(Color.RED);
            }
        });

        loadShoppingCartTable();
        initShoppingTable();

        loadOrderTable();
        initOrderTable();


    }

    public void calcIncreaseWallet() {
        Long amount = Main.shopService.getRequestWalletChargeArrayList()
                .stream().filter(c -> c.getUser().equals(Main.shopService.getUser())
                        && c.getStatus().equals(Status.CREATE))
                .mapToLong(RequestWalletCharge::getAmount).sum();
        incWalletLBL.setText(String.valueOf(amount));

    }

    public void tabSelected() {
        SingleSelectionModel<Tab> selectionModel = tabPaneId.getSelectionModel();
        selectionModel.select(2);
    }

    public void loadShoppingCartTable() {
        basketTBL.getItems().clear();
        // Copies data into the TableView's items list
        ArrayList<Item> items = Main.shopService.viewShoppingCart();
        basketTBL.getItems().addAll(items);

    }

    public void initShoppingTable() {
        TableColumn<Item, Integer> idCol = new TableColumn<>("ردیف");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(80);

        TableColumn<Item, String> productNameCol = new TableColumn<>("نام کالا");
        productNameCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getProduct()
                .getName()));
        productNameCol.setPrefWidth(150);

        TableColumn<Item, String> qtyCol = new TableColumn<>("تعداد کالا");
        qtyCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(String.valueOf(c.getValue()
                .getQuantity())));
        qtyCol.setPrefWidth(80);

        TableColumn<Item, String> priceCol = new TableColumn<>("مبلغ");
        priceCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(String.valueOf(c.getValue()
                .getPrice())));
        priceCol.setPrefWidth(150);

        basketTBL.getColumns().addAll(idCol, productNameCol, qtyCol, priceCol);
    }

    public void loadOrderTable() {
        orderTBL.getItems().clear();
        // Copies data into the TableView's items list
        ArrayList<Order> items = Main.shopService.viewOrderList();
        orderTBL.getItems().addAll(items);

    }

    public void initOrderTable() {
        TableColumn<Order, Integer> idCol = new TableColumn<>("ردیف");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(80);

        TableColumn<Order, String> productNameCol = new TableColumn<>("نام کالا");
        productNameCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getProduct()
                .getName()));
        productNameCol.setPrefWidth(150);

        TableColumn<Order, String> qtyCol = new TableColumn<>("تعداد کالا");
        qtyCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(String.valueOf(c.getValue()
                .getQty())));
        qtyCol.setPrefWidth(80);

        TableColumn<Order, String> priceCol = new TableColumn<>("مبلغ");
        priceCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(String.valueOf(c.getValue()
                .getTotalAmount())));
        priceCol.setPrefWidth(150);

        TableColumn<Order, String> dateCol = new TableColumn<>("تاریخ");
        dateCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(new SimpleDateFormat("dd/MM/yyyy")
                .format(c.getValue().getCreateDate())));
        dateCol.setPrefWidth(150);

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getStatusOrder().name()));
        statusCol.setPrefWidth(150);

        orderTBL.getColumns().addAll(idCol, productNameCol, qtyCol, priceCol, dateCol, statusCol);


    }


    @FXML
    public void logOut() {

        mainPageController.logOut(usernameTXT.getText());
        mainPageController.init();
        paneId.getScene().getWindow().hide();

    }
}
