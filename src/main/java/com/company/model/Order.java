package com.company.model;

import java.util.Date;

public class Order {
    private User user;
    private Product product;
    private Date createDate;
    private int qty;
    private long totalAmount;
    private StatusOrder statusOrder;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Order(User user, Product product, Date createDate, int qty, long totalAmount, StatusOrder statusOrder) {
        this.user = user;
        this.product = product;
        this.createDate = createDate;
        this.qty = qty;
        this.totalAmount = totalAmount;
        this.statusOrder = statusOrder;
    }
}
