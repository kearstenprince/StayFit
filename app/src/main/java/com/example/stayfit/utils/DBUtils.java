package com.example.stayfit.utils;

import io.paperdb.Paper;

public class DBUtils {

    public static void saveUserLoggedIn(int id){
        Paper.book(Constants.BOOK_CONFIG).write(Constants.KEY_LOGGED_IN_USER, id);
    }

    public static int readIsUserLoggedId(){
        return Paper.book(Constants.BOOK_CONFIG).read(Constants.KEY_LOGGED_IN_USER, -1);
    }

    public static void clearCurrentUser(){
        Paper.book(Constants.BOOK_CONFIG).delete(Constants.KEY_LOGGED_IN_USER);
    }
}
