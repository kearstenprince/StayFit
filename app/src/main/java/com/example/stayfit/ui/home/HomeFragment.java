package com.example.stayfit.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.esotericsoftware.minlog.Log;
import com.example.stayfit.R;
import com.example.stayfit.databinding.FragmentHomeBinding;
import com.example.stayfit.db.UserRepository;
import com.example.stayfit.models.User;
import com.example.stayfit.myroutine.MyRoutineActivity;
import com.example.stayfit.myroutine.MyRoutineItemList;
import com.example.stayfit.utils.Constants;
import com.example.stayfit.utils.DBUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    double getBmi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        UserRepository user = new UserRepository(requireActivity().getApplication());
        user.getLoggedInUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
           //// get BMI Result
            }
        });


        SimpleDateFormat CurrentMonth = new SimpleDateFormat("MMM");
        SimpleDateFormat CurrentDate= new SimpleDateFormat("dd");
        SimpleDateFormat CurrentYear = new SimpleDateFormat("yyyy");
        Calendar cal=Calendar.getInstance();
        String month_name = CurrentMonth.format(cal.getTime());
        String date = CurrentDate.format(cal.getTime());
        String year = CurrentYear.format(cal.getTime());
        date= month_name+" "+date+" ," +year;
        binding.tvDate.setText(date);





        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyRoutineActivity.class));
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
            }
        });


//      binding.btnMenu.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//            binding.drawerLayout.openDrawer(Gravity.LEFT);
//          }
//      });
        return root;
    }
}