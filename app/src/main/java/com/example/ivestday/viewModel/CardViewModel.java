package com.example.ivestday.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ivestday.MainActivity;
import com.example.ivestday.Repository.CardRepo;
import com.example.ivestday.Repository.UserRepo;
import com.example.ivestday.model.Card;

public class CardViewModel extends ViewModel {

    private MutableLiveData<Card> CardLiveData = new MutableLiveData<>();
    private UserViewModel user = MainActivity.getUserViewModel();
    private UserRepo repository = MainActivity.newInstance();
    private CardRepo cardRepo = MainActivity.getCardRepo();

    public void CreateNewCard(Context c, String number_card, String date_month, String CVV) {

        if (cardRepo.getCardByNumber(number_card) != null) {
            Toast.makeText(c, "Карта уже существует!", Toast.LENGTH_SHORT).show();
        } else {
            cardRepo.AddNewCard(number_card, date_month, CVV);
            user.getUser_m().getValue().setCard_list(cardRepo.getCards());
            Toast.makeText(c, "Карта успешно добавлена!", Toast.LENGTH_SHORT).show();
        }
    }

    public void giveCard(String number_card) {
        CardLiveData.setValue(cardRepo.getCardByNumber(number_card));
    }

    public MutableLiveData<Card> getCard() {
        return CardLiveData;
    }

    public void DeleteCard() {
        cardRepo.DeleteCard(CardLiveData.getValue().getNumber_card());
        user.getUser_m().getValue().setCard_list(cardRepo.getCards());
    }

}
