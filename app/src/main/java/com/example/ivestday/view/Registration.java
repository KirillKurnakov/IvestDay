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
import com.example.ivestday.databinding.RegistrationBinding;
import com.example.ivestday.viewModel.UserViewModel;

public class Registration extends Fragment {

    private UserViewModel user;
    RegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RegistrationBinding.inflate(inflater,container,false);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        user = MainActivity.getUserViewModel();
        binding.ButtonRegistrationReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextLoginReg.getText().toString().length() == 0 ||
                binding.editTextLoginReg.getText().toString().length() > 10) {
                    Toast.makeText(getContext(),"Логин Некорректный. Допустимая длина от 1 до 10",
                            Toast.LENGTH_SHORT).show();
                } else if (!user.CheckAlreadyRegistered(getContext(),binding.editTextLoginReg.getText().toString())) { // если такого пользователя нет
                    if (binding.editTextPasswordReg.getText().toString().equals(
                            binding.editTextTextPasswordAgain.getText().toString())) {
                        user.addNewUser(binding.editTextLoginReg.getText().toString(),
                                binding.editTextPasswordReg.getText().toString());
                        user.giveUser(binding.editTextLoginReg.getText().toString());
                        Toast.makeText(getContext(),"Регистрация успешная!",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_registration_to_profile2);
                    } else {
                        Toast.makeText(getContext(),"Пароли не совпадают",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
