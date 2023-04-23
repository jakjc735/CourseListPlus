package com.example.courselistplus;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


/**
 * This class tests the functionality of the DataAccessObject class
 * @author amirshariatmadari 
 */
@RunWith(MockitoJUnitRunner.class)
public class DataAccessObjectTest {

    private DataAccessObject dataAccessObject;
    private Context mockContext;

    /**
     * This method sets up the DataAccessObject by passing in a mock context to the object
     * on construction.
     * @author amirshariatmadari
     */
    @Before
    public void setUp() {
//        mockContext = Mockito.mock
        // create a new instance of the class to be tested
        dataAccessObject = new DataAccessObject(RuntimeEnvironment.application.getApplicationContext());
    }

    /**
     * This method closes the object connection to the database upon test completion
     * @author amirhassanshariatmadari
     */
    @After
    public void tearDown() {
        // close the database connection when done
        dataAccessObject.close();
    }


    /**
     * This method will create a CourseModel and populate it with class information. The CourseModel
     * object is added to the database via the dataAccessObject. The method tests if the course is
     * successfully added to the database and that the dataAccessObject can successfully list it.
     * @author amirshariatmadari
     */
    @Test
    public void testGetAllCourses() {

        // create a new course object to insert into the database
        CourseModel courseModel = new CourseModel(0, 12345, "CS101",
                "NQR" ,"Intro to CS", "John Smith",
                "3", "MWF", "10:00-11:00", 50,
                25, "Open",20, 5, "Sample course description");

        // insert the course object into the database
        dataAccessObject.addOne(courseModel);

        // get all the courses from the database
        List<CourseModel> courseList = dataAccessObject.getAllCourses();

        // check that the list contains the course that was inserted
        assertTrue(courseList.contains(courseModel));
    }

    /**
     * This method tests the dataAccessObject.getMatchingCourses() method after adding a course the database.
     * @author amirshariatmadari
     */
    @Test
    public void testGetMatchingCourses() {
        // create a new course object to insert into the database
        CourseModel courseModel = new CourseModel(0, 12345, "CS101",
                "NQR" ,"Intro to CS", "John Smith",
                "3", "MWF", "10:00-11:00", 50, 25,
                "Open",20, 5, "Sample course description");

        // insert the course object into the database
        dataAccessObject.addOne(courseModel);

        // get all the courses that match the CRN filter
        List<CourseModel> courseList = dataAccessObject.getMatchingCourses("CRN", "12345");

        // check that the list contains the course that was inserted
        assertTrue(courseList.contains(courseModel));
    }
}
