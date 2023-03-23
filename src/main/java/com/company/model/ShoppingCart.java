package com.company.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {

    private Date createTime;
    ArrayList<Item> itemList;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public ShoppingCart(Date createTime, ArrayList<Item> itemList) {
        this.createTime = createTime;
        this.itemList = itemList;
    }
}
