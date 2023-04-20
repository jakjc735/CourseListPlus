package com.example.courselistplus.ui.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

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

        // Initialize buttons
        searchButton = root.findViewById(R.id.searchButton);
        coursesListView = root.findViewById(R.id.coursesListView);
        querySearchView = root.findViewById(R.id.querySearchView);
        filterSpinner = (Spinner) root.findViewById(R.id.filterSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(), R.array.search_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        searchButton.setOnClickListener((view) -> {
            DataAccessObject dataAccessObject = new DataAccessObject(root.getContext());
            List<CourseModel> searchResults = dataAccessObject.getMatchingCourses(filterSpinner.getSelectedItem().toString(), querySearchView.getQuery().toString());



            ArrayAdapter courseArrayAdapter = new ArrayAdapter<CourseModel>(
                    root.getContext(), android.R.layout.simple_list_item_1, searchResults);
            coursesListView.setAdapter(courseArrayAdapter);

            Log.d("Search Results", searchResults.toString());



            coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                    List<CourseModel> selectedItem = (List<CourseModel>) adapterView.getItemAtPosition(i);

//                    Log.d("Selected Item", selectedItem);


                    Intent myIntent = new Intent(getActivity(), CourseViewActivity.class);
                    myIntent.putExtra("selectedItem", (CharSequence) selectedItem);
                    startActivity(myIntent);







                }
            });
        });

        return root;
    }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
}