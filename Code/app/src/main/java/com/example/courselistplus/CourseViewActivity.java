package com.example.courselistplus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseViewActivity extends AppCompatActivity {

    Button addButton;
    Button rateButton;

    TextView courseName;
    TextView courseRating;
    TextView courseId;
    TextView instructor;
    TextView courseTime;
    TextView courseDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        //Initalize Buttons
        addButton = findViewById(R.id.AddButton);
        rateButton = findViewById(R.id.RateButton);

        //Initalize Textviews
        courseName = findViewById(R.id.CourseName);
        courseRating = findViewById(R.id.CourseRating);
        courseId = findViewById(R.id.CourseId);
        instructor = findViewById(R.id.Instructor);
        courseTime = findViewById(R.id.CourseTime);
        courseDescription = findViewById(R.id.CourseDescription);

        Intent intent = getIntent();
        String courseTitleIntent = intent.getStringExtra("courseTitleIntent");
        String courseIDIntent = intent.getStringExtra("courseIDIntent");
        String courseInstructorIntent = intent.getStringExtra("courseInstructorIntent");
        String courseMeetTimeIntent = intent.getStringExtra("courseMeetTimeIntent");
        String courseDescriptionIntent = intent.getStringExtra("courseDescriptionIntent");

        courseName.setText(courseTitleIntent);
        courseId.setText(courseIDIntent);
        instructor.setText(courseInstructorIntent);
        courseTime.setText(courseMeetTimeIntent);
        courseDescription.setText(courseDescriptionIntent);
    }
}