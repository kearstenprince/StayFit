package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.stayfit.databinding.FragmentHomeBinding;
import com.example.stayfit.db.UserDatabase;
import com.example.stayfit.db.UserRepository;
import com.example.stayfit.models.User;
import com.example.stayfit.ui.home.HomeFragment;
import com.example.stayfit.utils.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.stayfit.databinding.ActivityBottomNavigationBinding;

import java.util.Arrays;
import java.util.List;

public class BottomNavigation extends AppCompatActivity {

    private ActivityBottomNavigationBinding binding;
   double getHeight,getWeight;
   String bmiCategory;
    UserDatabase userDatabase;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each.


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_bmi, R.id.navigation_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_navigation);
       // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView,navController);
    }

}