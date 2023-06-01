package com.example.ivestday.model;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class User {

    @Nullable
    private String login = "";
    private String password = "";
    private String pass_data = "";
    private String date_birth = "";
    private String pass_data_serial_number = "";
    private int bank_check;
    private String phone_number = "";
    private String email_address = "";
    private String Stock_market_account = "0";
    private String Stock_market_account_invest = "0";
    private String Stock_market_account_change = "0";
    private String Currency_market_account = "0";
    private String Currency_market_account_invest = "0";
    private String Currency_market_account_change = "0";
    private ArrayList<Card> card_list;
    private ArrayList<Stock> Stock_users; // поле для хранения купленных акций
    private ArrayList<Notify> Notify_users;

    public ArrayList<Notify> getNotify_users() {
        return Notify_users;
    }

    public void setNotify_users(ArrayList<Notify> notify_users) {
        Notify_users = notify_users;
    }

    public String Sum_Changes() {
        int g = Integer.parseInt(Stock_market_account_change);
        int g1 = Integer.parseInt(Currency_market_account_change);
        return Integer.toString(g + g1);
    }
    public ArrayList<Card> getCard_list() {
        return card_list;
    }

    public ArrayList<String> getCardList_performance() {
        ArrayList<String> f = new ArrayList<>();
        for (int i = 0; i < card_list.size(); i++) {
            f.add(card_list.get(i).getPerformance());
        }
        return f;
    }

    public void setCard_list(ArrayList<Card> card_list) {
        this.card_list = card_list;
    }

    public ArrayList<Stock> getStock_users() {
        return Stock_users;
    }

    public void setStock_users(ArrayList<Stock> stock_users) {
        Stock_users = stock_users;
    }

    public String Sum_Invest_Account() {
        int g = Integer.parseInt(Stock_market_account_invest);
        int g1 = Integer.parseInt(Currency_market_account_invest);
        return Integer.toString(g + g1);
    }

    public String getCurrency_market_account() {
        return Currency_market_account;
    }

    public void setCurrency_market_account(String currency_market_account) {
        Currency_market_account = currency_market_account;
    }

    public String getCurrency_market_account_invest() {
        return Currency_market_account_invest;
    }

    public void setCurrency_market_account_invest(String currency_market_account_invest) {
        Currency_market_account_invest = currency_market_account_invest;
    }

    public String getCurrency_market_account_change() {
        return Currency_market_account_change;
    }

    public void setCurrency_market_account_change(String currency_market_account_change) {
        Currency_market_account_change = currency_market_account_change;
    }

    public String getStock_market_account() {
        return Stock_market_account;
    }

    public void setStock_market_account(String stock_market_account) {
        Stock_market_account = stock_market_account;
    }

    public String getStock_market_account_invest() {
        return Stock_market_account_invest;
    }

    public void setStock_market_account_invest(String stock_market_account_invest) {
        Stock_market_account_invest = stock_market_account_invest;
    }

    public String getStock_market_account_change() {
        return Stock_market_account_change;
    }

    public void setStock_market_account_change(String stock_market_account_change) {
        Stock_market_account_change = stock_market_account_change;
    }

    public String getDate_birth() { return date_birth; }

    public void setDate_birth(String date_birth) { this.date_birth = date_birth; }

    public String getPass_data_serial_number() {
        return pass_data_serial_number;
    }

    public void setPass_data_serial_number(String pass_data_serial_number) {
        this.pass_data_serial_number = pass_data_serial_number;
    }

    public String getBank_check() { return Integer.toString(bank_check); }

    public void setBank_check(int bank_check) {
        this.bank_check += bank_check;
    }

    public String getPass_data() {
        return pass_data;
    }

    public void setPass_data(String pass_data) {
        this.pass_data = pass_data;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public User(){ }

    @Nullable
    public String getLogin() {
        return login;
    }

    public void setLogin(@Nullable String login) {
        this.login = login;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }
}
