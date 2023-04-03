package com.company.controller;

import com.company.Main;
import com.company.model.Category;
import com.company.model.Order;
import com.company.model.StatusOrder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UserOrderListPageController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private TableView<Order> orderListTBL;

    @FXML
    private Button confirmBTN;

    @FXML
    private Label resultLBL;

    @FXML
    private Button existBTN;

    Stage stage;
    private AdminPageController adminPageController;

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setAdminPageController(AdminPageController adminPageController) {
        this.adminPageController = adminPageController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTable();
        creatTableOrder();
        confirmBTN.setOnAction(v->{
            Order order=orderListTBL.getSelectionModel().getSelectedItem();
            if(order!=null){
                if(order.getStatusOrder().equals(StatusOrder.CREATE)){
                    String result=Main.shopService.confirmOrderUserByAdmin(order.getUser().getUsername(),
                            order.getProduct().getName());
                    if(result.equals("Success")){
                        resultLBL.setText(result);
                        resultLBL.setTextFill(Color.GREEN);
                        loadTable();
                    }else {
                        resultLBL.setText(result);
                        resultLBL.setTextFill(Color.RED);
                    }
                }
            }

        });

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

    }
    public void loadTable(){
        orderListTBL.getItems().clear();
        // Copies data into the TableView's items list
        ArrayList<Order> arrayList=new ArrayList<>();
        Main.shopService.getUserList().forEach(c->{
            c.getOrderList().forEach(d->{
                if(d.getStatusOrder().equals(StatusOrder.CREATE)){
                    arrayList.add(d);
                }
            });
        });
        orderListTBL.getItems().addAll(arrayList);

    }

    public void creatTableOrder(){
        TableColumn<Order,Integer> idCol=new TableColumn<>("ردیف");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(100);

        TableColumn<Order,String> userCol=new TableColumn<>("نام کاربر");
        userCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getUser()
                .getUsername()));
        userCol.setPrefWidth(150);

        TableColumn<Order,String> productCol=new TableColumn<>("نام کالا");
        productCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getProduct()
                .getName()));
        productCol.setPrefWidth(150);

        TableColumn<Order,Integer> qtyCol=new TableColumn<>("تعداد");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        qtyCol.setPrefWidth(100);

        TableColumn<Order,Long> amountCol=new TableColumn<>("مبلغ");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        amountCol.setPrefWidth(100);

        TableColumn<Order,String> statusCol=new TableColumn<>("وضعیت");
        statusCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getStatusOrder()
                .name()));
        statusCol.setPrefWidth(150);

        orderListTBL.getColumns().addAll(idCol,userCol,productCol,qtyCol,amountCol,statusCol);
    }
}
