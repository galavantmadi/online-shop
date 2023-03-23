package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Seller extends SuperUser{
    private String companyName;
    private Wallet wallet;
    private ArrayList<Product> productList;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public Seller(int id, String username, String password, String phone, AccountType accountType, boolean active, String token, String companyName, Wallet wallet, ArrayList<Product> productList) {
        super(id, username, password, phone, accountType, active, token);
        this.companyName = companyName;
        this.wallet = wallet;
        this.productList = productList;
    }

    public Seller(String username, String password, String phone, AccountType accountType, boolean active, String token, String companyName, Wallet wallet, ArrayList<Product> productList) {
        super(username, password, phone, accountType, active, token);
        this.companyName = companyName;
        this.wallet = wallet;
        this.productList = productList;
    }

    @Override
    public void crate() {
        System.out.println("Create Seller With Username : "+this.getUsername()+" And Company Name :"+this.getCompanyName()+"has been created");
    }
    @Override
    public void login() {
        System.out.println("Seller wit username : "+this.getUsername()+"login");
    }
}
