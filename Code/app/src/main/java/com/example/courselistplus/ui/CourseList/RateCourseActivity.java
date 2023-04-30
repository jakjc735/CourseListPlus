package com.example.courselistplus.ui.CourseList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.courselistplus.CourseModel;
import com.example.courselistplus.R;

public class RateCourseActivity extends AppCompatActivity {

    TextView currentCourseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_course);

        currentCourseTextView = findViewById(R.id.textView2);
        Bundle data = getIntent().getExtras();

        CourseModel selectedCourse = data.getParcelable("Course");
        currentCourseTextView.setText(String.valueOf(selectedCourse.getCRN()));
    }
}