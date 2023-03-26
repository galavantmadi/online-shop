package com.company;

import com.company.model.AccountType;
import com.company.model.Admin;
import com.company.model.Shop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    public static Admin admin=new Admin("peyman","123","09391120290", AccountType.ADMIN,true,"","peyman@.com");

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //VBox root= FXMLLoader.load((Objects.requireNonNull(this.getClass().getClassLoader().getResource("LoginPage.fxml"))));
        FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("LoginPage.fxml"));
        loader.load();
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.show();
    }

}
