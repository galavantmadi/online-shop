package com.company.controller;

import com.company.Main;
import com.company.model.User;
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
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    @FXML
    private TableView<User> customerListTBL;

    @FXML
    private BorderPane root;

    @FXML
    private Button removeBTN;

    @FXML
    private Button existBTN;



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

        removeBTN.setOnAction(c->{
            User user=customerListTBL.getSelectionModel().getSelectedItem();
            if(user!=null){
                customerListTBL.getItems().remove(user);
            }
        });

        TableColumn<User,Integer> idCol=new TableColumn<>("ردیف");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(150);

        TableColumn<User,String> usernameCol=new TableColumn<>("نام کاربری");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setPrefWidth(150);

        TableColumn<User,String> passeCol=new TableColumn<>("رمز عبور");
        passeCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passeCol.setPrefWidth(150);

        TableColumn<User,String> phoneCol=new TableColumn<>("تلفن");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(150);

        TableColumn<User,String> emailCol=new TableColumn<>("ایمیل");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(150);

        TableColumn<User,String> addressCol=new TableColumn<>("آدرس");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(300);

        customerListTBL.getColumns().addAll(idCol,usernameCol,passeCol,phoneCol,emailCol,addressCol);

        loadTable();
    }

    public void loadTable(){
        customerListTBL.getItems().clear();
        // Copies data into the TableView's items list
        customerListTBL.getItems().addAll(Main.shopService.getUserList());

    }
}
