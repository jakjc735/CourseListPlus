package com.example.courselistplus.ui.Calendar;

import static com.example.courselistplus.ui.Calendar.CalendarUtils.daysInWeekArray;
import static com.example.courselistplus.ui.Calendar.CalendarUtils.monthYearFromDate;
import static com.example.courselistplus.ui.Calendar.CalendarUtils.selectedDate;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courselistplus.CourseModel;
import com.example.courselistplus.R;
import com.example.courselistplus.StudentDataAccessObject;
import com.example.courselistplus.databinding.FragmentCalendarBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that constructs the weekly calendar view
 *
 * @author JC Alvarez
 */
public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {
    //constants that shows the calender numbers and calender layout itself
    //allows the layout to change the days to match months correctly
    private FragmentCalendarBinding binding;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    private ListView courseListView;

    Button backwardsWeekButton;
    Button forwardsWeekButton;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calendarRecyclerView = root.findViewById(R.id.calendarRecyclerView);
        monthYearText = root.findViewById(R.id.monthYearDisplay);
        courseListView = root.findViewById(R.id.courseListView);

        CalendarUtils.selectedDate = LocalDate.now();
        setWeekView();

        backwardsWeekButton = root.findViewById(R.id.backwardsWeekButton);
        forwardsWeekButton = root.findViewById(R.id.forwardsWeekButton);


        backwardsWeekButton.setOnClickListener((view) -> {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
            setWeekView();
        });

        forwardsWeekButton.setOnClickListener((view) -> {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
            setWeekView();
        });

        return root;
    }

    //this is what sets the month view up
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setCourseAdapter();
    }

    //if you click on a day, the background changes to light gray
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setWeekView();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();
        setCourseAdapter();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setCourseAdapter() {
        StudentDataAccessObject studentDb = new StudentDataAccessObject(getActivity());

        // Get day of the week
        String dayOfWeek = CalendarUtils.getDayOfWeek(selectedDate);
        List<CourseModel> searchResults = studentDb.getMatchingCourses("Meet Days", dayOfWeek);

        ArrayList<CourseModel> dailyEvents = new ArrayList<CourseModel>(searchResults);

        EventAdapter eventAdapter = new EventAdapter(getActivity().getApplicationContext(), dailyEvents);
        courseListView.setAdapter(eventAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}