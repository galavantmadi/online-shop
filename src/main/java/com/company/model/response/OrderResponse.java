package com.company.model.response;

import com.company.model.StatusOrder;

import java.util.Date;

public class OrderResponse {
    private int id;
    private String username;
    private String userPhone;
    private String userEmail;
    private String productName;
    private Date orderDate;
    private int qty;
    private long amount;
    private String sellerName;
    private String sellerPhone;
    private StatusOrder statusOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public OrderResponse(int id, String username, String userPhone, String userEmail, String productName, Date orderDate
            , int qty, long amount, String sellerName, String sellerPhone, StatusOrder statusOrder) {
        this.id = id;
        this.username = username;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.productName = productName;
        this.orderDate = orderDate;
        this.qty = qty;
        this.amount = amount;
        this.sellerName = sellerName;
        this.sellerPhone = sellerPhone;
        this.statusOrder = statusOrder;
    }
}
