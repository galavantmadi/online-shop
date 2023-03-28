package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class User extends SuperUser {

    private String email;
    private String address;
    private Wallet wallet;
    private ShoppingCart shoppingCart;
    private ArrayList<Order> orderList;
    private ArrayList<Product> productList;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }


    public User(int id, String username, String password, String phone, AccountType accountType, boolean active, String token, String email, String address, Wallet wallet, ShoppingCart shoppingCart, ArrayList<Order> orderList, ArrayList<Product> productList) {
        super(id, username, password, phone, accountType, active, token);
        this.email = email;
        this.address = address;
        this.wallet = wallet;
        this.shoppingCart = shoppingCart;
        this.orderList = orderList;
        this.productList = productList;
    }

    public User(String username, String password, String phone, AccountType accountType, boolean active, String token, String email, String address, Wallet wallet, ShoppingCart shoppingCart, ArrayList<Order> orderList, ArrayList<Product> productList) {
        super(username, password, phone, accountType, active, token);
        this.email = email;
        this.address = address;
        this.wallet = wallet;
        this.shoppingCart = shoppingCart;
        this.orderList = orderList;
        this.productList = productList;
    }

    public User() {
    }

    @Override
    public void crate() {
        System.out.println("Create User With Username : "+this.getUsername()+"has been created");
    }

    @Override
    public void login() {
        System.out.println("User wit username : "+this.getUsername()+"login");
    }
}
