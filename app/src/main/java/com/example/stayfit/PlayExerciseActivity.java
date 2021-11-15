package com.example.stayfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.stayfit.databinding.ActivityPlayExerciseBinding;
import com.example.stayfit.myexercise.MyExerciseActivity;
import com.example.stayfit.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PlayExerciseActivity extends AppCompatActivity {

    int count = 1;
    String start = "START";
    String complete = "COMPLETE";
    String completed = "COMPLETED";
    String nextstep = "NEXT SET";
    ActivityPlayExerciseBinding binding;
    Bundle bundle;
    int resID;
    String title;
    boolean disable=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        bundle = getIntent().getExtras();
        if (bundle != null) {
            resID = bundle.getInt(Constants.KEY_IMAGE);
            title = bundle.getString(Constants.KEY_TITLE);

        }
        binding.tvExerciseTitle.setText(title);
        binding.ivExerciseTodo.setImageResource(resID);

        binding.btnStartExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count>3){
                    Intent i = new Intent(PlayExerciseActivity.this, MyExerciseActivity.class);
                    i.putExtra("disable",disable);
                    i.putExtra("text",complete);
                    startActivity(i);
                }

                start = binding.btnStartExercise.getText().toString();
                Log.d(Constants.TAG, "onClick: start = " + start);
                if (start.equals("START")) {


                    binding.btnStartExercise.setText("STARTED");
                    binding.btnComplete.setBackgroundColor(getResources().getColor(R.color.unselect_card_color));
                    binding.btnComplete.setEnabled(true);
                    binding.btnStartExercise.setEnabled(false);
                } else if (start.equals(nextstep)) {


                    binding.btnStartExercise.setText("START");
                    binding.btnComplete.setBackgroundColor(getResources().getColor(R.color.unselect_card_color));
                    binding.btnStartExercise.setBackgroundColor(getResources().getColor(R.color.yelloColor));
                    count++;
                    binding.tvSetCount.setText("SET " + count);
                    binding.btnComplete.setText("COMPLETE");
                    binding.btnComplete.setEnabled(false);

                }

            }
        });
        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                complete = binding.btnComplete.getText().toString();
                Log.d(Constants.TAG, "onClick: complete = " + complete);
                if (complete.equals("COMPLETE")) {


                    binding.btnComplete.setText("COMPLETED");
                    binding.btnComplete.setBackgroundColor(getResources().getColor(R.color.yelloColor));
                    binding.btnStartExercise.setBackgroundColor(getResources().getColor(R.color.unselect_card_color));
                    binding.btnComplete.setEnabled(false);
                    binding.btnStartExercise.setText("NEXT SET");
                    binding.btnStartExercise.setEnabled(true);

                } else if (complete.equals("COMPLETED")) {

                    binding.btnComplete.setText("COMPLETE");
                    binding.btnComplete.setBackgroundColor(getResources().getColor(R.color.unselect_card_color));
                    binding.btnStartExercise.setBackgroundColor(getResources().getColor(R.color.yelloColor));
                    binding.btnStartExercise.setEnabled(true);
                    binding.btnComplete.setEnabled(false);

                }
            }

        });

    }
}