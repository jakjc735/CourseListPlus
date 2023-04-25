package com.example.courselistplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CourseViewActivity extends AppCompatActivity {
    Button addButton;
    Button rateButton;
    TextView courseName;
    TextView courseRating;
    TextView courseId;
    TextView instructor;
    TextView courseTime;
    TextView courseDescription;

    private StudentDataAccessObject studentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        // Init the student database
        studentDB = new StudentDataAccessObject(CourseViewActivity.this);

        //Initalize Buttons
        addButton = (Button) findViewById(R.id.AddButton);
        rateButton = (Button) findViewById(R.id.RateButton);

        //Initalize Textviews
        courseName = findViewById(R.id.CourseName);
        courseRating = findViewById(R.id.CourseRating);
        courseId = findViewById(R.id.CourseId);
        instructor = findViewById(R.id.Instructor);
        courseTime = findViewById(R.id.CourseTime);
        courseDescription = findViewById(R.id.CourseDescription);

        // TODO: Get all course attrs from intent
        Intent intent = getIntent();
        String courseTitleIntent = intent.getStringExtra("courseTitleIntent");
        String courseIDIntent = intent.getStringExtra("courseIDIntent");
        String courseInstructorIntent = intent.getStringExtra("courseInstructorIntent");
        String courseMeetTimeIntent = intent.getStringExtra("courseMeetTimeIntent");
        String courseDescriptionIntent = intent.getStringExtra("courseDescriptionIntent");
        String courseMeetDaysIntent = intent.getStringExtra("courseMeetDaysIntent");
        String courseRatingIntent = intent.getStringExtra("courseRatingIntent");

        courseName.setText(courseTitleIntent);
        courseId.setText(courseIDIntent);
        instructor.setText(courseInstructorIntent);
        courseTime.setText(courseMeetTimeIntent);
        courseDescription.setText(courseDescriptionIntent);
        courseRating.setText(courseRatingIntent);

        // Add course clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add course to student database

                // add course attr to course model object
                Random random = new Random();
                int rand = random.nextInt(1000);

                // Create course model object
                //TODO: @amir Fix the example fields
                CourseModel studentCourse = new CourseModel(-1, rand, courseIDIntent, "placeholder",
                        courseTitleIntent, courseInstructorIntent, "placeholder", courseMeetDaysIntent,
                        courseMeetTimeIntent, 10, 9, "placeholder",
                        9, 3, "placeholder");

                // Add course model to student database
                studentDB.addOne(studentCourse);
            }
        });
    }
}