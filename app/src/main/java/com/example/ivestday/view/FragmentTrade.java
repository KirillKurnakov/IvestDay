package com.example.ivestday.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ivestday.MainActivity;
import com.example.ivestday.databinding.FragmentTradeBinding;
import com.example.ivestday.viewModel.StockViewModel;

public class FragmentTrade extends Fragment {

    private FragmentTradeBinding binding;
    private StockViewModel stockViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTradeBinding.inflate(inflater,container,false);
        stockViewModel = MainActivity.getStockViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stockViewModel.getStockLive().observe(getViewLifecycleOwner(),stock -> {
            switch (stock.getType()){
                case "Акции":
                    binding.typeMarket.setText("Фондовый рынок");
                    binding.typeMarket.setTypeface(null,Typeface.BOLD);
                    break;
                case "Валюта":
                    binding.typeMarket.setText("Валютный рынок");
                    binding.typeMarket.setTypeface(null,Typeface.BOLD);
                    break;
            }

            binding.assetChange.setText(Integer.toString(Integer.parseInt(stock.getUser_Last_Purchase_Price())*
                    Integer.parseInt(stock.getUsersQuantity())) + " ₽");
            binding.assetChange.setTypeface(null, Typeface.BOLD);

            binding.purchasePriceChange.setText(" " + stock.getUser_Last_Purchase_Price() + " ₽");

            binding.numberCounts.setText(stock.getUsersQuantity()); // переделать

            binding.investChange.setText(Integer.toString(Integer.parseInt(stock.getUser_Last_Purchase_Price())*
                    Integer.parseInt(stock.getUsersQuantity())) + " ₽");
        });
    }
}
