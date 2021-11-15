package com.example.stayfit.onboarding;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;

import com.example.stayfit.R;
import com.example.stayfit.signup.SignUpActivity;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroCustomLayoutFragment;

import org.jetbrains.annotations.Nullable;

public class OnBoardScreens extends AppIntro {
    private Object Fragment;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setIndicatorColor(
                this.getResources().getColor(R.color.white),
                this.getResources().getColor(R.color.white_light)

        );

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.onboard_layout));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.onboard_layout2));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.onboard_layout3));

    }
    @Override
    public void onDonePressed(androidx.fragment.app.Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }

    @Override
    protected void onSkipPressed(@Nullable androidx.fragment.app.Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }
}
