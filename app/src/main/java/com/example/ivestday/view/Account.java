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
import androidx.navigation.Navigation;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.AccountBinding;
import com.example.ivestday.viewModel.UserViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class Account extends Fragment {

    AccountBinding binding;
    private UserViewModel user;

    public Account() {
    }

    public static Account newInstance() {
        return new Account();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = AccountBinding.inflate(inflater,container,false);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = MainActivity.getUserViewModel();
        binding = AccountBinding.bind(view);
        binding.bottomNavigation.setSelectedItemId(R.id.page_4);
        user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
            binding.textView14.setText(user1.getLogin());
            binding.pass.setText("Паспорт " + user1.getPass_data_serial_number());
            binding.phone.setText("Телефон " + user1.getPhone_number());
            binding.email.setText("Почта " + user1.getEmail_address());
        });
        binding.pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile2_to_passport);
            }
        });
        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile2_to_phone2);
            }
        });
        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile2_to_email2);
            }
        });
        binding.cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile2_to_cards2);
            }
        });
        binding.topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                requireActivity().finish();
                System.exit(0);
                //Navigation.findNavController(requireView()).navigate(R.id.action_profile2_to_autoris2);
                return true;
            }
        });
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Navigation.findNavController(view).navigate(R.id.action_profile2_to_your_Bag);
                        break;
                    case R.id.page_2:
                        Navigation.findNavController(view).navigate(R.id.action_profile2_to_quotes);
                        break;
                    case R.id.page_3:
                        Navigation.findNavController(view).navigate(R.id.action_profile2_to_notifications);
                        break;
                }
                return false;
            }
        });
    }
}
