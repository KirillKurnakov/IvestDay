package com.example.ivestday.model;

public class Notify {

    private String Name_of_stock;
    private String Status;
    private String Desired_price;
    private String Type_of_growth;

    public String getType_of_growth() {
        return Type_of_growth;
    }

    public void setType_of_growth(String type_of_growth) {
        Type_of_growth = type_of_growth;
    }

    public Notify(String Name_of_stock, String Desired_price, String Status) {
        this.Name_of_stock = Name_of_stock;
        this.Desired_price = Desired_price;
        this.Status = Status;
    }

    public String getName_of_stock() {
        return Name_of_stock;
    }

    public void setName_of_stock(String name_of_stock) {
        Name_of_stock = name_of_stock;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDesired_price() {
        return Desired_price;
    }

    public void setDesired_price(String desired_price) {
        Desired_price = desired_price;
    }

}
