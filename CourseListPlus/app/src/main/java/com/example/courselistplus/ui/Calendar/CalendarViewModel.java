package com.example.courselistplus.ui.Calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalendarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CalendarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("JC implement a calendar view here, it should preferably be a grid like on courscicle" +
                "that places it in the correct date and time, but if we can not do that on this sprint that is fine" +
                "Also make sure there is a way to remove courses from the calendar");
    }

    public LiveData<String> getText() {
        return mText;
    }
}