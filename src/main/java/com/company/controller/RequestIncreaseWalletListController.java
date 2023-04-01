package com.company.controller;

import com.company.Main;
import com.company.model.RequestWalletCharge;
import com.company.model.Status;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class RequestIncreaseWalletListController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button confirmBTN;

    @FXML
    private Button rejectBTN;

    @FXML
    private Button existBTN;

    @FXML
    private TableView<RequestWalletCharge> requestWalletTBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<RequestWalletCharge,Integer> idCol=new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(150);

        TableColumn<RequestWalletCharge,String> userCol=new TableColumn<>("Username");
        userCol.setCellValueFactory(c-> new SimpleObjectProperty<String>(c.getValue().getUser().getUsername()));
        userCol.setPrefWidth(150);

        TableColumn<RequestWalletCharge, String> dateCol=new TableColumn<>("Date");
        dateCol.setCellValueFactory(c-> new SimpleObjectProperty<String>(new SimpleDateFormat("dd/MM/yyyy").format(c.getValue().getCreateTime())));
        dateCol.setPrefWidth(150);

        TableColumn<RequestWalletCharge, Long> amountCol=new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setPrefWidth(150);

        TableColumn<RequestWalletCharge,String> statusCol=new TableColumn<>("Status");
        statusCol.setCellValueFactory(c-> new SimpleObjectProperty<String>(c.getValue().getStatus().name()));
        statusCol.setPrefWidth(150);

        requestWalletTBL.getColumns().addAll(idCol,userCol,dateCol,amountCol,statusCol);

        loadTable();


        existBTN.setOnAction(c->{
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
            System.out.println(Main.shopService.getUser().getOrderList());
        });

        confirmBTN.setOnAction(c->{
            RequestWalletCharge charge=requestWalletTBL.getSelectionModel().getSelectedItem();
            if(charge!=null){
                FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("RequestIncreaseWalletConfirmPage.fxml"));
                try {
                    Parent parent =loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage stage=new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                RequestIncreaseWalletConfirmController controller=loader.<RequestIncreaseWalletConfirmController>getController();
                controller.setStage();
                controller.setRequestIncreaseWalletListController(this);
                controller.setRequestWalletCharge(charge);
                controller.fillController();
                stage.show();
            }

        });

        rejectBTN.setOnAction(v->{
            RequestWalletCharge charge=requestWalletTBL.getSelectionModel().getSelectedItem();
            Main.shopService.getRequestWalletChargeArrayList().forEach(c->{
                if(c.getId()==charge.getId() && c.getStatus().equals(charge.getStatus())){
                    c.setStatus(Status.REJECT);
                }
            });
            loadTable();
        });

    }

    public void loadTable(){
        requestWalletTBL.getItems().clear();
        // Copies data into the TableView's items list
        requestWalletTBL.getItems().addAll(Main.shopService.getRequestWalletChargeArrayList()
        .stream().filter(d->d.getStatus().equals(Status.CREATE)).collect(Collectors.toList()));
        System.out.println(Main.shopService.getUser());

    }

    public String confirmRequest(){
        RequestWalletCharge charge=requestWalletTBL.getSelectionModel().getSelectedItem();
       return Main.shopService.acceptListChargeWallet(charge);
    }
}
