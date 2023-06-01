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
import com.example.ivestday.databinding.PurchaseRequestBinding;
import com.example.ivestday.viewModel.StockViewModel;
import com.example.ivestday.viewModel.UserViewModel;

public class PurchaseRequest extends Fragment {

    private PurchaseRequestBinding binding;
    private UserViewModel user;
    private StockViewModel stock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PurchaseRequestBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        stock = MainActivity.getStockViewModel();
        binding.purchaseRequestTopApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_purchaseRequest_to_stockBigModel);
            }
        });
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stock.getStockLive().observe(getViewLifecycleOwner(),stock1 -> {
            // добавить binding для доступно средств:
            binding.purchaseRequestTextViewBuyStock.setText("Купить " + stock1.getName());
            binding.purchaseRequestTextViewBuyStock.setTypeface(null, Typeface.BOLD);
            binding.purchaseRequestChangePrice.setText(Integer.toString(stock1.getPrice()));
            user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
                if (stock1.getType().equals("Акции")) {
                    binding.purchaseRequestWillChangeAvailablePrice.setText("Доступно средств: " + user1.getStock_market_account() + " ₽");
                } else {
                    binding.purchaseRequestWillChangeAvailablePrice.setText("Доступно средств: " + user1.getCurrency_market_account() + " ₽");
                }

                binding.purchaseRequestButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String g = binding.purchaseRequestChangeAmountForUser.getText().toString(); // считали кол-во лотов
                        if (!(g.length() == 0)) {
                            Integer helper = Integer.valueOf(g); // получили как число кол-во лотов
                            if (helper == 0) {
                                Toast.makeText(getContext(),"Минимальное число лотов 1",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                g = binding.purchaseRequestChangeAmountForUser.getText().toString(); // количество лотов
                                Integer helper_1 = Integer.valueOf(g);
                                if (stock1.getType().equals("Акции")) {
                                    if (Integer.parseInt(user1.getStock_market_account()) < (helper_1*stock1.getPrice())) {
                                    Toast.makeText(getContext(),"Недостаточно средств!",Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (stock1.getUsersQuantity().equals("0")) { // пользователь только покупает ценную бумагу ( 0 лотов на счету)
                                            stock.BuyStockNew(stock1.getName(),g,Integer.toString(stock1.getPrice()),stock1.getType());
                                            Toast.makeText(getContext(),"Покупка прошла успешно!",Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(view).navigate(R.id.action_purchaseRequest_to_stockBigModel);
                                        } else {
                                            stock.BuyStock(stock1.getName(),g,Integer.toString(stock1.getPrice()),stock1.getType());
                                            Toast.makeText(getContext(),"Покупка прошла успешно!",Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(view).navigate(R.id.action_purchaseRequest_to_stockBigModel);
                                        }
                                    }
                                } else {
                                    if (Integer.parseInt(user1.getCurrency_market_account()) < (helper_1*stock1.getPrice())) {
                                        Toast.makeText(getContext(),"Недостаточно средств!",Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (stock1.getUsersQuantity().equals("0")) { // пользователь только покупает ценную бумагу ( 0 лотов на счету)
                                            stock.BuyStockNew(stock1.getName(),g,Integer.toString(stock1.getPrice()),stock1.getType());
                                            Toast.makeText(getContext(),"Покупка прошла успешно!",Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(view).navigate(R.id.action_purchaseRequest_to_stockBigModel);
                                        } else {
                                            stock.BuyStock(stock1.getName(),g,Integer.toString(stock1.getPrice()),stock1.getType());
                                            Toast.makeText(getContext(),"Покупка прошла успешно!",Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(view).navigate(R.id.action_purchaseRequest_to_stockBigModel);
                                        }
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(getContext(),"Введите количество лотов. Минимум 1",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            });
        });



        binding.purchaseRequestChangeAmountForUser.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                long g = Integer.parseInt(binding.purchaseRequestChangePrice.getText().toString());
                if (charSequence.toString().isEmpty()) {
                    binding.purchaseRequestChangeFinalPrice.setText(Long.toString(g*0));
                } else {
                    long g1 = Integer.parseInt(charSequence.toString());
                    binding.purchaseRequestChangeFinalPrice.setText(Long.toString(g * g1));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}
