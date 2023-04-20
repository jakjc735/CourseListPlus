package com.example.courselistplus.ui.CourseList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.courselistplus.R;
import com.example.courselistplus.databinding.FragmentCourseviewBinding;

public class CourseViewFragment extends Fragment {

    private FragmentCourseviewBinding binding;

    Button addButton;
    Button rateButton;

    TextView courseName;
    TextView courseRating;
    TextView courseId;
    TextView instructor;
    TextView courseTime;
    TextView courseDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCourseviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Initalize Buttons
        addButton = root.findViewById(R.id.AddButton);
        rateButton = root.findViewById(R.id.RateButton);

        //Initalize Textviews
        courseName = root.findViewById(R.id.CourseName);
        courseRating = root.findViewById(R.id.CourseRating);
        courseId = root.findViewById(R.id.CourseId);
        instructor = root.findViewById(R.id.Instructor);
        courseTime = root.findViewById(R.id.CourseTime);
        courseDescription = root.findViewById(R.id.CourseDescription);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}
