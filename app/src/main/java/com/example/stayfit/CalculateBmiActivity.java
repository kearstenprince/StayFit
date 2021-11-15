package com.example.stayfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.stayfit.databinding.ActivityCalculateBmiBinding;
import com.example.stayfit.databinding.FragmentSettingBinding;
import com.example.stayfit.db.UserDatabase;
import com.example.stayfit.db.UserRepository;
import com.example.stayfit.models.User;
import com.example.stayfit.ui.home.HomeFragment;
import com.example.stayfit.utils.Constants;

public class CalculateBmiActivity extends AppCompatActivity {

    ActivityCalculateBmiBinding bmiBinding;
    int weight = 64;
    int age = 24;

    double heightInCms,weightInKgs;
    double bmi;

    String bmiCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bmiBinding = ActivityCalculateBmiBinding.inflate(getLayoutInflater());
        setContentView(bmiBinding.getRoot());
        double heightInCms = Double.parseDouble(bmiBinding.tvHeight.getText().toString());
        double weightInKgs = Double.parseDouble(bmiBinding.tvWeight.getText().toString());


        double bmi = BMICalcUtils.getInstance().calculateBMIMetric(heightInCms, weightInKgs);
        bmiCategory= BMICalcUtils.getInstance().classifyBMI(bmi);

        User userBmi = new User(bmi);



        bmiBinding.btnCalculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              if(bmiBinding.tvWeight.length() ==0 || bmiBinding.tvHeight.length() == 1)
                {
                    Toast.makeText(CalculateBmiActivity.this, "Populate Weight and Height to Calculate BMI", Toast.LENGTH_SHORT).show();
                }
                else{

                  UserDatabase userDatabase = Room.databaseBuilder(CalculateBmiActivity.this, UserDatabase.class,"User_DB").allowMainThreadQueries().build();
                  userDatabase.userDao().insertUser(userBmi);
                  Intent i = new Intent(CalculateBmiActivity.this,BottomNavigation.class);
                  getBmi();
                  startActivity(i);
                }
            }
        }
        );
        bmiBinding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight--;
               bmiBinding.tvWeight.setText(String.valueOf(weight));
            }
        });
        bmiBinding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight++;
                bmiBinding.tvWeight.setText(String.valueOf(weight));
            }
        });

          bmiBinding.btnMinusAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    age--;
                bmiBinding.tvAge.setText(String.valueOf(age));
            }
        });
        bmiBinding.btnPlusAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                age++;
                bmiBinding.tvAge.setText(String.valueOf(age));
            }
        });



        bmiBinding.cvMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmiBinding.cvMale.setCardBackgroundColor(getResources().getColor(R.color.yelloColor));
                bmiBinding.cvFemale.setCardBackgroundColor(getResources().getColor(R.color.unselect_card_color));
            }
        });

        bmiBinding.cvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmiBinding.cvFemale.setCardBackgroundColor(getResources().getColor(R.color.yelloColor));
                bmiBinding.cvMale.setCardBackgroundColor(getResources().getColor(R.color.unselect_card_color));
            }
        });

        bmiBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 158;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress = i;
                bmiBinding.seekBar.setMax(200);
                bmiBinding.tvHeight.setText(seekBarProgress+"cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

    private void getBmi() {
        UserRepository myBMI= new UserRepository(getApplication());
        myBMI.getLoggedInUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

            }
        });


    }

}


