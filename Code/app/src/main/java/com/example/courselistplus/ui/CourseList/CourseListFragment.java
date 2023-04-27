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
        coursesListView.setAdapter(new ArrayAdapter<CourseModel>(root.getContext(),
                android.R.layout.simple_list_item_1, dataAccessObject.getAllCourses()));

        // Initialize spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.search_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        searchButton.setOnClickListener((view) -> {
            List<CourseModel> searchResults = dataAccessObject.getMatchingCourses(
                    filterSpinner.getSelectedItem().toString(), querySearchView.getQuery().toString()
            );

            ArrayAdapter courseArrayAdapter = new ArrayAdapter<CourseModel>(
                    root.getContext(), android.R.layout.simple_list_item_1, searchResults);
            coursesListView.setAdapter(courseArrayAdapter);

            // TODO @abdi move this item click listener outside the search button click listener
            coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    CourseModel selectedItem = (CourseModel) adapterView.getItemAtPosition(i);
                    Intent myIntent = new Intent(getActivity(), CourseViewActivity.class);

                    myIntent.putExtra("courseTitleIntent", selectedItem.getCourseTitle());
                    myIntent.putExtra("courseIDIntent", selectedItem.getCourseID());
                    myIntent.putExtra("courseInstructorIntent", selectedItem.getCourseInstructor());
                    myIntent.putExtra("courseMeetTimeIntent", selectedItem.getMeetTime());
                    myIntent.putExtra("courseDescriptionIntent", selectedItem.getCourseDescription());
                    myIntent.putExtra("courseMeetDaysIntent", selectedItem.getMeetDays());
                    myIntent.putExtra("courseRatingIntent", "Course Overall Rating: " +
                            selectedItem.getOverallRating() + " (as rated by " +
                            selectedItem.getNumRatings() + " students!)");

                    startActivity(myIntent);
                }
            });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}