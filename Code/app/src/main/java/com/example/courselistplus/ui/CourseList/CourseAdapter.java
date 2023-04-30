package com.example.courselistplus.ui.CourseList;

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

public class CourseAdapter extends ArrayAdapter<CourseModel> {
    private List<CourseModel> courses;

    public CourseAdapter(@NonNull Context context, int resource, List<CourseModel> courses) {
        super(context, resource, courses);
        this.courses = courses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int phraseIndex = position;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Declare views
        ImageView courseImage = convertView.findViewById(R.id.courseImageView);
        ImageView ratingImage = convertView.findViewById(R.id.courseRatingImageView);
        TextView courseTitleText = convertView.findViewById(R.id.courseTitleTextView);
        TextView courseMeetDaysAndTimesText = convertView.
                findViewById(R.id.courseMeetDaysAndTimesTextView);
        TextView courseInstructorText = convertView.findViewById(R.id.courseInstructorTextView);

        // Instantiate views with proper information (rating, course meet day/time, etc.)
        courseImage.setImageResource(R.drawable.wm_logo);
        switch (courses.get(position).getOverallRating()) {
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
        courseTitleText.setText(courses.get(position).getCourseTitle());
        if (courses.get(position).getMeetDays().length() > 0) {
            courseMeetDaysAndTimesText.setText(
                    courses.get(position).getMeetDays() + ":"
                            + courses.get(position).getMeetTime());
        } else {
            courseMeetDaysAndTimesText.setText("Determined by Instructor");
        }
        courseInstructorText.setText(courses.get(position).getCourseInstructor());

        return convertView;
    }
}