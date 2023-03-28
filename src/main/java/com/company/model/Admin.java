package com.company.model;

public class Admin extends SuperUser {
    private String email;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Admin(int id, String username, String password, String phone, AccountType accountType, boolean active, String token, String email) {
        super(id, username, password, phone, accountType, active, token);
        this.email = email;
    }

    public Admin(String username, String password, String phone, AccountType accountType, boolean active, String token, String email) {
        super(username, password, phone, accountType, active, token);
        this.email = email;
    }

    @Override
    public void crate() {
        System.out.println("Create Admin With Username : "+this.getUsername()+"has been created");
    }
    @Override
    public void login() {
        System.out.println("Admin wit username : "+this.getUsername()+"login");
    }
}
