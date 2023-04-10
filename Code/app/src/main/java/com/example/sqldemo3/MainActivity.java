package com.example.sqldemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    Button showAllCoursesButton;
    Button searchButton;
    ListView coursesListView;
    SearchView querySearchView;
    Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
//        showAllCoursesButton = findViewById(R.id.showAllCoursesButton);
        searchButton = findViewById(R.id.searchButton);
        coursesListView = findViewById(R.id.coursesListView);
        querySearchView = findViewById(R.id.querySearchView);
        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);

        ArrayAdapter<String> adapter;
        List<String> list = new ArrayList<String>();
        list.add("CRN");
        list.add("Credits");
        list.add("Meet Days");
        list.add("Meet Time");
        list.add("Instructor");

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

//        // Hard-coded courses to prepopulate the database on every run
//        CourseModel emergingDiseases = new CourseModel(-1, 21621,
//                "CHEM 150 01", "C150", "Emerging Diseases",
//                "Sher, Beverly", 4, "MWF", "1200-1250",
//                17, 16, "Open");
//        CourseModel entrepreneurshipInCS = new CourseModel(-1, 24354,
//                "CSCI 425 01", "C400, IN", "Entrepreneurship in CS",
//                "Kemper, Peter", 3, "TR", "1100-1220",
//                45, 44, "Open");
//        CourseModel dataMining = new CourseModel(-1, 27294,
//                "CSCI 436 01", "None", "Data Mining",
//                "Shao, Huajie", 3, "MW", "1400-1520",
//                40, 27, "Open");
//        CourseModel operatingSystems = new CourseModel(-1, 25722,
//                "CSCI 444 01", "None", "Operating Systems",
//                "Kumar, Pradeep", 3, "TR", "1230-1350",
//                40, 35, "Open");
//        CourseModel computerAndNetworkSecurity = new CourseModel(-1, 24957,
//                "CSCI 454 01", "None", "Computer and Network Security",
//                "Evtyushkin, Dmitry", 3, "TR", "1400-1520",
//                40, 34, "Open");
//
//        DataAccessObject dataAccessObject = new DataAccessObject(this);
//        ArrayList<CourseModel> courseModelList = new ArrayList<CourseModel>(){{
//            add(emergingDiseases);
//            add(entrepreneurshipInCS);
//            add(dataMining);
//            add(operatingSystems);
//            add(computerAndNetworkSecurity);
//        }};
//
//        for(int i = 0; i < 5; i++){
//            boolean success = dataAccessObject.addOne(courseModelList.get(i));
//            Toast.makeText(MainActivity.this, "Inserting " +
//                    courseModelList.get(i).getCourseTitle() + " Success = " + success,
//                    Toast.LENGTH_SHORT).show();
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }

//        showAllCoursesButton.setOnClickListener((view) -> {
//            DataAccessObject dataAccessObject = new DataAccessObject(MainActivity.this);
//            List<CourseModel> allCourses = dataAccessObject.getAllCourses();
//
//            ArrayAdapter courseArrayAdapter = new ArrayAdapter<CourseModel>(
//                    MainActivity.this, android.R.layout.simple_list_item_1, allCourses);
//            coursesListView.setAdapter(courseArrayAdapter);
//
////            Toast.makeText(MainActivity.this, allCourses.toString(), Toast.LENGTH_SHORT).show();
//        });

        searchButton.setOnClickListener((view) -> {
            DataAccessObject dataAccessObject = new DataAccessObject(MainActivity.this);
            List<CourseModel> searchResults = dataAccessObject.getMatchingCourses(filterSpinner.getSelectedItem().toString(), querySearchView.getQuery().toString());

            ArrayAdapter courseArrayAdapter = new ArrayAdapter<CourseModel>(
                    MainActivity.this, android.R.layout.simple_list_item_1, searchResults);
            coursesListView.setAdapter(courseArrayAdapter);
        });
    }
}