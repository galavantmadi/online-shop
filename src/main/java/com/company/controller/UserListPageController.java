package com.company.controller;

import com.company.Main;
import com.company.model.Admin;
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

public class UserListPageController implements Initializable {

    @FXML
    private TableView<Admin> adminListTBL;

    @FXML
    private BorderPane root;

    @FXML
    private Button removeBTN;

    @FXML
    private Button existBTN;

    @FXML
    private Button addBTN;

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
            FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("AddAdminPage.fxml"));
            try {
                Parent parent =loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            AddAdminPageController controller = loader.<AddAdminPageController>getController();
            controller.setStage();
            controller.setUserListPageController(this);
            stage.show();
        });

        removeBTN.setOnAction(c->{
            Admin admin=adminListTBL.getSelectionModel().getSelectedItem();
            if(admin!=null){
                adminListTBL.getItems().remove(admin);
            }
        });

        TableColumn<Admin,Integer> idCol=new TableColumn<>("ردیف");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(150);

        TableColumn<Admin,String> usernameCol=new TableColumn<>("نام کاربری");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setPrefWidth(150);

        TableColumn<Admin,String> passeCol=new TableColumn<>("رمز عبور");
        passeCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passeCol.setPrefWidth(150);

        TableColumn<Admin,String> phoneCol=new TableColumn<>("تلفن");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(150);

        TableColumn<Admin,String> emailCol=new TableColumn<>("ایمیل");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(150);

        adminListTBL.getColumns().addAll(idCol,usernameCol,passeCol,phoneCol,emailCol);

       loadTable(Main.shopService.getAdminList());



    }

    public void loadTable(ArrayList<Admin> admins){
        adminListTBL.getItems().clear();
        // Copies data into the TableView's items list
        adminListTBL.getItems().addAll(Main.shopService.getAdminList());

    }

    public String addAdminToTable(Admin admin){
        String strValue= Main.shopService.createAdmin(admin.getUsername(),admin.getPassword(),admin.getPhone(),admin.getEmail());
        return strValue;
    }
}
