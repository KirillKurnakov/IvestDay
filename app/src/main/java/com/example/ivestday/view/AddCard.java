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
import com.example.ivestday.databinding.AddCardLayBinding;
import com.example.ivestday.viewModel.CardViewModel;
import com.example.ivestday.viewModel.UserViewModel;

public class AddCard extends Fragment {

    private AddCardLayBinding binding;
    private UserViewModel user;
    private CardViewModel cardViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = AddCardLayBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        cardViewModel = MainActivity.getCardViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.topApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_addCard_to_cards2);
            }
        });

        binding.buttonSaveAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.partCard1.getText().toString().isEmpty() ||
                        binding.partCard1.getText().toString().length() < 16) {
                    Toast.makeText(getContext(),"Введите корректный номер карты!",Toast.LENGTH_SHORT).show();
                } else if (binding.partCard1.getText().toString().charAt(0) == '2' ||
                        binding.partCard1.getText().toString().charAt(0) == '4') {
                    if (binding.period1.getText().toString().isEmpty() ||
                    binding.period1.getText().toString().length() < 4) {
                        Toast.makeText(getContext(),"Укажите верный срок действия!",Toast.LENGTH_SHORT).show();
                    } else if (binding.cvv1.getText().toString().isEmpty() ||
                    binding.cvv1.getText().toString().length() < 3) {
                        Toast.makeText(getContext(), "Укажите корректный CVV!", Toast.LENGTH_SHORT).show();
                    } else {
                        cardViewModel.CreateNewCard(getContext(), binding.partCard1.getText().toString(),
                                binding.period1.getText().toString(), binding.cvv1.getText().toString());
                        Navigation.findNavController(view).navigate(R.id.action_addCard_to_cards2);
                    }

                } else {
                    Toast.makeText(getContext(),"Приложение поддерживает Visa или Mir", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
