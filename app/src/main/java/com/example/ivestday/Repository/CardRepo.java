package com.example.ivestday.Repository;

import com.example.ivestday.model.Card;

import java.util.ArrayList;

public class CardRepo {

    public ArrayList<Card> cards = new ArrayList<Card>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void AddNewCard(String number_card, String date_month, String CVV) {
        Card card = new Card(number_card,date_month,CVV);
        card.Create_performance();
        cards.add(card);
    }

    public Card getCardByNumber(String number_card) {
        Card result = null;
        for (int i = 0; i < cards.size(); i++) {
            if (number_card.equals(cards.get(i).getNumber_card())) {
                result = cards.get(i);
            }
        }
        return result;
    }

    public void DeleteCard(String number_card) {
        for (int i = 0; i < cards.size(); i++) {
            if (number_card.equals(cards.get(i).getNumber_card())) {
                cards.remove(i);
            }
        }
    }
}
