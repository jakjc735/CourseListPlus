package com.example.courselistplus.ui.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // Get views
        ImageView courseImage = convertView.findViewById(R.id.courseImageView);
        ImageView ratingImage = convertView.findViewById(R.id.courseRatingImageView);
        TextView courseTitleText = convertView.findViewById(R.id.courseTitleTextView);
        TextView courseMeetDaysAndTimesText = convertView.findViewById(R.id.courseMeetDaysAndTimesTextView);
        TextView courseInstructorText = convertView.findViewById(R.id.courseInstructorTextView);

        // Set list item views from teh course
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String eventTitle = course.getCourseTitle() + " " + course.getMeetTime();
            courseImage.setImageResource(R.drawable.wm_logo);
            // Instantiate views with proper information (rating, course meet day/time, etc.)
            courseImage.setImageResource(R.drawable.wm_logo);
            switch (course.getOverallRating()) {
                case 1:
                    ratingImage.setImageResource(R.drawable.one_star);
                    break;
                case 2:
                    ratingImage.setImageResource(R.drawable.two_star);
                    break;
                case 3:
                    ratingImage.setImageResource(R.drawable.three_star);
                    break;
                case 4:
                    ratingImage.setImageResource(R.drawable.four_star);
                    break;
                case 5:
                    ratingImage.setImageResource(R.drawable.five_star);
                    break;
            }
            courseTitleText.setText(course.getCourseTitle());
            if (course.getMeetDays().length() > 0) {
                courseMeetDaysAndTimesText.setText(
                        course.getMeetDays() + ":"
                                + course.getMeetTime());
            } else {
                courseMeetDaysAndTimesText.setText("Determined by Instructor");
            }
            courseInstructorText.setText(course.getCourseInstructor());

        }
        return convertView;
    }
}

