package com.example.ivestday.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.model.Stock;
import com.example.ivestday.viewModel.StockViewModel;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    private String[] localDataSet;

    private final LayoutInflater inflater;
    private final List<Stock> stocks;
    private StockViewModel stockViewModel;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView flagView;
        final TextView name, name_2, type,price;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.flag);
            name = view.findViewById(R.id.name);
            name_2 = view.findViewById(R.id.capital);
            type = view.findViewById(R.id.capital_2);
            price = view.findViewById(R.id.name_price);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public StateAdapter(Context context, List<Stock> stocks) {
        this.stocks = stocks;
        this.inflater = LayoutInflater.from(context);
        stockViewModel = MainActivity.getStockViewModel();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stock stock = stocks.get(position);
        holder.flagView.setImageResource(stock.getResource_for_icon());
        holder.name.setText(stock.getName());
        holder.name_2.setText(stock.getName_2());
        holder.type.setText(stock.getType());
        holder.price.setText(Integer.toString(stock.getPrice()) + " ₽");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockViewModel.giveStock(stock.getName());
                Navigation.findNavController(view).navigate(R.id.stockBigModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (stocks == null) {
            return 0;
        }
        return stocks.size();
    }
}
