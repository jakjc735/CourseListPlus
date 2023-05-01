package com.example.courselistplus.ui.Profile;

import static com.example.courselistplus.R.id.nav_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.courselistplus.databinding.FragmentProfileBinding;


public class ProfileFragment  extends Fragment {

    // clicking on 'my profile' from navigation page sends user to my profile page
    Button myProfileButton;

    private FragmentProfileBinding binding;
    //TODO (Brandon) add content from profile xml

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //TODO (Brandon) add functionality to content from profile xml


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
