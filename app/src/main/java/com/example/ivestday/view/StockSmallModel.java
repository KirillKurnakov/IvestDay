package com.example.ivestday.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ivestday.databinding.ListItemBinding;
import com.example.ivestday.viewModel.UserViewModel;

public class StockSmallModel extends Fragment {

    private ListItemBinding binding;
    private UserViewModel user;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListItemBinding.inflate(inflater,container,false);
        user = new ViewModelProvider(this).get(UserViewModel.class);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
