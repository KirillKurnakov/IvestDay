package com.example.ivestday.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.StockBinding;
import com.example.ivestday.model.Stock;
import com.example.ivestday.viewModel.StockViewModel;
import com.example.ivestday.viewModel.UserViewModel;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class StockBigModel extends Fragment {

    private StockBinding binding;
    private ArrayList<Stock> stocks = new ArrayList<Stock>();

    private UserViewModel user;
    private StockViewModel stock;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = StockBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        stock = MainActivity.getStockViewModel();

        binding.topAppBarStock.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_stockBigModel_to_quotes);
            }
        });

        binding.topAppBarStock.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Navigation.findNavController(requireView()).navigate(R.id.dialogCreateNotify2);
                return true;
            }
        });
        return binding.getRoot();
    }

    private List<Fragment> fragments;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = StockBinding.bind(view);

        GraphView graph = (GraphView) binding.layoutGraphic;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        stock.getStockLive().observe(getViewLifecycleOwner(), stock1 -> {
            binding.topAppBarStock.setTitle(stock1.getName());
            graph.setTitle("График для " + stock1.getName());
        });

        FragmentOverview fragmentOverview = new FragmentOverview();
        FragmentTrade fragmentTrade = new FragmentTrade();
        fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.viewpager_stock,fragmentOverview);
        fragmentTransaction.commit();

        binding.TabLayoutStock.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentTransaction = getParentFragmentManager().beginTransaction();
                switch (tab.getText().toString()) {
                    case "Обзор":
                        fragmentTransaction.replace(R.id.viewpager_stock,fragmentOverview);
                        break;
                    case "Торговля":
                        fragmentTransaction.replace(R.id.viewpager_stock,fragmentTrade);
                        break;
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        });

        binding.bottomNavigation.setSelectedItemId(R.id.page_2);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Navigation.findNavController(view).navigate(R.id.action_stockBigModel_to_your_Bag);
                        break;
                    case R.id.page_4:
                        Navigation.findNavController(view).navigate(R.id.action_stockBigModel_to_profile2);
                        break;
                    case R.id.page_3:
                        Navigation.findNavController(view).navigate(R.id.action_stockBigModel_to_notifications);
                        break;
                }
                return false;
            }
        });
        binding.sellStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Navigation.findNavController(view).navigate(R.id.action_stockBigModel_to_sale_request);
            }
        });
        binding.buyStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Navigation.findNavController(view).navigate(R.id.action_stockBigModel_to_purchaseRequest);
            }
        });
    }
}
