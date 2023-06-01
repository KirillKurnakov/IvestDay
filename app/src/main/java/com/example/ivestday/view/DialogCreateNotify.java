package com.example.ivestday.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ivestday.MainActivity;
import com.example.ivestday.databinding.DialogCreateNotifyBinding;
import com.example.ivestday.viewModel.NotifyViewModel;
import com.example.ivestday.viewModel.StockViewModel;

public class DialogCreateNotify extends DialogFragment {

    DialogCreateNotifyBinding binding;
    private StockViewModel stock;
    private NotifyViewModel notify;

    public DialogCreateNotify() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DialogCreateNotifyBinding.inflate(inflater,container,false);
        stock = MainActivity.getStockViewModel();
        notify = MainActivity.getNotifyViewModel();
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stock.getStockLive().observe(getViewLifecycleOwner(),stock1 -> {
            binding.TitleDialogCreateNotify.setText("Уведомление для " + stock1.getName());
            binding.MainTextDialogCreateNotify.setText("Последняя цена " + stock1.getPrice() + " ₽");
            binding.YesCreateNotify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (binding.inputDecirePrice.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(),"Укажите требуемую цену!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        notify.CreateNotify(getContext(),stock1.getName(), binding.inputDecirePrice.getText().toString(),
                                binding.spinnerCreateNotify.getSelectedItem().toString());
                        dismiss();
                    }
                }
            });
        });

        binding.NoCreateNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
