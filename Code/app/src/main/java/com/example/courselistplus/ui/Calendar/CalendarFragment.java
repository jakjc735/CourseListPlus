package com.example.courselistplus.ui.Calendar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courselistplus.R;
import com.example.courselistplus.WeekActivity;
import com.example.courselistplus.databinding.FragmentCalendarBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.example.courselistplus.ui.Calendar.CalendarUtils.daysInMonthArray;
import static com.example.courselistplus.ui.Calendar.CalendarUtils.monthYearFromDate;

/**
 * Class that constructs the monthly calendar view
 *
 * @author JC Alvarez
 */
public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener{

    //constants that shows the calender numbers and calender layout itself
    //allows the layout to change the days to match months correctly
    private FragmentCalendarBinding binding;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    Button backwardsButton;
    Button forwardsButton;
    Button weeklyButton;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarViewModel calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calendarRecyclerView = root.findViewById(R.id.calendarRecyclerView);
        monthYearText = root.findViewById(R.id.monthYearDisplay);

        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

        backwardsButton = root.findViewById(R.id.backwardsButton);
        forwardsButton = root.findViewById(R.id.forwardsButton);
        weeklyButton = root.findViewById(R.id.weeklyButton);

        backwardsButton.setOnClickListener((view) -> {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
            setMonthView();
        });

        forwardsButton.setOnClickListener((view) ->  {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
            setMonthView();
        });

        weeklyButton.setOnClickListener((view) -> {
            Intent myIntent = new Intent(getActivity(), WeekActivity.class);
            startActivity(myIntent);

        });


        return root;
    }

    //this is what sets the month view up
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
       monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    //if you click on a day, the background changes to light gray
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}