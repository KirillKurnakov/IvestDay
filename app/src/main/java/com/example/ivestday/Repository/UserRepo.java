package com.example.ivestday.Repository;

import android.content.Context;
import android.widget.Toast;

import com.example.ivestday.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    public List<User> users = new ArrayList<>();

    public UserRepo(/*String login, String password*/) {
        User user = new User();
        user.setLogin("d");
        user.setPassword("d");
        user.setPhone_number("89999999999");
        users.add(user);

        User user1 = new User();
        user1.setLogin("Kirill");
        user1.setPassword("Kurnakov");
        user1.setPass_data("Курнаков Кирилл Александрович");
        user1.setPhone_number("80009990000");
        user1.setEmail_address("gfdsa@gmail.com");
        user1.setPass_data_serial_number("1111123456");
        user1.setBank_check(1234);
        users.add(user1);
    }

    public void AddNewUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        users.add(user);
    }

    public User TopUpBank(String login, int number) {
        User user = null;
        user = getUserByLogin(login);
        user.setBank_check(number);
        return user;
    }

    public void login(Context context, String login, String password) {
        User user = new User();
        user = getUserByLogin(login);
        if (user.getPassword().equals(password)) { // если пользователь ввел правильный пароль
            Toast.makeText(context,"Успешная авторизация!",Toast.LENGTH_SHORT).show();
        } else { // если пароль неверный
            Toast.makeText(context,"Пароль неверный!",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isPasswordForUserValid(String login, String password) {
        User user = new User();
        user = getUserByLogin(login);
        if (user.getPassword().equals(password)) { // если пользователь ввел правильный пароль
            return true;
        }
        return false;
    }

    public User getUserByLogin(String login) {
        User result = null;
        for (int i = 0; i < users.size(); i++) {
           if (login.equals(users.get(i).getLogin())) {
               result = users.get(i);
           }
        }
        return result;
    }

    public void SaveEmail(String login, String email) {
        User user = getUserByLogin(login);
        user.setEmail_address(email);
    }

}
