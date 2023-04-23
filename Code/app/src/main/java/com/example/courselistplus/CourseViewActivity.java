package com.example.courselistplus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courselistplus.ui.Calendar.CalendarAdapter;
import com.example.courselistplus.ui.Calendar.CalendarUtils;
import com.example.courselistplus.ui.Calendar.Event;
import com.example.courselistplus.ui.Calendar.EventAdapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class CourseViewActivity extends AppCompatActivity{

    Button addButton;
    Button rateButton;

    TextView courseName;
    TextView courseRating;
    TextView courseId;
    TextView instructor;
    TextView courseTime;
    TextView courseDescription;
    LocalTime time;

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
        String courseMeetDaysIntent = intent.getStringExtra("courseMeetDaysIntent");

        courseName.setText(courseTitleIntent);
        courseId.setText(courseIDIntent);
        instructor.setText(courseInstructorIntent);
        courseTime.setText(courseMeetTimeIntent);


        // Add course clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add course to student database

                // Create course model object
                CourseModel studentCourse = new CourseModel(-1);

                // add course attr to course model object
                Random random = new Random();
                int rand = random.nextInt(1000);
                studentCourse.setCRN(rand); //TODO: Fix the example fields
                studentCourse.setCourseTitle(courseTitleIntent);
                studentCourse.setCourseID(courseIDIntent);
                studentCourse.setCourseInstructor(courseInstructorIntent);
                studentCourse.setMeetTime(courseMeetTimeIntent);
                studentCourse.setMeetDays(courseMeetDaysIntent);
                studentCourse.setCourseAttribute("NULL");


                // Add course model to student database
                studentDB.addOne(studentCourse);
            }
        });


    }









}