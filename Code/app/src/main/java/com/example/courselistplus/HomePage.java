package com.example.courselistplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.courselistplus.ui.CourseList.CourseAdapter;

import java.util.List;

public class HomePage extends AppCompatActivity {

    ListView popularCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        popularCourses = findViewById(R.id.popularCourseListView);

        DataAccessObject dataAccessObject = new DataAccessObject(this);

        CourseAdapter courseAdapter = new CourseAdapter(this, R.layout.list_item, (List<CourseModel>) dataAccessObject);
        popularCourses.setAdapter(courseAdapter);
    }


}