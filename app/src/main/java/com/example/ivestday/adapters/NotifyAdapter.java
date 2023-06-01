package com.example.ivestday.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivestday.MainActivity;
import com.example.ivestday.R;
import com.example.ivestday.databinding.ListItemNotificationBinding;
import com.example.ivestday.model.Notify;
import com.example.ivestday.viewModel.NotifyViewModel;

import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {

    private String[] localDataSet;

    public ListItemNotificationBinding binding;
    private final LayoutInflater inflater;
    private final List<Notify> notifies;
    private NotifyViewModel notifyViewModel;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView Name_of_stock, Desired_price, Status;
        ViewHolder(View view){
            super(view);
            Name_of_stock = view.findViewById(R.id.name_of_stock_in_notify_small);
            Desired_price = view.findViewById(R.id.capital_in_notify_small);
            Status = view.findViewById(R.id.capital_2_in_notify_small);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public NotifyAdapter(Context context, List<Notify> notifies) {
        //this.notifications = notifications;
        notifyViewModel = MainActivity.getNotifyViewModel();
        this.notifies = notifies;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_notification, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Notify notify = notifies.get(position);
        holder.Name_of_stock.setText(notify.getName_of_stock());
        holder.Desired_price.setText(notify.getDesired_price());
        holder.Status.setText(notify.getStatus());
        holder.Status.setTextColor(Color.RED);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyViewModel.giveNotify(notify.getName_of_stock());
                Navigation.findNavController(view).navigate(R.id.action_notifications_to_notifyBigModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (notifies == null)
            return 0;
        return notifies.size();
    }
}
