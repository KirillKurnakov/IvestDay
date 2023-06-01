package com.example.ivestday.model;

public class Card {

    private String Number_card;
    private String Date_and_month;
    private String CVV;
    private String Performance;

    public String getPerformance() {
        return Performance;
    }

    public Card(String Number_card,String Date_and_month,String CVV) {
        this.Number_card = Number_card;
        this.Date_and_month = Date_and_month;
        this.CVV = CVV;
    }

    public String getNumber_card() {
        return Number_card;
    }

    public void setNumber_card(String number_card) {
        Number_card = number_card;
    }

    public String getDate_and_month() {
        return Date_and_month;
    }

    public void setDate_and_month(String date_and_month) {
        Date_and_month = date_and_month;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public void Create_performance() {
        switch (Number_card.charAt(0)){
            case '2':
                Performance = "Mir ••••" + Number_card.substring(Number_card.length()-4,Number_card.length());
                break;
            case '4':
                Performance = "Visa ••••" + Number_card.substring(Number_card.length()-4,Number_card.length());
                break;
        }
    }
}
