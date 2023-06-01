package com.example.ivestday.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ivestday.MainActivity;
import com.example.ivestday.Repository.StockRepo;
import com.example.ivestday.model.Stock;

public class StockViewModel extends ViewModel {

    private MutableLiveData<Stock> StockLiveData = new MutableLiveData<>();
    private StockRepo repository = MainActivity.getStockRepo();
    private UserViewModel user = MainActivity.getUserViewModel();

    public void giveStock(String name) {
        StockLiveData.setValue(repository.getStockByName(name));
    }

    public MutableLiveData<Stock> getStockLive() {
        return StockLiveData;
    }

    public void SaleStock(String name_stock, String quantity, String price,String type) {
        repository.SaleStock(name_stock,quantity,price); // продать акцию
        user.getUser_m().getValue().setStock_users(repository.getStocks_for_user()); // установить список
        user.MakeTransaction(type,quantity,price,0);
    }
    public void BuyStock(String name_stock, String quantity, String price,String type) {
        repository.BuyStock(name_stock,quantity,price);
        user.getUser_m().getValue().setStock_users(repository.getStocks_for_user());
        user.MakeTransaction(type,quantity,price,1);
    }

    public void BuyStockNew(String name_stock, String quantity, String price,String type) {
        repository.BuyStockNew(name_stock,quantity,price);
        user.getUser_m().getValue().setStock_users(repository.getStocks_for_user());
        user.MakeTransaction(type,quantity,price,1);
    }
}
