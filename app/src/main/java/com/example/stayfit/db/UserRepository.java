package com.example.stayfit.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.stayfit.BMICalcUtils;
import com.example.stayfit.models.User;
import com.example.stayfit.utils.DBUtils;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<User> loggedInUser;
    private List<BMICalcUtils> getAllBmi;

    public List<BMICalcUtils> getGetAllBmi() {
        return getAllBmi;
    }



    public UserRepository() {

    }

    public LiveData<List<User>> getAllUsers() {
           return allUsers;
    }

    public UserRepository(Application application){
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();
        allUsers = userDao.getAllUsers();
        loggedInUser = userDao.getUserById(DBUtils.readIsUserLoggedId());
    }

    public Single<User> getUserByEmail(String email){

        return userDao.getUserByEmail(email);
    }

    public Maybe<Long> insertUser(User user ){

        return userDao.insertUser(user);
    }

    public LiveData<User> getLoggedInUser() {

        return loggedInUser;
    }


}
