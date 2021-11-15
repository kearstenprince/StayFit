package com.example.stayfit.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.stayfit.BMICalcUtils;
import com.example.stayfit.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {
    @Query("Select * from User")
    LiveData<List<User>> getAllUsers();

    @Query("Select * from User where userId = :id")
    LiveData<User> getUserById(int id);

    @Query("Select * from User where email = :email")
//    LiveData<User> getUserByEmail(String email);
    Single<User> getUserByEmail(String email);

    @Insert()
    Maybe<Long> insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete()
    void deleteUser(User user);
}
