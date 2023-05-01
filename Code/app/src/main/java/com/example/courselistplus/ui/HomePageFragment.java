package com.example.courselistplus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.courselistplus.CourseModel;
import com.example.courselistplus.CourseViewActivity;
import com.example.courselistplus.DataAccessObject;
import com.example.courselistplus.R;
import com.example.courselistplus.databinding.FragmentCourselistBinding;
import com.example.courselistplus.databinding.FragmentHomePageBinding;
import com.example.courselistplus.ui.CourseList.CourseAdapter;

public class HomePageFragment extends Fragment {

    ListView popularCourses;

    private FragmentHomePageBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DataAccessObject dataAccessObject = new DataAccessObject(root.getContext());

        popularCourses = root.findViewById(R.id.popularCourseListView);

        CourseAdapter courseAdapter = new CourseAdapter(root.getContext(), R.layout.list_item,
                dataAccessObject.getAllCourses());
        popularCourses.setAdapter(courseAdapter);

        popularCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                CourseModel selectedItem = (CourseModel) adapterView.getItemAtPosition(i);
                Intent myIntent = new Intent(getActivity(), CourseViewActivity.class);

                myIntent.putExtra("Course", selectedItem);

                startActivity(myIntent);
            }
        });

        return root;
        //        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }
}