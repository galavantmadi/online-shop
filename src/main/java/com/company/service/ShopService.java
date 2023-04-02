package com.company.service;


import com.company.Main;
import com.company.model.*;
import com.company.model.response.OrderResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ShopService {
    private ArrayList<User> userList;
    private ArrayList<Shop> shopList;
    private ArrayList<Admin> adminList;
    private ArrayList<Seller> sellerList;
    private ArrayList<Product> productList;
    private ArrayList<Category> categoryList;
    private ArrayList<RequestWalletCharge> requestWalletChargeArrayList;
    private User user;
    private Admin admin;
    private Seller seller;

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<Shop> getShopList() {
        return shopList;
    }


    public void setShopList(ArrayList<Shop> shopList) {
        this.shopList = shopList;
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    public ArrayList<Seller> getSellerList() {
        return sellerList;
    }

    public void setSellerList(ArrayList<Seller> sellerList) {
        this.sellerList = sellerList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<RequestWalletCharge> getRequestWalletChargeArrayList() {
        return requestWalletChargeArrayList;
    }

    public void setRequestWalletChargeArrayList(ArrayList<RequestWalletCharge> requestWalletChargeArrayList) {
        this.requestWalletChargeArrayList = requestWalletChargeArrayList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    //To Do function

    private static AtomicInteger at = new AtomicInteger(0);

    public int getNextCountValue() {
        return at.incrementAndGet();
    }

    public Optional<Shop> searchShop(String name){
        return shopList.stream().filter(c->c.getName().equals(name)).findAny();

    }

    public void createShop(String name, String webSite, String phone) throws SQLException {
        Optional<Shop> shop=searchShop(name);
        if(!shop.isPresent()){
            Shop newShop=new Shop(name,webSite,phone,0);
            shopList.add(newShop);
            System.out.println("Shop with name : "+newShop.getName()+"has been created");
        }else {
            System.out.println("Shop with name : "+ shop.get().getName()+"IS Exist");
        }
    }

    public Optional<User> searchUser(String username){
        return userList.stream().filter(c->c.getUsername().equals(username)).findAny();
    }
    public Optional<User> searchUserByPass(String username, String pass){
        return userList.stream().filter(c->c.getUsername().equals(username)
        && c.getPassword().equals(pass)).findAny();
    }

    public String createUser(String username,String password,String phone,String email,String address){
        Optional<User> user=searchUser(username);
        if(!user.isPresent()){
            int count=adminList.size();
            User newUser=new User(count+1,username,password,phone, AccountType.USER,true,"",
                    email,address,new Wallet(WalletType.USER,0),new ShoppingCart(new Date(),new ArrayList<>()),
                    new ArrayList<>(),new ArrayList<>());
            userList.add(newUser);
            newUser.crate();
            return "Success";
        }else {
            return "Username is Already Exist ";
        }
    }

    public void updateUser(User user){
        userList.forEach(c->{
            if(c.getUsername().equals(user.getUsername())){
                c.setToken(!user.getToken().equals("")?user.getToken():c.getToken());
                c.setPassword(!user.getPassword().equals("")?user.getPassword():c.getPassword());
                c.setPhone(!user.getPhone().equals("")?user.getPhone():c.getPhone());
                c.setActive(user.isActive());
                c.setPhone(user.getPhone().equals("")?user.getPhone():c.getPhone());
                c.setEmail(!user.getEmail().equals("")?user.getEmail():c.getEmail());
                c.setAddress(!user.getAddress().equals("")?user.getAddress():c.getAddress());
                c.setWallet(user.getWallet()!=null?user.getWallet():c.getWallet());
            }

        });

    }

    public Optional<Admin> searchAdmin(String username){
        return  adminList.stream().filter(c->c.getUsername().equals(username)).findAny();
    }
    public Optional<Admin> searchAdminByPass(String username, String pass){
        return  adminList.stream().filter(c->c.getUsername().equals(username)
        && c.getPassword().equals(pass)).findAny();
    }

    public String createAdmin(String username,String password,String phone,String email){
        Optional<Admin> admin= searchAdmin(username);
        if(!admin.isPresent()){
            int count=adminList.size();
            Admin newAdmin=new Admin(count+1,username,password,phone, AccountType.ADMIN,true,"",email);
            adminList.add(newAdmin);
            newAdmin.crate();
            return "Success";
        }else {
           return "Username is Already Exist ";
        }
    }

    public void updateAdmin(Admin admin){
        adminList.forEach(c->{
            if(c.getUsername().equals(admin.getUsername())){
                c.setToken(!admin.getToken().equals("")?admin.getToken():c.getToken());
                c.setPassword(!admin.getPassword().equals("")?admin.getPassword():c.getPassword());
                c.setPhone(!admin.getPhone().equals("")?admin.getPhone():c.getPhone());
                c.setActive(admin.isActive());
                c.setPhone(admin.getPhone().equals("")?admin.getPhone():c.getPhone());
                c.setEmail(!admin.getEmail().equals("")?admin.getEmail():c.getEmail());
            }

        });
    }

    public Optional<Seller> searchSeller(String username){
        return sellerList.stream().filter(c->c.getUsername().equals(username)).findAny();
    }

    public Optional<Seller> searchSellerByPass(String username, String pass){
        return sellerList.stream().filter(c->c.getUsername().equals(username)
        && c.getPassword().equals(pass)).findAny();
    }

    public String createSeller(String username,String password,String phone,String companyName){
        Optional<Seller> seller= searchSeller(username);
        if(!seller.isPresent()){
            int count=sellerList.size();
            Seller newSeller=new Seller(count+1,username,password,phone, AccountType.SELLER,false,"",companyName,
                    new Wallet(WalletType.SELLER,0),new ArrayList<>());
            sellerList.add(newSeller);
            newSeller.crate();
            return "Success";
        }else {
            return "Username is Already Exist ";
        }
    }

    public void updateSeller(Seller seller){
        sellerList.forEach(c->{
            if(c.getUsername().equals(seller.getUsername())){
                c.setToken(!seller.getToken().equals("")?seller.getToken():c.getToken());
                c.setPassword(!seller.getPassword().equals("")?seller.getPassword():c.getPassword());
                c.setPhone(!seller.getPhone().equals("")?seller.getPhone():c.getPhone());
                c.setActive(seller.isActive());
                c.setPhone(seller.getPhone().equals("")?seller.getPhone():c.getPhone());
                c.setCompanyName(!seller.getCompanyName().equals("")?seller.getCompanyName():c.getCompanyName());
                c.setWallet(seller.getWallet()!=null?seller.getWallet():c.getWallet());
            }

        });
    }

    public AccountType login(String username, String password) {
        // Implement login logic here
        Optional<User> user = searchUserByPass(username,password);
        Optional<Admin> admin = searchAdminByPass(username,password);
        Optional<Seller> seller = searchSellerByPass(username,password);
        if(user.isPresent()){
            user.get().setToken("Ub32587DA");
            user.get().login();
            Main.shopService.setUser(user.get());
            return AccountType.USER;
        }else if(admin.isPresent()){
            admin.get().setToken("Ab32587DA");
            admin.get().login();
            Main.shopService.setAdmin(admin.get());
            return AccountType.ADMIN;
        }else if(seller.isPresent()){
            seller.get().setToken("Sb32587DA");
            seller.get().login();
            Main.shopService.setSeller(seller.get());
            return AccountType.SELLER;
        }

        return AccountType.NONE;
    }

    public void logOut(String username){
        Optional<User> user = searchUser(username);
        Optional<Admin> admin = searchAdmin(username);
        Optional<Seller> seller = searchSeller(username);
        if(user.isPresent()){
            user.get().setToken("");
            user.get().setShoppingCart( new ShoppingCart(new Date(),new ArrayList<>()));
            /*user.get().setOrderList(new ArrayList<>());
            user.get().setProductList(new ArrayList<>());*/
            Main.shopService.setUser(user.get());


        }else if(admin.isPresent()){
            admin.get().setToken("");
            Main.shopService.setAdmin(admin.get());

        }else if(seller.isPresent()){
            seller.get().setToken("");
            Main.shopService.setSeller(seller.get());

        }
    }

    public List<Product> searchProductByCategory(String title){
      return   productList.stream().filter(d->d.getCategory().getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public List<User> getAllUser(){
        return userList;
    }

    public void createChargeWallet(User user, long amount){
        int count=requestWalletChargeArrayList.size();
        RequestWalletCharge charge=new RequestWalletCharge(count+1,user,new Date(),amount, Status.CREATE);
        requestWalletChargeArrayList.add(charge);
    }

    public String acceptListChargeWallet(RequestWalletCharge request){
        if(!admin.getToken().equals("")){
            if(requestWalletChargeArrayList.stream()
                .anyMatch(f->f.getId()==request.getId() && f.getStatus()== Status.DONE)){
                System.out.println("Request for charge amount wallet has been done");
                return "Fail Request";
            }
            requestWalletChargeArrayList.forEach(c->{
                if(c.getId()==request.getId()){
                    c.setStatus(Status.DONE);
                    userList.forEach(d->{
                        if(d.getId()==c.getUser().getId()){
                            d.getWallet().setBalance(d.getWallet().getBalance()+c.getAmount());
                        }
                    });
                    System.out.println("Request Charge Success");

                }
            });
            return "Success";
        }
        System.out.println("User not login yet");
        return "Fail Request";

    }

    public String createCategory(String title){
        if (admin.getToken().equals("")) {
            System.out.println("Admin not yet login");
            return "Admin not yet login";
        }else {
            int count=categoryList.size();
            if(categoryList.stream().noneMatch(c->c.getTitle().equals(title))){
                categoryList.add(new Category(count+1,title));
                return "Success";
            }else {
                return "Title is Already Exist ";
            }
        }
    }

    public ArrayList<Item> viewShoppingCart(){
        if(user.getToken().equals("")){
            System.out.println("User not yet login ");
            return null;
        }else {
            return user.getShoppingCart().getItemList();
        }

    }

    public ArrayList<Item> createShoppingCart(Product product, int qty){
        if (user.getToken() == null) {
            System.out.println("User not yet login");
        }else if(user.getToken().equals("")){
            System.out.println("User not yet login");
        }else {
            ArrayList<Item> items=new ArrayList<>();
            if(user.getShoppingCart().getItemList().size()>0){
                user.getShoppingCart().getItemList().forEach(c->{
                    if(c.getProduct().getName().equals(product.getName())){
                        if(product.getQuantity()>=c.getQuantity()+qty){
                            c.setQuantity(c.getQuantity()+qty);
                            c.setPrice(product.getPrice()*(c.getQuantity()+qty));
                        }else {
                            System.out.println("Quantity for product not valid");
                        }
                    }else {
                        if(product.getQuantity()>=qty){
                            int count=user.getShoppingCart().getItemList().size();
                            Item item=new Item(count+1,product,qty,product.getPrice()*qty);
                            items.add(item);
                        }else {
                            System.out.println("Quantity for product not valid");
                        }
                    }
                });
                items.forEach(d->{
                    user.getShoppingCart().getItemList().add(d);
                });
            }else {
                if(product.getQuantity()>=qty){
                    int count=user.getShoppingCart().getItemList().size();
                    Item item=new Item(count+1,product,qty,product.getPrice()*qty);
                    user.getShoppingCart().getItemList().add(item);

                }else {
                    System.out.println("Quantity for product not valid");
                }
            }


        }
        return viewShoppingCart();
    }

    public void removeItemFromShoppingCart(Product product){
        if(user.getToken()==null){
            System.out.println("User not yet login");
        }else {

            Optional<Item> optionalItem=user.getShoppingCart().getItemList().stream().filter(
                    s->s.getProduct().equals(product)).findAny();
            optionalItem.ifPresent(item -> user.getShoppingCart().getItemList().remove(item));

        }


    }

    public ArrayList<Item>removeAllItemFromShoppingCart(){
        if(user.getToken().equals("")){
            System.out.println("User not yet login");
        }else {
            user.getShoppingCart().getItemList().clear();
            return viewShoppingCart();
        }
        return  null;
    }

    public String confirmShoppingCart(User user){
        if(user.getToken().equals("")){
            System.out.println("User not login yet");
            return "User not login yet";
        }else {
            if(user.getShoppingCart()==null){
                System.out.println("Shopping Cart is empty");
                return "Shopping Cart is empty";
            }else {
                long totalAmount=user.getShoppingCart().getItemList().stream().mapToLong(Item::getPrice).sum();
                if(user.getWallet().getBalance()>=totalAmount){
                    user.getShoppingCart().getItemList().forEach(c->{
                        int count=user.getOrderList().size();
                        Order order=new Order(count+1,user,c.getProduct(),new Date(),c.getQuantity(),c.getPrice(),
                                StatusOrder.CREATE);
                        user.getOrderList().add(order);
                       /* userList.forEach(d->{
                            if(d.equals(user)){
                                d.getOrderList().add(order);
                            }
                        });*/
                    });
                    user.setShoppingCart(new ShoppingCart(new Date(),new ArrayList<>()));
                    System.out.println("Action Success");
                    return "Success";
                }else {
                    System.out.println("Wallet User Not Valid");
                    return "Wallet User Not Valid";
                }

            }
        }
    }

    public ArrayList<Order> viewOrderList(){


            return user.getOrderList();


    }

    public String confirmOrderUserByAdmin(String username,String productName){
        if(admin.getToken()==null || admin.getToken().equals("")){
            System.out.println("Admin not login yet");
            return "Admin not login yet";
        }else{
            Optional<User> user=userList.stream().filter(c->c.getUsername().equals(username)).findAny();
            user.ifPresent(user1 -> user1.getOrderList().forEach(c -> {
                if (c.getProduct().getName().equals(productName)) {
                    c.setStatusOrder(StatusOrder.CONFIRM);
                    user1.getWallet().setBalance(user1.getWallet().getBalance() - c.getTotalAmount());
                    Optional<Product> product = productList.stream().filter(d -> d.getName().equals(c.getProduct().getName()) &&
                            d.getSeller().equals(c.getProduct().getSeller())).findAny();
                    product.ifPresent(value -> sellerList.forEach(s -> {
                        if (s.equals(value.getSeller())) {
                            s.getWallet().setBalance((c.getTotalAmount() * 90) / 100);

                        }
                    }));
                    shopList.get(0).setTotalProfit(shopList.get(0).getTotalProfit() + (c.getTotalAmount() * 10) / 100);

                }

            }));

        }
        return "Success";
    }

    public Optional<Product> searchProductByTitleAndCategory(Product product){
        return  productList.stream().filter(c->c.getName().equals(product.getName()) &&
                c.getSeller().getCompanyName().equals(product.getSeller().getCompanyName())&&
                c.getCategory().getTitle().equals(product.getCategory().getTitle())).findAny();
    }

    public String createProduct(Product product){
        Optional<Product> optionalProduct=searchProductByTitleAndCategory(product);
        if(!optionalProduct.isPresent()){
            productList.add(product);
            seller.getProductList().add(product);
            /*sellerList.forEach(c->{
                if(c.getUsername().equals(product.getSeller().getUsername())
                && c.getCompanyName().equals(product.getSeller().getCompanyName())){
                    c.getProductList().add(product);
                }
            });*/
            return "Success";
        }else {
            return "Product is Already Exist ";
        }
    }

    public ShopService(ArrayList<User> userList, ArrayList<Shop> shopList, ArrayList<Admin> adminList, ArrayList<Seller> sellerList,
                       ArrayList<Product> productList, ArrayList<Category> categoryList, ArrayList<RequestWalletCharge> requestWalletChargeArrayList,
                       User user, Admin admin, Seller seller) {
        this.userList = userList;
        this.shopList = shopList;
        this.adminList = adminList;
        this.sellerList = sellerList;
        this.productList = productList;
        this.categoryList = categoryList;
        this.requestWalletChargeArrayList = requestWalletChargeArrayList;
        this.user = user;
        this.admin = admin;
        this.seller = seller;
    }
}
