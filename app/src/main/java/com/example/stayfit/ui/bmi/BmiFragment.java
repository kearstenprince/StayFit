package com.example.stayfit.ui.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.stayfit.CalculateBmiActivity;
import com.example.stayfit.databinding.FragmentBmiBinding;
import com.example.stayfit.db.UserDao;
import com.example.stayfit.db.UserDatabase;
import com.example.stayfit.db.UserRepository;
import com.example.stayfit.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BmiFragment extends Fragment {

    private BmiViewModel dashboardViewModel;
    private FragmentBmiBinding binding;
    double bmiDouble;

    //UserDatabase userDb = Room.databaseBuilder(getContext(),UserDatabase.class,"User_DB").allowMainThreadQueries().build();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(BmiViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        UserRepository repository = new UserRepository(requireActivity().getApplication());

        repository.getLoggedInUser().observe(getViewLifecycleOwner(), new Observer<User>() {
         @Override
         public void onChanged(User user) {
             bmiDouble = user.getBmi();
             Toast.makeText(getContext(), "My Bmi " + user.getFirstName(), Toast.LENGTH_SHORT).show();
         }
     });


     String tvBMI = new Double(bmiDouble).toString();

        Toast.makeText(getContext(), "BMI from here "+bmiDouble, Toast.LENGTH_SHORT).show();

      binding.tvBmiResult.setText(tvBMI);
      binding.btnChangeBmi.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         startActivity(new Intent(getActivity(), CalculateBmiActivity.class));
     }
 });


        return root;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}