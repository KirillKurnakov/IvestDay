package com.example.ivestday.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.adapters.NotifyAdapter;
import com.example.ivestday.databinding.NotificationsBinding;
import com.example.ivestday.model.Notify;
import com.example.ivestday.viewModel.NotifyViewModel;
import com.example.ivestday.viewModel.UserViewModel;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Notifications extends Fragment {

    private NotifyViewModel notifyViewModel;
    private UserViewModel user;

    private ArrayList<Notify> notifies = new ArrayList<Notify>();

    public Notifications() {
    }

    public static Notifications newInstance() {
        return new Notifications();
    }

    private NotificationsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NotificationsBinding.inflate(inflater,container,false);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = MainActivity.getUserViewModel();

        user.getUser_m().observe(getViewLifecycleOwner(),user1 -> {

            RecyclerView recyclerView = binding.notificationsList;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            notifies = user1.getNotify_users();
            NotifyAdapter adapter = new NotifyAdapter(getContext(), notifies);
            // устанавливаем для списка адаптер
            recyclerView.setAdapter(adapter);

        });
        binding.bottomNavigation.setSelectedItemId(R.id.page_3);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Navigation.findNavController(view).navigate(R.id.action_notifications_to_your_Bag);
                        break;
                    case R.id.page_4:
                        Navigation.findNavController(view).navigate(R.id.action_notifications_to_profile2);
                        break;
                    case R.id.page_2:
                        Navigation.findNavController(view).navigate(R.id.action_notifications_to_quotes);
                        break;
                }
                return false;
            }
        });
    }

}
