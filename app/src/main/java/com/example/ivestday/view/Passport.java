package com.example.ivestday.view;

import android.os.Bundle;
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
import com.example.ivestday.databinding.PassportLayoutBinding;
import com.example.ivestday.viewModel.StockViewModel;
import com.example.ivestday.viewModel.UserViewModel;

public class Passport extends Fragment {

    private PassportLayoutBinding binding;
    private UserViewModel user;
    private StockViewModel stock;

    private String type_of_stock_now;

    public Passport() {
    }

    public static Passport newInstance() { return new Passport(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PassportLayoutBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        binding.topApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_passport_to_profile2);
            }
        });
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
            binding.enterFio.setText(user1.getPass_data());
            binding.enterSerialNumber.setText(user1.getPass_data_serial_number());
            binding.enterBirthDay.setText(user1.getDate_birth());
        });
        binding.buttonSavePassport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (binding.enterFio.getText().toString().length() == 0 &&
                            binding.enterSerialNumber.getText().toString().length() == 0 &&
                            binding.enterBirthDay.getText().toString().length() == 0) {
                        user.Save_Pass_Data(binding.enterFio.getText().toString(),
                                binding.enterSerialNumber.getText().toString(),
                                binding.enterBirthDay.getText().toString());
                        Navigation.findNavController(view).navigate(R.id.action_passport_to_profile2);
                    } else if (binding.enterFio.getText().toString().length() == 0) {
                        Toast.makeText(getContext(),"Введите ФИО",
                                Toast.LENGTH_SHORT).show();
                    } else if (binding.enterSerialNumber.getText().toString().length() >= 0
                            && binding.enterSerialNumber.getText().toString().length() < 10) {
                        Toast.makeText(getContext(),"Введите корректные паспортные данные",
                                Toast.LENGTH_SHORT).show();
                    } else if (binding.enterBirthDay.getText().toString().length() >= 0 &&
                            binding.enterBirthDay.getText().toString().length() < 8) {
                        Toast.makeText(getContext(),"Введите корректную дату рождения",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        user.Save_Pass_Data(binding.enterFio.getText().toString(),
                                binding.enterSerialNumber.getText().toString(),
                                binding.enterBirthDay.getText().toString());
                        Toast.makeText(getContext(),"Данные сохранены",
                                Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_passport_to_profile2);
                    }
                }
        });
    }
}
