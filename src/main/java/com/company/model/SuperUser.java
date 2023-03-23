package com.company.model;

public abstract class SuperUser {

    private int id;
    private String username;
    private String password;
    private String phone;
    private AccountType accountType;
    private boolean active;
    private String token;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public SuperUser(int id, String username, String password, String phone, AccountType accountType, boolean active,
                     String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.accountType = accountType;
        this.active = active;
        this.token = token;
    }

    public SuperUser(String username, String password, String phone, AccountType accountType, boolean active,
                     String token) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.accountType = accountType;
        this.active = active;
        this.token = token;
    }

    public abstract void crate();
    public abstract void login();
}
