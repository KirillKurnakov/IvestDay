package com.example.ivestday.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ivestday.MainActivity;
import com.example.ivestday.Repository.UserRepo;
import com.example.ivestday.model.User;

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> UserLiveData = new MutableLiveData<>();
    private UserRepo repository = MainActivity.newInstance();

    public void giveUser(String login) {
        UserLiveData.setValue(repository.getUserByLogin(login));
    }

    public MutableLiveData<User> getUser_m() {
        return UserLiveData;
    }

    public void MakeTransaction(String type, String quantity, String price, int how_) { // метод для пополнения после продажи, снятие после покупки акции
        if (type.equals("Акции")) {
            if (how_ == 0) { // при продаже ( начисление на счет, списание с "инверстировано" )
                User user = UserLiveData.getValue();
                Integer helper = Integer.parseInt(user.getStock_market_account()); // текущее число денег на счету
                user.setStock_market_account(Integer.toString(helper + (Integer.parseInt(quantity) * Integer.parseInt(price))));
                helper = Integer.parseInt(user.getStock_market_account_invest());
                user.setStock_market_account_invest(Integer.toString(helper - (Integer.parseInt(quantity) * Integer.parseInt(price))));
            } else if (how_ == 1) { // при покупке ( списание со счета, начисление на "инверстировано" )
                User user = UserLiveData.getValue();
                Integer helper = Integer.parseInt(user.getStock_market_account()); // текущее число денег на счету
                user.setStock_market_account(Integer.toString(helper - (Integer.parseInt(quantity) * Integer.parseInt(price))));
                helper = Integer.parseInt(user.getStock_market_account_invest());
                user.setStock_market_account_invest(Integer.toString(helper + (Integer.parseInt(quantity) * Integer.parseInt(price))));
            }
        } else {
            if (how_ == 0) { // при продаже ( начисление на счет, списание с "инверстировано" )
                User user = UserLiveData.getValue();
                Integer helper = Integer.parseInt(user.getCurrency_market_account()); // текущее число денег на счету
                user.setCurrency_market_account(Integer.toString(helper + (Integer.parseInt(quantity) * Integer.parseInt(price))));
                helper = Integer.parseInt(user.getCurrency_market_account_invest());
                user.setCurrency_market_account_invest(Integer.toString(helper - (Integer.parseInt(quantity) * Integer.parseInt(price))));
            } else if (how_ == 1) { // при покупке ( списание со счета, начисление на "инверстировано" )
                User user = UserLiveData.getValue();
                Integer helper = Integer.parseInt(user.getCurrency_market_account()); // текущее число денег на счету
                user.setCurrency_market_account(Integer.toString(helper - (Integer.parseInt(quantity) * Integer.parseInt(price))));
                helper = Integer.parseInt(user.getCurrency_market_account_invest());
                user.setCurrency_market_account_invest(Integer.toString(helper + (Integer.parseInt(quantity) * Integer.parseInt(price))));
            }
        }
    }

    public boolean CheckAddedCard() { // проверка наличия добавленных пользователем карт

        if (UserLiveData.getValue().getCard_list() == null) {
            return false;
        }
        return true;

    }

    public boolean loginViewModel(Context context, String login, String password) {

        if ((repository.getUserByLogin(login)) == null) { // если такого юзера нет
            Toast.makeText(context, "Пользователя с таким именем нет!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (repository.isPasswordForUserValid(login,password)) {
            giveUser(login); // mutablelivedata назначим текущего юзера
            Toast.makeText(context,"Успешная авторизация!",Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context,"Пароль неверный!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean CheckAlreadyRegistered(Context context, String login) {

        if ((repository.getUserByLogin(login)) != null) {  // если пользователь уже зареган
            Toast.makeText(context.getApplicationContext(), repository.getUserByLogin(login).getLogin(),
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(context.getApplicationContext(), repository.getUserByLogin(login).getPassword(),
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Пользователь с таким именем уже существует!",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void addNewUser(String login, String password) {
        repository.AddNewUser(login,password);
        //giveUser(login); // mutablelivedata назначим текущего юзера
    }

    public void SaveEmail(String email) {
        UserLiveData.getValue().setEmail_address(email);
        //repository.SaveEmail(UserLiveData.getValue().getLogin(),email);
    }

    public void SavePhone(String phone) {
        UserLiveData.getValue().setPhone_number(phone);
    }

    public void Save_Pass_Data(String FIO, String SerialNumber, String DateBirth ) {
        UserLiveData.getValue().setPass_data(FIO);
        UserLiveData.getValue().setPass_data_serial_number(SerialNumber);
        UserLiveData.getValue().setDate_birth(DateBirth);
    }

    public void TopUp(String type_of_stock,String number) {
        if (type_of_stock.equals("Фондовый рынок")) {
            int g = Integer.parseInt(UserLiveData.getValue().getStock_market_account()); // текущий баланс
            int g1 = Integer.parseInt(number);
            UserLiveData.getValue().setStock_market_account(Long.toString(g + g1));
        } else {
            long g = Integer.parseInt(UserLiveData.getValue().getCurrency_market_account()); // текущий баланс
            long g1 = Integer.parseInt(number);
            UserLiveData.getValue().setCurrency_market_account(Long.toString(g + g1));
        }
    }

    public boolean Withdraw(Context c,String type_of_stock, String number) {
        if (type_of_stock.equals("Фондовый рынок")) {
            long g = Integer.parseInt(UserLiveData.getValue().getStock_market_account()); // текущий баланс
            long g1 = Integer.parseInt(number);
            if (g1 > g) {
                Toast.makeText(c,"Недостаточно денег на счету", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                UserLiveData.getValue().setStock_market_account(Long.toString(g - g1));
                return true;
            }
        } else {
            long g = Integer.parseInt(UserLiveData.getValue().getCurrency_market_account()); // текущий баланс
            long g1 = Integer.parseInt(number);
            if (g1 > g) {
                Toast.makeText(c,"Недостаточно денег на счету", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                UserLiveData.getValue().setCurrency_market_account(Long.toString(g - g1));
                return true;
            }
        }
    }
}
