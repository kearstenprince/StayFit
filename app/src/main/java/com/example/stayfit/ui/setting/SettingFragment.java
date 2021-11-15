package com.example.stayfit.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.stayfit.databinding.FragmentSettingBinding;
import com.example.stayfit.db.UserDao;
import com.example.stayfit.db.UserDatabase;
import com.example.stayfit.db.UserRepository;
import com.example.stayfit.login.LoginActivity;
import com.example.stayfit.models.User;
import com.example.stayfit.signup.SignUpActivity;
import com.example.stayfit.signup.SignupViewModel;
import com.example.stayfit.utils.DBUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private FragmentSettingBinding binding;
    SignupViewModel viewModel;
    UserDatabase user;
    LiveData<User> loggedInUser;
    String firstName, lastName,emailAddres;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        UserRepository repository = new UserRepository(requireActivity().getApplication());
        repository.getLoggedInUser().observe(getViewLifecycleOwner(), new Observer<User>() {

            @Override
            public void onChanged(User user) {
                firstName = user.getFirstName();
                lastName = user.getLastName();
                emailAddres= user.getEmail();
                binding.etFirstName.setText(firstName);
                binding.etLastName.setText(lastName);
                binding.etEmail.setText(emailAddres);

            }
        });



        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBUtils.clearCurrentUser();
                startActivity(new Intent(requireContext(), LoginActivity.class));
                requireActivity().finish();
            }
        });

        return root;
    }

//    private void setUpDatabase() {
//
//
//
//    }

//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}