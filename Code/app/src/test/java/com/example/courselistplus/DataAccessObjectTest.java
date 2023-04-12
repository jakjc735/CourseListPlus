package com.example.courselistplus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;

import com.example.courselistplus.CourseModel;
import com.example.courselistplus.DataAccessObject;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DataAccessObjectTest {

    private DataAccessObject dataAccessObject;
    private Context mockContext;
    @Before
    public void setUp() {

//        mockContext = Mockito.mock
        // create a new instance of the class to be tested
        dataAccessObject = new DataAccessObject(RuntimeEnvironment.application.getApplicationContext());
    }
//
    @After
    public void tearDown() {
        // close the database connection when done
        dataAccessObject.close();
    }


    @Test
    public void testGetAllCourses() {

        // create a new course object to insert into the database
        CourseModel courseModel = new CourseModel(0, 12345, "CS101", "NQR" ,"Intro to CS", "John Smith",
                3, "MWF", "10:00-11:00", 50, 25, "Open");

        // insert the course object into the database
        dataAccessObject.addOne(courseModel);

        // get all the courses from the database
        List<CourseModel> courseList = dataAccessObject.getAllCourses();

        // check that the list contains the course that was inserted
        assertTrue(courseList.contains(courseModel));
    }

    @Test
    public void testGetMatchingCourses() {
        // create a new course object to insert into the database
        CourseModel courseModel = new CourseModel(0, 12345, "CS101", "NQR" ,"Intro to CS", "John Smith",
                3, "MWF", "10:00-11:00", 50, 25, "Open");

        // insert the course object into the database
        dataAccessObject.addOne(courseModel);

        // get all the courses that match the CRN filter
        List<CourseModel> courseList = dataAccessObject.getMatchingCourses("CRN", "12345");

        // check that the list contains the course that was inserted
        assertTrue(courseList.contains(courseModel));
    }
}
