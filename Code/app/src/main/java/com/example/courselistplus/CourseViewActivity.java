package com.example.courselistplus;

import android.os.Bundle;
import android.view.View;
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

        Bundle data = getIntent().getExtras();
        CourseModel selectedCourse = data.getParcelable("Course");

        courseName.setText(selectedCourse.getCourseTitle());
        courseId.setText(selectedCourse.getCourseID());
        instructor.setText(selectedCourse.getCourseInstructor());
        courseTime.setText(selectedCourse.getMeetTime());
        courseDescription.setText(selectedCourse.getCourseDescription());
        courseRating.setText("Course Overall Rating: " + selectedCourse.getOverallRating() +
                " (as rated by " + selectedCourse.getNumRatings() + " students!)");

        // Add course clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add course to student database

                // Create course model object
                //TODO: @amir Fix the example fields
                CourseModel studentCourse = new CourseModel(selectedCourse.getId(), selectedCourse.getCRN(),
                        selectedCourse.getCourseID(), selectedCourse.getCourseAttribute(),
                        selectedCourse.getCourseTitle(), selectedCourse.getCourseInstructor(),
                        selectedCourse.getCreditHours(), selectedCourse.getMeetDays(),
                        selectedCourse.getMeetTime(), selectedCourse.getProjectedEnrollment(),
                        selectedCourse.getCurrentEnrollment(), selectedCourse.getStatus(),
                        selectedCourse.getTotalRating(), selectedCourse.getNumRatings(),
                        selectedCourse.getCourseDescription());

                // Add course model to student database
                studentDB.addOne(studentCourse);
            }
        });
    }
}