package com.example.ivestday.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.WithdrawBinding;
import com.example.ivestday.viewModel.UserViewModel;

import java.util.List;

public class Withdraw extends Fragment {

    private WithdrawBinding binding;
    private UserViewModel user;
    private List<String> cards;

    private String type_of_stock_now;

    public Withdraw() { }

    public static Withdraw newInstance() {
        return new Withdraw();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = WithdrawBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        binding.topApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_withdraw_to_your_Bag);
            }
        });
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
            cards = user1.getCardList_performance();
            Spinner spinner = binding.spinner1;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item_card, cards);
            spinner.setAdapter(adapter);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        });

        binding.buttonWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String g = binding.inputSumWithdraw.getText().toString(); // считали сумму
                if (!(g.length() == 0)) {
                    Integer helper = Integer.valueOf(g); // получили как число кол-во лотов
                    if (helper == 0) {
                        Toast.makeText(getContext(),"Минимальное сумма 1 Р",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        if (user.Withdraw(getContext(),binding.spinner.getSelectedItem().toString(),
                               binding.inputSumWithdraw.getText().toString())) {
                            Navigation.findNavController(view).navigate(R.id.action_withdraw_to_your_Bag);
                        }
                    }
                } else {
                    Toast.makeText(getContext(),"Введите сумму. Минимум 1 Р",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
