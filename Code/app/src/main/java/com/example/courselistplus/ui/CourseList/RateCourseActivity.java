package com.example.courselistplus.ui.CourseList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.courselistplus.R;

public class RateCourseActivity extends AppCompatActivity {

    TextView currentCourseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_course);

        Intent intent = getIntent();
        String courseCRNIntent = intent.getStringExtra("courseCRNIntent");
        currentCourseTextView = findViewById(R.id.textView2);
        currentCourseTextView.setText(courseCRNIntent);
    }
}