package com.example.courselistplus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courselistplus.ui.Calendar.CalendarAdapter;
import com.example.courselistplus.ui.Calendar.CalendarUtils;
import com.example.courselistplus.ui.Calendar.Event;
import com.example.courselistplus.ui.Calendar.EventAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.courselistplus.ui.Calendar.CalendarUtils.daysInWeekArray;
import static com.example.courselistplus.ui.Calendar.CalendarUtils.monthYearFromDate;
import static com.example.courselistplus.ui.Calendar.CalendarUtils.selectedDate;

/**
 * Sets up the week view after you click on the weekly button
 * Also allows you to click on each day in the weekly view
 *
 * @author JC Alvarez
 */

public class WeekActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView courseListView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        initWidgets();
        setWeekView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearDisplay);
        courseListView = findViewById(R.id.courseListView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setCourseAdapter();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        setCourseAdapter();
    }


    /**
     * Sets up event adapter for the list view that displays events for a given day
     * @author amirshariatmadari
     */
    private void setCourseAdapter() {
        StudentDataAccessObject studentDb = new StudentDataAccessObject(WeekActivity.this);

        // Get day of the week
        String dayOfWeek = CalendarUtils.getDayOfWeek(selectedDate);
        List<CourseModel> searchResults = studentDb.getMatchingCourses("Meet Days", dayOfWeek);

        Log.d("myTag", "Begin logging");
        for(int j = 0; j < searchResults.size(); j++){
            Log.d("myTag", searchResults.get(j).getCourseTitle());
        }

        ArrayList<CourseModel> dailyEvents = new ArrayList<CourseModel>(searchResults);

        for(int i = 0; i < dailyEvents.size(); i++){
            Log.d("myTag", dailyEvents.get(i).getCourseTitle());
        }
        Log.d("myTag", "End logging");

        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        courseListView.setAdapter(eventAdapter);
    }


}