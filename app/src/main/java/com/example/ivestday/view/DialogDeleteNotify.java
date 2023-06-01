package com.example.ivestday.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ivestday.MainActivity;
import com.example.ivestday.databinding.DialogDeleteNotifyBinding;
import com.example.ivestday.viewModel.NotifyViewModel;

public class DialogDeleteNotify extends DialogFragment {

    DialogDeleteNotifyBinding binding;
    private NotifyViewModel notifyViewModel;
    // добавить общение с view

    public DialogDeleteNotify() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DialogDeleteNotifyBinding.inflate(inflater,container,false);
        notifyViewModel = MainActivity.getNotifyViewModel();
        View v = binding.getRoot();
        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.NoDelNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        binding.YesDelNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyViewModel.DeleteNotify();
                Toast.makeText(getContext(), "Уведомление удалено!", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
