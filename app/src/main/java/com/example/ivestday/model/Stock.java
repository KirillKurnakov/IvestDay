package com.example.ivestday.model;

public class Stock {

    private String name;
    private String name_2;
    private String type;
    private int price;
    private int resource_for_icon;
    private String User_Last_Purchase_Price = "0"; // цена акции при покупке пользователем
    private String UsersQuantity = "0"; // количество акции у пользователя

    public String getUsersQuantity() {
        return UsersQuantity;
    }

    public void setUsersQuantity(String usersQuantity) {
        UsersQuantity = usersQuantity;
    }

    public Stock(String name, String name_2, String type, int price, int resource_for_icon) {
        this.name = name;
        this.name_2 = name_2;
        this.type = type;
        this.price = price;
        this.resource_for_icon = resource_for_icon;
    }

    public Stock () {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_2() {
        return name_2;
    }

    public void setName_2(String name_2) {
        this.name_2 = name_2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getResource_for_icon() {
        return resource_for_icon;
    }

    public void setResource_for_icon(int resource_for_icon) {
        this.resource_for_icon = resource_for_icon;
    }

    public String getUser_Last_Purchase_Price() {
        return User_Last_Purchase_Price;
    }

    public void setUser_Last_Purchase_Price(String user_Last_Purchase_Price) {
        User_Last_Purchase_Price = user_Last_Purchase_Price;
    }
}
