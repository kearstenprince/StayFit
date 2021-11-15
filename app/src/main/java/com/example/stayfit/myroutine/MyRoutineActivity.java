package com.example.stayfit.myroutine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.stayfit.R;
import com.example.stayfit.databinding.ActivityMyRoutineBinding;
import com.example.stayfit.myexercise.MyExerciseActivity;
import com.example.stayfit.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyRoutineActivity extends AppCompatActivity {

    ActivityMyRoutineBinding binding;
    List<MyRoutineItemList> myListData = new ArrayList<>();
    List<MyRoutineItemList> selectedExercises = new ArrayList<>();
    MyRoutineRecyclerViewAdapter myRoutineRecyclerViewAdapter;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyRoutineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String[] age = getResources().getStringArray(R.array.weekMenu);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_item,
                        age);
        binding.menuDay.setAdapter(adapter);

//        binding.menuDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               item= parent.getItemAtPosition(position).toString();
//
//                // create Toast with user selected value
//                Toast.makeText(MyRoutineActivity.this, " item is selected"+ item, Toast.LENGTH_SHORT).show();
//
//            }
//        });

        String[] height = getResources().getStringArray(R.array.hourMenu);

        ArrayAdapter<String> adapterHeight =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_item,
                        height);

        binding.menuHour.setAdapter(adapterHeight);


        MyRoutineItemList list = new MyRoutineItemList(R.drawable.img_mc, getString(R.string.Mountain_climber), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_ar, getString(R.string.Abs_roll_out), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_bd, getString(R.string.bird_dod), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_crunch, getString(R.string.crunches), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_db, getString(R.string.dead_bug), "ADD");
        myListData.add(list);

        list = new MyRoutineItemList(R.drawable.img_ke, getString(R.string.Hanging_knee_raise), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_lr, getString(R.string.leg_raise), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_plank, getString(R.string.Plank), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_rt, getString(R.string.Russian_twists), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_sp, getString(R.string.Side_plank), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_t, getString(R.string.knee_tucks), "ADD");
        myListData.add(list);
        list = new MyRoutineItemList(R.drawable.img_rc, getString(R.string.Russian_twists), "ADD");
        myListData.add(list);


        myRoutineRecyclerViewAdapter = new MyRoutineRecyclerViewAdapter(this, myListData);
        myRoutineRecyclerViewAdapter.setOnRoutineItemClickListener(new MyRoutineRecyclerViewAdapter.OnRoutineItemClickListener() {
            @Override
            public void onAddClicked(int position, MyRoutineItemList myRoutineItemList) {
                selectedExercises.add(myRoutineItemList);
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(myRoutineRecyclerViewAdapter);
        myRoutineRecyclerViewAdapter.notifyDataSetChanged();

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyRoutineActivity.this, MyExerciseActivity.class);
                intent.putExtra(Constants.KEY_EXERCISE_LIST,(Serializable) selectedExercises);
                //intent.putExtra("day",binding.menuDay.getText().toString());
                startActivity(intent);
            }
        });


    }
}