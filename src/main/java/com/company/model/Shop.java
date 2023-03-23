package com.company.model;


import java.util.List;

public class Shop {
    private int id;
    private String name;
    private String webSite;
    private String phone;
    private long totalProfit;
    private List<SuperUser> superUserList;
    private List<Product> productList;
    private List<Order> orderList;

    public Shop(int id, String name, String webSite, String phone, long totalProfit, List<SuperUser> superUserList,
                List<Product> productList, List<Order> orderList) {
        this.id = id;
        this.name = name;
        this.webSite = webSite;
        this.phone = phone;
        this.totalProfit = totalProfit;
        this.superUserList = superUserList;
        this.productList = productList;
        this.orderList = orderList;
    }

    public Shop(String name, String webSite, String phone, long totalProfit) {
        this.name = name;
        this.webSite = webSite;
        this.phone = phone;
        this.totalProfit = totalProfit;
    }

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(long totalProfit) {
        this.totalProfit = totalProfit;
    }

    public List<SuperUser> getSuperUserList() {
        return superUserList;
    }

    public void setSuperUserList(List<SuperUser> superUserList) {
        this.superUserList = superUserList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }



}
