package com.example.courselistplus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.courselistplus.databinding.ActivityNavigationBinding;
import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NavigationActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private DataAccessObject dataAccessObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNavigationBinding binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigation.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_profile)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Instantiate the database
        dataAccessObject = new DataAccessObject(NavigationActivity.this);

        // Call asynchronous task to populate the database from open course list website
        Content content = new Content();
        content.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    private class Content extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // We know our starting point, the open course list webpage, and its URL,
                // however, we will generate the URLs for the subject pages programmatically
                String openCourseListHomePageURL = "https://courselist.wm.edu/" +
                        "courselist/courseinfo/search?";
                String currentSubjectPageURL;
                String currentCourseCourseDescriptionPageURL;
                //Connect to the website
                Document openCourseListHomePage = Jsoup.connect(openCourseListHomePageURL).get();

                for (int i = 2; i < 75; i++) {
                    // The url for a particular subject's open course list page is formulaic.
                    // Setting the term_subj= with the correct subject tag will yield the desired url.
                    currentSubjectPageURL = "https://courselist.wm.edu/courselist/courseinfo/searchresults?" +
                            "term_code=202320&term_subj=" + openCourseListHomePage.select(
                            "#term_subj > option:nth-child(" + i + ")").val() +
                            "&attr=0&attr2=0&levl=0&status=0&ptrm=0&search=Search";

                    Document currentSubjectPage = Jsoup.connect(currentSubjectPageURL).get();

                    // results is the table of courses associated with the current subject/department
                    List<Element> results = currentSubjectPage.select("table tr");

                    // Code to populate the database with courses from W&M's open course list
                    for (Element row : results.subList(1, results.size())) {
                        int courseCRN = Integer.parseInt(row.select("td:nth-of-type(1)").text());
                        currentCourseCourseDescriptionPageURL = "https://courselist.wm.edu/" +
                                "courselist/courseinfo/addInfo?fterm=202320&fcrn=" + courseCRN;
                        Document currentCourseCourseDescriptionPage = Jsoup.connect(
                                currentCourseCourseDescriptionPageURL).get();
                        String courseID = row.select("td:nth-of-type(2)").text();
                        String courseAttribute = row.select("td:nth-of-type(3)").text();
                        String courseTitle = row.select("td:nth-of-type(4)").text();
                        String courseInstructor = row.select("td:nth-of-type(5)").text();
                        String courseCreditHours = row.select("td:nth-of-type(6)").text();
                        String courseMeetDays = "";
                        String courseMeetTime = "";

                        // Meet days and meet times are given together on the Open Course List
                        // The format is DDD:HHMM-HHMM where D are the meet days and HHMM-HHMM is the meet time
                        // in military time. Thus splitting on the colon separates the two for use in the CourseModel
                        // NOTE: Some courses don't have meet days/times like "Directed Study" courses, use empty string
                        if (!row.select("td:nth-of-type(7)").text().isEmpty()) {
                            String[] meetDaysAndTimes = row.select("td:nth-of-type(7)").text().split(":");
                            courseMeetDays = meetDaysAndTimes[0];
                            courseMeetTime = meetDaysAndTimes[1];
                        }

                        int courseProjectedEnrollment = Integer.parseInt(row.select("td:nth-of-type(8)").text());
                        int courseCurrentEnrollment = Integer.parseInt(row.select("td:nth-of-type(9)").text());
                        String courseStatus = row.select("td:nth-of-type(11)").text();

                        // We are randomly generating ratings for each course since we do not have this data yet
                        int courseTotalRating = 0;
                        int courseNumRatings = ThreadLocalRandom.current().nextInt(1, 11);
                        // Need to generate numRatings number of random course ratings, 1-5
                        for (int j = 0; j < courseNumRatings; j++) {
                            courseTotalRating += ThreadLocalRandom.current().nextInt(1, 6);
                        }

                        String courseCourseDescription = currentCourseCourseDescriptionPage.select(
                                        "#addinfo > table:nth-child(1) > tbody:nth-child(1) > tr > td")
                                .text().split(" ", 3)[2];
                        ;

                        // Constructor CourseModel object from scraped data and insert into database
                        CourseModel currentCourse = new CourseModel(-1, courseCRN, courseID, courseAttribute,
                                courseTitle, courseInstructor, courseCreditHours, courseMeetDays, courseMeetTime,
                                courseProjectedEnrollment, courseCurrentEnrollment, courseStatus,
                                courseTotalRating, courseNumRatings, courseCourseDescription);
                        dataAccessObject.addOne(currentCourse);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}