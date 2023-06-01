package com.example.ivestday.adapters;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.ListItemCardBinding;
import com.example.ivestday.model.Card;
import com.example.ivestday.view.Cards;
import com.example.ivestday.viewModel.CardViewModel;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private String[] localDataSet;

    public ListItemCardBinding binding;
    private final List<Card> cards;
    private CardViewModel cardViewModel;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView Information_of_card;
        final ImageButton delete_card;
        ViewHolder(ListItemCardBinding view){
            super(view.getRoot());
            Information_of_card = view.textCardItem;
            delete_card = view.imageButtonCardDelete;
        }

        public ImageButton getDelete_card(){
            return delete_card;
        }

        @Override
        public void onClick(View view) {

        }
    }

    public CardAdapter(Cards context, List<Card> cards) {
        this.cards = cards;
        cardViewModel = MainActivity.getCardViewModel();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ListItemCardBinding ceb = ListItemCardBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
        return new ViewHolder(ceb);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Card card = cards.get(position);

        holder.Information_of_card.setText(card.getPerformance());
        holder.Information_of_card.setTypeface(null, Typeface.BOLD);

        holder.getDelete_card().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewModel.giveCard(card.getNumber_card());
                cardViewModel.DeleteCard();
                Toast.makeText(view.getContext(), "Карта удалена", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.cards2);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cards == null) {
            return 0;
        }
        return cards.size();
    }
}
