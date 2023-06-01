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
import com.example.ivestday.databinding.EmailLayBinding;
import com.example.ivestday.viewModel.UserViewModel;

public class Email extends Fragment {

    private EmailLayBinding binding;
    private UserViewModel user;

    public Email() {}

    public static Email newInstance() { return new Email(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = EmailLayBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        binding.topApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_email2_to_profile2);
            }
        });
        View v = binding.getRoot();
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
            binding.enterEmail.setText("" + user1.getEmail_address());
        });

        binding.buttonSaveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.enterEmail.getText().toString()).matches()) {
                    Toast.makeText(getContext(),"Введите корректный email", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.SaveEmail(binding.enterEmail.getText().toString());
                    Toast.makeText(getContext(),"Почта сохранена",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_email2_to_profile2);
                }
            }
        });
    }
}
