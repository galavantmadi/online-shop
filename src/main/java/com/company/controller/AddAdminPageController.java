package com.company.controller;

import com.company.Main;
import com.company.model.AccountType;
import com.company.model.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AddAdminPageController  implements Initializable{

    @FXML
    private TextField phoneTXT;

    @FXML
    private VBox root;


    @FXML
    private TextField passTXT;

    @FXML
    private TextField usernameTXT;

    @FXML
    private TextField emailTXT;

    @FXML
    private Button addBTN;

    @FXML
    private Label resultLBL;


    private static AtomicInteger at = new AtomicInteger(0);
    public int getNextCountValue() {
        return at.incrementAndGet();
    }

    Stage stage;
    private UserListPageController userListPageController;

    public void setStage() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void setUserListPageController(UserListPageController userListPageController) {
        this.userListPageController = userListPageController;
    }

    public void saveAdmin(){
        String username=usernameTXT.getText();
        String password=passTXT.getText();
        String phone=phoneTXT.getText();
        String email=emailTXT.getText();
        if(username.equals("")||password.equals("")||phone.equals("")||email.equals("")){
            resultLBL.setText("اطلاعات وارد نشده است");
            resultLBL.setTextFill(Color.RED);
            return;
        }

        Admin admin=new Admin(getNextCountValue(),username,password,phone, AccountType.ADMIN,true,"",email);
        String result= userListPageController.addAdminToTable(admin);
        if(result.equals("Success")){
            userListPageController.loadTable(Main.shopService.getAdminList());
            usernameTXT.setText("");
            passTXT.setText("");
            phoneTXT.setText("");
            emailTXT.setText("");
        }else {
            resultLBL.setText(result);
            resultLBL.setTextFill(Color.RED);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBTN.setOnAction(c->{
            resultLBL.setText("");
            resultLBL.setTextFill(Color.BLACK);
            saveAdmin();
        });
    }
}
