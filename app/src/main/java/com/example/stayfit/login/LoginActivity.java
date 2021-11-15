package com.example.stayfit.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.stayfit.CalculateBmiActivity;
import com.example.stayfit.databinding.ActivityLoginBinding;
import com.example.stayfit.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        viewModel.isSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                if(isSuccess){
                    startActivity(new Intent(LoginActivity.this, CalculateBmiActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, viewModel.errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginUser() {
        if (!areFieldsValid())
            return;

        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        viewModel.loginUser(email,password);

    }

    private boolean areFieldsValid() {
        return true;
    }

}