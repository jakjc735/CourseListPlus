package com.example.courselistplus.ui.CourseList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.courselistplus.CourseModel;
import com.example.courselistplus.CourseViewActivity;
import com.example.courselistplus.DataAccessObject;
import com.example.courselistplus.R;

import java.util.ArrayList;

public class RateCourseActivity extends AppCompatActivity {
    TextView currentCourseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_course);

        Bundle data = getIntent().getExtras();
        CourseModel selectedCourse = data.getParcelable("Course");

        // Initialize button and ratingSpinner
        Button rateButton = findViewById(R.id.rateButton);
        Spinner ratingSpinner = findViewById(R.id.rateSpinner);

        // Create Array of values for ratingSpinner
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(arrayAdapter);

        ratingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //what to do when an item is selected
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                int rateValue = (Integer) parent.getItemAtPosition(position);
                switch (rateValue) {
                    case 1:
                        Toast.makeText(parent.getContext(), "Aw man, better luck next semester!",
                                Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Not great... but it could have been worse!",
                                Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(parent.getContext(), "A pretty average course, no shame there!",
                                Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(parent.getContext(), "Looks like you enjoyed this course!",
                                Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(parent.getContext(), "WOW... 5 stars?? Peter Kemper must have taught this course!",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        rateButton.setOnClickListener(v -> {
            Intent ratingIntent = new Intent(RateCourseActivity.this, CourseViewActivity.class);
            DataAccessObject dataAccessObject = new DataAccessObject(getApplicationContext());

            selectedCourse.addRating((Integer) ratingSpinner.getSelectedItem());
            dataAccessObject.update(selectedCourse, (Integer)ratingSpinner.getSelectedItem());
            ratingIntent.putExtra("Course", selectedCourse);


            startActivity(ratingIntent);
        });
    }
}