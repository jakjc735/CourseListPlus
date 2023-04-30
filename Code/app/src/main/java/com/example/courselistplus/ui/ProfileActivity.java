package com.example.courselistplus.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.courselistplus.R;

/**
 * Class that constructs the profile view
 *
 * @author Brandon Nguyen
 */

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
    }
}
