package com.example.stayfit.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.stayfit.BMICalcUtils;
import com.example.stayfit.models.User;

@Database(entities = User.class, exportSchema = false,  version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DB_NAME = "User_DB";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,
                    DB_NAME)
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}
