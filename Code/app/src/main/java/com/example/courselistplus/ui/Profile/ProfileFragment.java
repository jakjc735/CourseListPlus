package com.example.courselistplus.ui.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.courselistplus.R;
import com.example.courselistplus.databinding.FragmentCourselistBinding;
import com.example.courselistplus.databinding.FragmentProfileBinding;
import com.example.courselistplus.ui.ProfileActivity;

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

        Button myProfileButton = (Button)root.findViewById(R.id.nav_profile);

        myProfileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfileFragment.this.getActivity(), ProfileActivity.class);
                ProfileFragment.this.startActivity(myIntent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
