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
        Category category=new Category(1,"کالای دیجیتال");
        Category category2=new Category(2,"موبایل");
        Category category3=new Category(3,"مد و پوشاک");
        Category category4=new Category(4,"اسباب بازی");
        Category category5=new Category(5,"زیبایی");
        Category category6=new Category(6,"ورزش و سلامت");
        Category category7=new Category(7,"کتاب");
        Category category8=new Category(8,"سفر");
        Category category9=new Category(9,"ابزار صنعتی");
        Category category10=new Category(10,"سوپر مارکت");
        ArrayList<Category> categories=new ArrayList<>();
        categories.add(category);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);
        categories.add(category7);
        categories.add(category8);
        categories.add(category9);
        categories.add(category10);

        shopService=new ShopService(new ArrayList<>(),new ArrayList<>(),admins,new ArrayList<>()
                ,new ArrayList<>(),categories,new ArrayList<>(),new User(),admin,new Seller());

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
