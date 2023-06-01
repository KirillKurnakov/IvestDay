package com.example.ivestday.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.ivestday.Repository.StockRepo;
import com.example.ivestday.databinding.SaleRequestBinding;
import com.example.ivestday.viewModel.StockViewModel;
import com.example.ivestday.viewModel.UserViewModel;

public class Sale_request extends Fragment { // запрос на продажу ценной бумаги

    private SaleRequestBinding binding;
    private UserViewModel user;
    private StockViewModel stock;
    private StockRepo stockRepo;

    public Sale_request() {
    }

    public static Sale_request newInstance() {
        return new Sale_request();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SaleRequestBinding.inflate(inflater,container,false);
        stockRepo = MainActivity.getStockRepo();
        user = MainActivity.getUserViewModel();
        stock = MainActivity.getStockViewModel();
        binding.topAppSaleRequest.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_sale_request_to_stockBigModel);
            }
        });
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stock.getStockLive().observe(getViewLifecycleOwner(),stock1 -> {

            binding.saleRequestTextView.setText("Продать " + stock1.getName());
            binding.saleRequestTextView.setTypeface(null, Typeface.BOLD);
            binding.currentPriceWillChange.setText(Integer.toString(stock1.getPrice()));
            binding.saleRequestWillCount2.setText("Доступно лотов: " + stock1.getUsersQuantity());

            binding.saleRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String g = binding.fieldForFill.getText().toString(); // считали кол-во лотов
                    if (!(g.length() == 0)) {
                        Integer helper = Integer.valueOf(g); // получили как число кол-во лотов
                        if (helper == 0) {
                            Toast.makeText(getContext(),"Минимальное число лотов 1",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            g = binding.fieldForFill.getText().toString();
                            Integer helper_1 = Integer.valueOf(g);
                            if (helper_1 > Integer.parseInt(stock1.getUsersQuantity().toString())) {
                                Toast.makeText(getContext(),"Количество лотов выше доступного!",Toast.LENGTH_SHORT).show();
                            } else {
                                stock.SaleStock(stock1.getName(),g,Integer.toString(stock1.getPrice()),stock1.getType());
                                Toast.makeText(getContext(),"Продажа прошла успешно!",Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.action_sale_request_to_stockBigModel);
                            }
                        }
                    } else {
                        Toast.makeText(getContext(),"Введите количество лотов. Минимум 1",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });

        binding.fieldForFill.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                long g = Integer.parseInt(binding.currentPriceWillChange.getText().toString());
                if (charSequence.toString().isEmpty()) {
                    binding.saleRequestWillCount.setText(Long.toString(g*0));
                } else {
                    long g1 = Integer.parseInt(charSequence.toString());
                    binding.saleRequestWillCount.setText(Long.toString(g * g1));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}
