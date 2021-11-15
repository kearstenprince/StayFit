package com.example.stayfit.signup;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.ForeignKey;

import com.example.stayfit.db.UserRepository;
import com.example.stayfit.models.User;
import com.example.stayfit.utils.Constants;
import com.example.stayfit.utils.DBUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignupViewModel extends AndroidViewModel {

    UserRepository userRepository;

    MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();

    public SignupViewModel(@NonNull @NotNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void signupUser(User user) {
        userRepository.insertUser(user)
                .subscribeOn(Schedulers.io())
                .subscribe(new MaybeObserver<Long>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                        DBUtils.saveUserLoggedIn(aLong.intValue());
                        isSuccess.postValue(true);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        isSuccess.postValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
