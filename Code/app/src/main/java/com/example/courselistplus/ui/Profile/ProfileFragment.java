package com.example.courselistplus.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.courselistplus.databinding.FragmentCourselistBinding;
import com.example.courselistplus.databinding.FragmentProfileBinding;

public class ProfileFragment  extends Fragment {

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
