package com.example.ivestday.view;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.PhoneLayBinding;
import com.example.ivestday.viewModel.UserViewModel;

public class Phone extends Fragment {

    private PhoneLayBinding binding;
    private UserViewModel user;

    public Phone() {
    }

    public static Phone newInstance() { return new Phone(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PhoneLayBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        binding.topApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_phone2_to_profile2);
            }
        });
        user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
            binding.enterNumberPhone.setText("" + user1.getPhone_number());
        });
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonPhoneSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Patterns.PHONE.matcher(binding.enterNumberPhone.getText().toString()).matches()) {
                    Toast.makeText(getContext(),"Введите корректный номер телефона!",Toast.LENGTH_SHORT).show();
                } else {
                    user.SavePhone(binding.enterNumberPhone.getText().toString());
                    Toast.makeText(getContext(),"Успешное сохранение!",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_phone2_to_profile2);
                }

            }
        });
    }
}
