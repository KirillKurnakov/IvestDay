package com.example.ivestday.Repository;

import com.example.ivestday.model.Notify;

import java.util.ArrayList;

public class NotifyRepo {

    public ArrayList<Notify> notifies = new ArrayList<Notify>();

    public ArrayList<Notify> getNotifies() {
        return notifies;
    }

    public Notify AddNewNotify(String name, String desire_price, String type_of_growth) {
        Notify notify = new Notify(name,type_of_growth + " " + desire_price + " ₽","В процессе");
        notifies.add(notify);
        return notify;
    }

    public void DeleteNotify(String name) {
        for (int i = 0; i < notifies.size(); i++) {
            if (name.equals(notifies.get(i).getName_of_stock())) {
                notifies.remove(i);
            }
        }
    }

    public Notify getNotifyByName(String name) {
        Notify result = null;
        for (int i = 0; i < notifies.size(); i++) {
            if (name.equals(notifies.get(i).getName_of_stock())) {
                result = notifies.get(i);
            }
        }
        return result;
    }

}
