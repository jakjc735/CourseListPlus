package com.example.courselistplus.ui.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.courselistplus.CourseModel;
import com.example.courselistplus.CourseViewActivity;
import com.example.courselistplus.DataAccessObject;
import com.example.courselistplus.R;
import com.example.courselistplus.databinding.FragmentCourselistBinding;

import java.util.List;

public class CourseListFragment extends Fragment {
    private FragmentCourselistBinding binding;
    Button searchButton;
    ListView coursesListView;
    SearchView querySearchView;
    Spinner filterSpinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCourselistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Initialize database
        DataAccessObject dataAccessObject = new DataAccessObject(root.getContext());

        // Initialize buttons
        searchButton = root.findViewById(R.id.searchButton);
        coursesListView = root.findViewById(R.id.coursesListView);
        querySearchView = root.findViewById(R.id.querySearchView);
        filterSpinner = (Spinner) root.findViewById(R.id.filterSpinner);

        // Pre-populate courses list view with all courses
        // This way, users may browse all courses before making a query
        CourseAdapter courseAdapter = new CourseAdapter(root.getContext(), R.layout.list_item,
                dataAccessObject.getAllCourses());
        coursesListView.setAdapter(courseAdapter);

        // Initialize spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.search_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        searchButton.setOnClickListener((view) -> {
            List<CourseModel> searchResults = dataAccessObject.getMatchingCourses(
                    filterSpinner.getSelectedItem().toString(), querySearchView.getQuery().toString()
            );

            CourseAdapter arrayAdapter = new CourseAdapter(root.getContext(), R.layout.list_item,
                    searchResults);
            coursesListView.setAdapter(arrayAdapter);
        });

        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                CourseModel selectedItem = (CourseModel) adapterView.getItemAtPosition(i);
                Intent myIntent = new Intent(getActivity(), CourseViewActivity.class);

                myIntent.putExtra("Course", selectedItem);

                startActivity(myIntent);
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