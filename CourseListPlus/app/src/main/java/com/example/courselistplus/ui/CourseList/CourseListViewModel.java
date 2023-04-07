package com.example.courselistplus.ui.CourseList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CourseListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CourseListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Brandon implement a view here for the course list. It should give the course name, id(CSCI 301) proffessor, time," +
                "and student score(arbitrary value for now), and a button to add to schedule. you should be able to scroll through these(backend people will do the rest)" +
                "Also put a search bar at the top");
    }

    public LiveData<String> getText() {
        return mText;
    }
}