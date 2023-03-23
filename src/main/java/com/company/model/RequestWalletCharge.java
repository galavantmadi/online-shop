package com.company.model;

import java.util.Date;

public class RequestWalletCharge {
    private int id;
    private User user;
    private Date createTime;
    private long amount;
    private Status status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RequestWalletCharge(int id, User user, Date createTime, long amount, Status status) {
        this.id = id;
        this.user = user;
        this.createTime = createTime;
        this.amount = amount;
        this.status = status;
    }
}
