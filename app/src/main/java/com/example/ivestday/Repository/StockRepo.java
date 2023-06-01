package com.example.ivestday.Repository;

import com.example.ivestday.R;
import com.example.ivestday.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockRepo {

    public List<Stock> stocks = new ArrayList<>();
    public ArrayList<Stock> stocks_for_user = new ArrayList<>();

    public ArrayList<Stock> getStocks_for_user() {
        return stocks_for_user;
    }

    public void setStocks_for_user(ArrayList<Stock> stocks_for_user) {
        this.stocks_for_user = stocks_for_user;
    }

    public StockRepo() {

        stocks.add(new Stock ("GAZP", "Газпром","Акции",220,
                R.drawable.stockmarket));
        stocks.add(new Stock ("MOEX", "Мосбиржа","Акции",90,
                R.drawable.stockmarket));
        stocks.add(new Stock ("SBER", "Сбербанк","Акции",116,
                R.drawable.stockmarket));
        stocks.add(new Stock ("AFLT", "Аэрофлот","Акции",30,
                R.drawable.stockmarket));
        stocks.add(new Stock ("ALRS", "Алроса ао","Акции",80,
                R.drawable.stockmarket));
        stocks.add(new Stock ("VKCO", "VK-гдр","Акции",416,
                R.drawable.stockmarket));
        stocks.add(new Stock ("VKCO", "VK-гдр","Акции",416,
                R.drawable.stockmarket));
        stocks.add(new Stock ("VKCO", "VK-гдр","Акции",416,
                R.drawable.stockmarket));

        stocks.add(new Stock ("EURRUB", "Евро/Рубль","Валюта",70,
                R.drawable.stockmarket));
        stocks.add(new Stock ("USDRUB", "Доллар/Рубль","Валюта",66,
                R.drawable.stockmarket));
    }

    public Stock getStockByName(String name) {
        Stock result = null;
        for (int i = 0; i < stocks.size(); i++) {
            if (name.equals(stocks.get(i).getName())) {
                result = stocks.get(i);
            }
        }
        return result;
    }

    public Stock getStockByName_1(String name) {
        Stock result = null;
        for (int i = 0; i < stocks_for_user.size(); i++) {
            if (name.equals(stocks_for_user.get(i).getName())) {
                result = stocks_for_user.get(i);
            }
        }
        return result;
    }

    public void deleteByName_1(String name) {
        for (int i = 0; i < stocks_for_user.size(); i++) {
            if (name.equals(stocks_for_user.get(i).getName())) {
                stocks_for_user.remove(i);
            }
        }
    }

    public void CreateNewStockBuy(String name_stock, String quantity, String price) {
        Stock stock = getStockByName(name_stock); // получиили акцию
        stock.setUsersQuantity(quantity); // установили количество
        stock.setUser_Last_Purchase_Price(price); // установили цену покупки
        stocks_for_user.add(stock);
    }

    public void SaleStock(String name_stock, String quantity, String price) { // метод для продажи
        Stock stock = getStockByName_1(name_stock); // получиили акцию
        String helper = stock.getUsersQuantity();
        Integer helper_1 = Integer.parseInt(helper); // получили количество лотов как число
        Integer quantity_1 = Integer.parseInt(quantity); // получили входящее количество лотов как число
        stock.setUsersQuantity(Integer.toString(helper_1 - quantity_1));
        stock.setUser_Last_Purchase_Price(price);
        if (helper_1 - quantity_1 == 0) { // если пользователь продал все
            deleteByName_1(name_stock);
        } else {
            //deleteByName_1(name_stock);
            //stocks_for_user.add(stock);
        }
    }

    public void BuyStock(String name_stock, String quantity, String price) { // метод для продажи
        Stock stock = getStockByName_1(name_stock); // получиили акцию
        String helper = stock.getUsersQuantity();
        Integer helper_1 = Integer.parseInt(helper); // получили количество лотов как число
        Integer quantity_1 = Integer.parseInt(quantity); // получили входящее количество лотов как число
        stock.setUsersQuantity(Integer.toString(helper_1 + quantity_1));
        stock.setUser_Last_Purchase_Price(price);
        //deleteByName_1(name_stock);
        //stocks_for_user.add(stock);
    }
    public void BuyStockNew(String name_stock, String quantity, String price) { // метод для продажи
        Stock stock = getStockByName(name_stock); // получиили акцию
        stock.setUsersQuantity(quantity);
        stock.setUser_Last_Purchase_Price(price);
        stocks_for_user.add(stock);
    }
}
