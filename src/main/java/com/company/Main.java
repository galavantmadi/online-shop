package com.company;

import com.company.model.*;
import com.company.service.ShopService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
    public static Admin admin;
    public static ShopService shopService;
    public static Product productSelected;

    public static void main(String[] args) {

        //Admin
        admin=new Admin(1,"peyman","123","09391120290", AccountType.ADMIN,true,"","peyman@.com");
        ArrayList<Admin> admins=new ArrayList<>();
        admins.add(admin);

        //Category
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

        //Seller
        Seller seller=new Seller(1,"seller1","123","88223348",AccountType.SELLER
        ,true,"","سینداد مارکت",new Wallet(WalletType.SELLER,0)
                ,new ArrayList<>());

        ArrayList<Seller> sellers=new ArrayList<>();
        sellers.add(seller);

        //Product

        Product product=new Product(1,"ساعت هوشمند 1",100,4000000,new ArrayList<>(),
                "اطلاعات اضافه برای ساعت هوشمند1",category,seller);

        Product product2=new Product(2,"ساعت هوشمند 2",80,3500000,new ArrayList<>(),
                "اطلاعات اضافه برای ساعت هوشمند2",category,seller);

        Product product3=new Product(3,"ساعت هوشمند 3",10,40000000,new ArrayList<>(),
                "اطلاعات اضافه برای ساعت هوشمند3",category,seller);

        Product product4=new Product(4,"ساعت هوشمند 4",9,38000000,new ArrayList<>(),
                "اطلاعات اضافه برای ساعت هوشمند4",category,seller);

        ArrayList<Product> products=new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        //User
        User user=new User(1,"amir","123","88998877",AccountType.USER,true,"","amir@yahoo.com","address amir"
                ,new Wallet(WalletType.USER,0),new ShoppingCart(new Date(),new ArrayList<>()),new ArrayList<>()
                ,new ArrayList<>());

        ArrayList<User> users=new ArrayList<>();
        users.add(user);

        //RequestWalletCharge
        RequestWalletCharge charge=new RequestWalletCharge(1,user,new Date(),10000000,Status.CREATE);
        ArrayList<RequestWalletCharge> requestWalletCharges=new ArrayList<>();
        requestWalletCharges.add(charge);

        //Create Shop
        Shop shop=new Shop("DigiKal","www.digikala.com","8532",0);
        ArrayList<Shop> shops=new ArrayList<>();
        shops.add(shop);


        shopService=new ShopService(users,shops,admins,sellers
                ,products,categories,requestWalletCharges,new User(),admin,new Seller());

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
