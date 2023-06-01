package com.example.ivestday.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivestday.R;
import com.example.ivestday.adapters.StateAdapter;
import com.example.ivestday.databinding.QuotesBinding;
import com.example.ivestday.model.Stock;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quotes extends Fragment {

    // новый код для парсера
    private Document doc;
    private Thread secThread;
    private Runnable runnable; // то, где будет запускаться код для второго потока
    private RecyclerView listView;
    private StateAdapter adapter;
    private List<Stock> arrayList;
    //

    RecyclerView recyclerView;// = binding.countriesList;
    public Quotes() {
    }

    public static Quotes newInstance() {
        return new Quotes();
    }

    private QuotesBinding binding;
    private ArrayList<Stock> stocks = new ArrayList<Stock>();
    private ArrayList<Stock> stocks1 = new ArrayList<Stock>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = QuotesBinding.inflate(inflater, container, false);
        setInitialData();
        setInitialData1();
        View v = binding.getRoot();
        init();
        return v;
    }

    private void init() {
        recyclerView = binding.countriesList;
        arrayList = new ArrayList<>();
        adapter = new StateAdapter(getContext(),arrayList);
        recyclerView.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
        //adapter.notifyDataSetChanged();
    }

    private void getWeb() {
        try {
            doc = Jsoup.connect("https://ru.investing.com/equities/russia").get();
            Elements tables = doc.getElementsByTag("tbody");
            Element our_table = tables.get(0);
            Elements elements_from_table = our_table.children(); // дочерние элементы берем
            Element dollar = elements_from_table.get(0);
            Elements dollar_elements = dollar.children();

            //Log.d("TAB","Title: " + dollar_elements.get(0).text());

            for (int i = 0; i < our_table.childrenSize(); i++) {
                Stock items = new Stock();
                items.setName(our_table.children().get(i).child(0).text()); // название берем
                items.setName_2(our_table.children().get(i).child(1).text());
                items.setType(our_table.children().get(i).child(2).text());
                items.setUsersQuantity(our_table.children().get(i).child(3).text());
                arrayList.add(items);
            }

            //adapter.notifyDataSetChanged();
            Log.d("TAB","sddsdds");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adapter.notifyDataSetChanged();
//                }
//            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout TL = binding.TabLayout;

        //recyclerView = binding.countriesList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //StateAdapter adapter = new StateAdapter(getContext(), stocks);
        ///StateAdapter adapter = new StateAdapter(getContext(), arrayList);
        //recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

//        //TL.selectTab(binding.TabLayout.getTabAt(0));
//        TabLayout.Tab tab = TL.getTabAt(1);
//        Objects.requireNonNull(tab).select();

        binding.TabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "Акции":
                        //StateAdapter adapter = new StateAdapter(getContext(), stocks);
                        StateAdapter adapter = new StateAdapter(getContext(), arrayList);
                        recyclerView.setAdapter(adapter);
                        //adapter.notifyDataSetChanged();
                        break;
                    case "Валюта":
                        StateAdapter adapter1 = new StateAdapter(getContext(), stocks1);
                        recyclerView.setAdapter(adapter1);
                        //adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding = QuotesBinding.bind(view);
        binding.bottomNavigation.setSelectedItemId(R.id.page_2);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Navigation.findNavController(view).navigate(R.id.action_quotes_to_your_Bag);
                        break;
                    case R.id.page_4:
                        Navigation.findNavController(view).navigate(R.id.action_quotes_to_profile2);
                        break;
                    case R.id.page_3:
                        Navigation.findNavController(view).navigate(R.id.action_quotes_to_notifications);
                        break;
                }
                return false;
            }
        });
    }

    private void setInitialData(){

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

    }
    private void setInitialData1(){

        stocks1.add(new Stock ("EURRUB", "Евро/Рубль","Валюта",70,
                R.drawable.stockmarket));
        stocks1.add(new Stock ("USDRUB", "Доллар/Рубль","Валюта",66,
                R.drawable.stockmarket));

    }
}
