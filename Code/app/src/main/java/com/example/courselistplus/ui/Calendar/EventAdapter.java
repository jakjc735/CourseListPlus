package com.example.courselistplus.ui.Calendar;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.courselistplus.CourseModel;
import com.example.courselistplus.R;

import java.util.List;

public class EventAdapter extends ArrayAdapter<CourseModel> {
    public EventAdapter(@NonNull Context context, List<CourseModel> courses) {
        super(context, 0, courses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CourseModel course = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String eventTitle = course.getCourseTitle() + " " + course.getMeetTime();
            eventCellTV.setText(eventTitle);
        }
        return convertView;
    }
}

