package com.example.stayfit.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.stayfit.CalculateBmiActivity;
import com.example.stayfit.login.LoginActivity;
import com.example.stayfit.R;
import com.example.stayfit.databinding.ActivitySignUpBinding;
import com.example.stayfit.models.User;
import com.example.stayfit.utils.Constants;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    SignupViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        String[] age = getResources().getStringArray(R.array.ageMenu);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_item,
                        age);
        binding.menuAge.setAdapter(adapter);

        String[] height = getResources().getStringArray(R.array.heightMenu);

        ArrayAdapter<String> adapterHeight =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_item,
                        height);

        binding.menuHeight.setAdapter(adapterHeight);

        setListeners();

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupUser();
            }
        });
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });


        viewModel.userRepository.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users) {
                    Log.d(Constants.TAG, "onChanged: " + user);
                }
            }
        });;

    }

    private void setListeners() {
        viewModel.isSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                if(isSuccess){
                    startActivity(new Intent(SignUpActivity.this, CalculateBmiActivity.class));
                }else{
                    Toast.makeText(SignUpActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signupUser() {
        if(!areFieldsValid())
            return;

        String firstName = binding.etFirstName.getText().toString().trim();
        String lastName = binding.etLastName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        String height = binding.menuHeight.getText().toString().trim();
        String age = binding.menuAge.getText().toString().trim();
        User user = new User(firstName,lastName,email,password,Float.parseFloat(height),Integer.parseInt(age));
        signupUserNow(user);


    }

    private void signupUserNow(User user) {
        viewModel.signupUser(user);
    }

    private boolean areFieldsValid() {

        return true;
    }

}
