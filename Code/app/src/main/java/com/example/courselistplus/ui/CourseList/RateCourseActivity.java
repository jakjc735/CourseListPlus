package com.example.courselistplus.ui.CourseList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.MotionEvent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courselistplus.R;

import java.util.ArrayList;

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

        //initialize button
        Button rateButton = findViewById(R.id.rateButton);

        //initialize spinner
        Spinner spinner = findViewById(R.id.rateSpinner);

        //create Array of values for spinner
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            //what to do when an item is selected
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                String rateValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + rateValue,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        //TODO set on click listener for rate button that passes the spinner value and returns to the CourseViewActivity
    }
}