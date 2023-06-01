package com.example.ivestday.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.AutorisBinding;
import com.example.ivestday.viewModel.UserViewModel;

public class Autoris extends Fragment {

    private UserViewModel user;
    AutorisBinding binding;

    public Autoris() {}

    public static Autoris newInstance() {return new Autoris(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = AutorisBinding.inflate(inflater,container,false);
        View v = binding.getRoot();
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        user = MainActivity.getUserViewModel();
        binding.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.loginViewModel(getContext(),binding.editTextLogin.getText().toString(),
                        binding.editTextPassword.getText().toString()) ) {
                    user.giveUser(binding.editTextLogin.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.action_autoris2_to_profile2);
                }
            }
        });
        binding.containedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.navigate_to_registration);
            }
        });
    }
}
