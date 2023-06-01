package com.example.ivestday.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ivestday.MainActivity;
import com.example.ivestday.databinding.DialogDeleteCardBinding;
import com.example.ivestday.viewModel.CardViewModel;

public class DialogDeleteCard extends DialogFragment {

    DialogDeleteCardBinding binding;
    private CardViewModel cardViewModel;

    public DialogDeleteCard() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DialogDeleteCardBinding.inflate(inflater,container,false);
        cardViewModel = MainActivity.getCardViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardViewModel.getCard().observe(getViewLifecycleOwner(), card -> {
            binding.MainTextDialogDelCard.setText("Карта " + card.getPerformance() + " будет");
        });

        binding.NoDelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.YesDelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewModel.DeleteCard();
                Toast.makeText(getContext(),"Карта удалена", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
