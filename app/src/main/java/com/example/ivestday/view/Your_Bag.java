package com.example.ivestday.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.adapters.StateAdapter;
import com.example.ivestday.databinding.YourBagBinding;
import com.example.ivestday.model.Stock;
import com.example.ivestday.viewModel.UserViewModel;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class Your_Bag extends Fragment {

    private ArrayList<Stock> stocks = new ArrayList<Stock>();

    private YourBagBinding binding;
    private UserViewModel user;
    private List<Stock> users_stock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = YourBagBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user.getUser_m().observe(getViewLifecycleOwner(),user1 -> {

            binding.allPrice2.setText(user1.Sum_Invest_Account() + " ₽");
            binding.facilitiesFond.setText(user1.getStock_market_account() + " ₽");
            binding.investFond.setText(user1.getStock_market_account_invest() + " ₽");
            binding.changeInvFacFond.setText(user1.getStock_market_account_change() + " ₽");
            binding.facilitiesCurr.setText(user1.getCurrency_market_account() + " ₽");
            binding.investCurr.setText(user1.getCurrency_market_account_invest() + " ₽");
            binding.changeInvFacCurr.setText(user1.getCurrency_market_account_change() + " ₽");
            binding.priceChange.setText(user1.Sum_Changes() + " ₽");

            binding.topUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // здесь будет логика поставить банк счет новый
                    if (user1.getCard_list() != null) {
                        if (user1.getCard_list().isEmpty()) {
                            Toast.makeText(getContext(), "Добавьте карту (Профиль->Ваши карты)",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Navigation.findNavController(view).navigate(R.id.action_your_Bag_to_topUp);
                        }
                    } else {
                        Toast.makeText(getContext(), "Добавьте карту (Профиль->Ваши карты)",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

            binding.withdrawMoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (user1.getCard_list() != null) {
                        if (user1.getCard_list().isEmpty()) {
                            Toast.makeText(getContext(), "Добавьте карту (Профиль->Ваши карты)",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Navigation.findNavController(view).navigate(R.id.action_your_Bag_to_withdraw);
                        }
                    } else {
                        Toast.makeText(getContext(), "Добавьте карту (Профиль->Ваши карты)",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

            RecyclerView recyclerView = binding.countriesList;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            users_stock = user1.getStock_users();  // взяли акции для текущего пользователя
            StateAdapter adapter = new StateAdapter(getContext(), users_stock);
            recyclerView.setAdapter(adapter);

        });

        binding.bottomNavigation.setSelectedItemId(R.id.page_1);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_2:
                        Navigation.findNavController(view).navigate(R.id.action_your_Bag_to_quotes);
                        break;
                    case R.id.page_4:
                        Navigation.findNavController(view).navigate(R.id.action_your_Bag_to_profile2);
                        break;
                    case R.id.page_3:
                        Navigation.findNavController(view).navigate(R.id.action_your_Bag_to_notifications);
                        break;
                }
                return false;
            }
        });
    }
}
