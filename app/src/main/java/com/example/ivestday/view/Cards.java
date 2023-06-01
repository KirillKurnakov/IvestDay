package com.example.ivestday.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.adapters.CardAdapter;
import com.example.ivestday.databinding.ListOfCardBinding;
import com.example.ivestday.model.Card;
import com.example.ivestday.viewModel.UserViewModel;

import java.util.ArrayList;

public class Cards extends Fragment {

    private ListOfCardBinding binding;
    private UserViewModel user;
    private ArrayList<Card> cards = new ArrayList<Card>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListOfCardBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user.getUser_m().observe(getViewLifecycleOwner(),user1 -> {

            binding.ListViewCards.setLayoutManager(new LinearLayoutManager(getContext()));
            cards = user1.getCard_list();
            CardAdapter adapter = new CardAdapter(this, cards);
            binding.ListViewCards.setAdapter(adapter);

        });

        binding.topApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_cards2_to_profile2);
            }
        });

        binding.topApp.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Navigation.findNavController(requireView()).navigate(R.id.action_cards2_to_addCard);
                return true;
            }
        });

    }
}
