package com.example.courselistplus;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.courselistplus.databinding.ActivityNavigationBinding;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigation.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Hard-coded courses to prepopulate the database on every run
        CourseModel emergingDiseases = new CourseModel(-1, 21621,
                "CHEM 150 01", "C150", "Emerging Diseases",
                "Sher, Beverly", 4, "MWF", "1200-1250",
                17, 16, "Open");
        CourseModel entrepreneurshipInCS = new CourseModel(-1, 24354,
                "CSCI 425 01", "C400, IN", "Entrepreneurship in CS",
                "Kemper, Peter", 3, "TR", "1100-1220",
                45, 44, "Open");
        CourseModel dataMining = new CourseModel(-1, 27294,
                "CSCI 436 01", "None", "Data Mining",
                "Shao, Huajie", 3, "MW", "1400-1520",
                40, 27, "Open");
        CourseModel operatingSystems = new CourseModel(-1, 25722,
                "CSCI 444 01", "None", "Operating Systems",
                "Kumar, Pradeep", 3, "TR", "1230-1350",
                40, 35, "Open");
        CourseModel computerAndNetworkSecurity = new CourseModel(-1, 24957,
                "CSCI 454 01", "None", "Computer and Network Security",
                "Evtyushkin, Dmitry", 3, "TR", "1400-1520",
                40, 34, "Open");

        DataAccessObject dataAccessObject = new DataAccessObject(this);
        ArrayList<CourseModel> courseModelList = new ArrayList<CourseModel>(){{
            add(emergingDiseases);
            add(entrepreneurshipInCS);
            add(dataMining);
            add(operatingSystems);
            add(computerAndNetworkSecurity);
        }};

        for(int i = 0; i < 5; i++){
            dataAccessObject.addOne(courseModelList.get(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}