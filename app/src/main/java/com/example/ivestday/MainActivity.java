package com.example.ivestday;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ivestday.Repository.CardRepo;
import com.example.ivestday.Repository.NotifyRepo;
import com.example.ivestday.Repository.StockRepo;
import com.example.ivestday.Repository.UserRepo;
import com.example.ivestday.viewModel.CardViewModel;
import com.example.ivestday.viewModel.NotifyViewModel;
import com.example.ivestday.viewModel.StockViewModel;
import com.example.ivestday.viewModel.UserViewModel;

import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {

    private static UserViewModel userViewModel;
    private static StockViewModel stockViewModel;
    private static NotifyViewModel notifyViewModel;
    private static CardViewModel cardViewModel;

    static UserRepo repo = new UserRepo();
    static StockRepo stockRepo = new StockRepo();
    static NotifyRepo notifyRepo = new NotifyRepo();
    static CardRepo cardRepo = new CardRepo();

    public static CardRepo getCardRepo() {
        return cardRepo;
    }

    public static void setCardRepo(CardRepo cardRepo) {
        MainActivity.cardRepo = cardRepo;
    }

    public void init() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        stockViewModel = new ViewModelProvider(this).get(StockViewModel.class);
        notifyViewModel = new ViewModelProvider(this).get(NotifyViewModel.class);
        cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
    }

    public static UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public static StockViewModel getStockViewModel() {
        return stockViewModel;
    }

    public static NotifyViewModel getNotifyViewModel() {
        return notifyViewModel;
    }

    public static CardViewModel getCardViewModel() {
        return cardViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);

    }

    public static UserRepo newInstance() {
        return repo;
    }
    public static StockRepo getStockRepo() { return stockRepo; }
    public static NotifyRepo getNotifyRepo() { return notifyRepo; }

    private Document doc;
    private Thread secThread;
    private Runnable runnable; // то, где будет запускаться код для второго потока
    private ListView listView;
    //private CustomArrayAdapter adapter;
    //private List<ListItemClass> arrayList;


    // новый
    private void initWeb() {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb() {

    }
}
