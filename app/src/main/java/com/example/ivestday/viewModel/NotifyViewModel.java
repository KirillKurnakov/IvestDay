package com.example.ivestday.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ivestday.MainActivity;
import com.example.ivestday.Repository.NotifyRepo;
import com.example.ivestday.Repository.UserRepo;
import com.example.ivestday.model.Notify;

public class NotifyViewModel extends ViewModel {

    private MutableLiveData<Notify> NotifyLiveData = new MutableLiveData<>();
    private UserViewModel user = MainActivity.getUserViewModel();
    private UserRepo repository = MainActivity.newInstance();
    private NotifyRepo notifyRepo = MainActivity.getNotifyRepo();

    public void giveNotify(String name) {
        NotifyLiveData.setValue(notifyRepo.getNotifyByName(name));
    }

    public MutableLiveData<Notify> getUser_m() {
        return NotifyLiveData;
    }


    public void CreateNotify(Context c, String name, String desire_price, String type_of_growth) {
        if (notifyRepo.getNotifyByName(name) != null) {
            Toast.makeText(c, "Уведомление уже существует для " + name, Toast.LENGTH_SHORT).show();
        } else {
            notifyRepo.AddNewNotify(name, desire_price, type_of_growth);
            user.getUser_m().getValue().setNotify_users(notifyRepo.getNotifies());
            Toast.makeText(c, "Уведомление создано!", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteNotify() {
        notifyRepo.DeleteNotify(NotifyLiveData.getValue().getName_of_stock());
        user.getUser_m().getValue().setNotify_users(notifyRepo.getNotifies());
    }
}
