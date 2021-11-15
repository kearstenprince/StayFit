package com.example.stayfit.myexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.stayfit.PlayExerciseActivity;
import com.example.stayfit.databinding.ActivityMyExerciseBinding;
import com.example.stayfit.myroutine.MyRoutineItemList;
import com.example.stayfit.myroutine.MyRoutineRecyclerViewAdapter;
import com.example.stayfit.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyExerciseActivity extends AppCompatActivity {

    ActivityMyExerciseBinding binding;
    String passString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SimpleDateFormat CurrentMonth = new SimpleDateFormat("MMM");
        SimpleDateFormat CurrentDate= new SimpleDateFormat("dd");
        SimpleDateFormat CurrentYear = new SimpleDateFormat("yyyy");
        Calendar cal=Calendar.getInstance();
        String month_name = CurrentMonth.format(cal.getTime());
        String date = CurrentDate.format(cal.getTime());
        String year = CurrentYear.format(cal.getTime());
        date= month_name+" "+date+" ," +year;

        binding.tvDate.setText(date);

//        Bundle bundle = getIntent().getExtras();
//        if(bundle!= null){
//            resID = bundle.getString("day");
//        }

        //binding.tvToday.setText(resID+"day");

        ArrayList<MyRoutineItemList> selectedExercises =
                (ArrayList<MyRoutineItemList>) getIntent().getSerializableExtra(Constants.KEY_EXERCISE_LIST);
        if(selectedExercises != null){
            MyExerciseAdapter  adapter = new MyExerciseAdapter(selectedExercises,getApplicationContext());
            binding.rvExercise.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            binding.rvExercise.setItemAnimator(new DefaultItemAnimator());
            binding.rvExercise.setAdapter(adapter);
            binding.rvExercise.hasFixedSize();
            adapter.notifyDataSetChanged();
            adapter.setOnStartClickListener(new MyExerciseAdapter.onStartClickListener() {
                @Override
                public void onAddClick(int img, String title) {
                    Intent i = new Intent(MyExerciseActivity.this, PlayExerciseActivity.class);
                    i.putExtra(Constants.KEY_IMAGE,img);
                    i.putExtra(Constants.KEY_TITLE,title);
                    startActivity(i);
                }
            });
        }else{
            Toast.makeText(this, "Exercises list is empty", Toast.LENGTH_SHORT).show();
        }


    }
}