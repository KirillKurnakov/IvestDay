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
import com.example.ivestday.databinding.FragmentOverviewBinding;
import com.example.ivestday.viewModel.StockViewModel;

public class FragmentOverview extends Fragment {

    private FragmentOverviewBinding binding;
    private StockViewModel stockViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOverviewBinding.inflate(inflater, container, false);
        stockViewModel = MainActivity.getStockViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stockViewModel.getStockLive().observe(getViewLifecycleOwner(),stock -> {
            binding.nameStock.setText(stock.getName_2());
            binding.nameStock.setTypeface(null, Typeface.BOLD);

            binding.changeRublesPrice.setText("0 ₽");
            binding.currPriceChange.setText(stock.getPrice() + " ₽");

        });
    }

}
