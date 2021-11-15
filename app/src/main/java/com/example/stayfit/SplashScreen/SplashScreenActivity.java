package com.example.stayfit.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.stayfit.CalculateBmiActivity;
import com.example.stayfit.MainActivity;
import com.example.stayfit.R;
import com.example.stayfit.databinding.ActivitySplashScreenBinding;
import com.example.stayfit.onboarding.OnBoardScreens;
import com.example.stayfit.utils.DBUtils;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(DBUtils.readIsUserLoggedId() != -1){
                    startActivity(new Intent(SplashScreenActivity.this, CalculateBmiActivity.class));
                    finish();
                    return;
                }
                startActivity(new Intent(SplashScreenActivity.this, OnBoardScreens.class));
                finish();
            }
        },1000);
//        },3000);
    }
}