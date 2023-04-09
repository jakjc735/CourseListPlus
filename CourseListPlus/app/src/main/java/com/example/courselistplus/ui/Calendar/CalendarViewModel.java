package com.example.courselistplus.ui.Calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalendarViewModel extends ViewModel {
    String coursename;
    String time;
    String courseId;

    private final MutableLiveData<String> mText;
    public CalendarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("CHECKING TO SEE");

    }

    public LiveData<String> getText() {
        return mText;
    }
}