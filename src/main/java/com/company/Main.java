package com.company;

import com.company.model.*;
import com.company.service.ShopService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {
    public static Admin admin;
    public static ShopService shopService;

    public static void main(String[] args) {

        admin=new Admin(1,"peyman","123","09391120290", AccountType.ADMIN,true,"","peyman@.com");
        ArrayList<Admin> admins=new ArrayList<>();
        admins.add(admin);
        shopService=new ShopService(new ArrayList<>(),new ArrayList<>(),admins,new ArrayList<>()
                ,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new User(),admin,new Seller());

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //VBox root= FXMLLoader.load((Objects.requireNonNull(this.getClass().getClassLoader().getResource("LoginPage.fxml"))));
        FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("MainPage.fxml"));
        loader.load();
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.show();
    }

}
