package com.example.stayfit.login;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.stayfit.db.UserRepository;
import com.example.stayfit.models.User;
import com.example.stayfit.utils.Constants;
import com.example.stayfit.utils.DBUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends AndroidViewModel {

    UserRepository userRepository;
    MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();
    String errorMessage = "";
    public String message="";
    public LoginViewModel(@NonNull @NotNull Application application) {
        super(application);
        userRepository = new UserRepository(application);

    }

    public void loginUser(String email, String password) {
        userRepository.getUserByEmail(email)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull User user) {
                        Log.d(Constants.TAG, "onSuccess: Called");
                        if(user.getPassword().equals(password)){
                            Log.d(Constants.TAG, "onSuccess: Correct passwordd");
                            DBUtils.saveUserLoggedIn(user.getUserId());
                            isSuccess.postValue(true);
                        }else{
                            Log.d(Constants.TAG, "onSuccess: Wrong password");
                            errorMessage = "Entered password is incorrect";
                            isSuccess.postValue(false);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(Constants.TAG, "onError: " + e.getLocalizedMessage());
                        errorMessage = "Error: " + e.getLocalizedMessage();
                        isSuccess.postValue(false);
                    }
                });


    }
}
