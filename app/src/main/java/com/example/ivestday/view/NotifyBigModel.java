package com.example.ivestday.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.NotificationOneBinding;
import com.example.ivestday.viewModel.NotifyViewModel;
import com.example.ivestday.viewModel.StockViewModel;

public class NotifyBigModel extends Fragment {

    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "NOTIFY";
    private static String name = "notify_name";

    private NotificationOneBinding binding;
    private StockViewModel stock;
    private NotifyViewModel notifyViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NotificationOneBinding.inflate(inflater,container,false);
        stock = MainActivity.getStockViewModel();
        notifyViewModel = MainActivity.getNotifyViewModel();
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = NotificationOneBinding.bind(view);
        notifyViewModel.getUser_m().observe(getViewLifecycleOwner(),notify -> {
            binding.nameOfStock.setText(notify.getName_of_stock());
            binding.nameOfStock.setTypeface(null, Typeface.BOLD);
            binding.status.setText(notify.getStatus());
            binding.status.setTextColor(Color.RED);
            binding.informationAboutPrice.setText(notify.getDesired_price());
            binding.informationAboutPrice.setTypeface(null, Typeface.BOLD);
            if (notifyViewModel.getUser_m() == null) {
                Navigation.findNavController(view).navigate(R.id.action_notifyBigModel_to_notifications);
            }
            binding.topAppNotify.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.recycling:
                            notifyViewModel.DeleteNotify();
                            Toast.makeText(getContext(), "Уведомление удалено!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_notifyBigModel_to_notifications);
                            break;
                        case R.id.notify_create_super:
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            NotificationCompat.Builder builder =
                                    new NotificationCompat.Builder(getContext(),CHANNEL_ID)
                                            .setSmallIcon(R.drawable.stockmarket)
                                            .setContentTitle("Уведомление для " + notify.getName_of_stock())
                                            .setContentText(notify.getDesired_price() + " выполнено")
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                            NotificationManagerCompat notificationManager =
                                    NotificationManagerCompat.from(getContext());
                            notificationManager.createNotificationChannel(mChannel);
                            notificationManager.notify(NOTIFY_ID, builder.build());
                            break;
                    }
                    return true;
                }
            });
        });

        stock.getStockLive().observe(getViewLifecycleOwner(),stock1 -> {
            binding.currentPrice.setText("Текущая цена: " + stock1.getPrice() + " ₽");
            binding.currentPrice.setTypeface(null, Typeface.BOLD);
        });

        binding.topAppNotify.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_notifyBigModel_to_notifications);
            }
        });
    }
}
