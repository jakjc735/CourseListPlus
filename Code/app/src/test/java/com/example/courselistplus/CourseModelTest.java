package com.example.courselistplus;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This test class tests the functionality of the CourseModel class
 * @author amirshariatmadari
 */
public class CourseModelTest {

    /**
     * This tests the CourseModel's constructor and it's getter and setter methods to make sure that all added values are
     * stored and received properly.
     * @author amirshariatmadari
     */
    @Test
    public void testCourseModelConstructorAndGetters() {
        // Create a new CourseModel object
        CourseModel course = new CourseModel(1, 12345, "CS 101", "A1", "Intro to Computer Science",
                "John Smith", "3", "MW", "10:00 AM - 11:15 AM", 50, 25,
                "Open", 20, 5, "Sample course description");

        // Test the getters for all the attributes
        assertEquals(1, course.getId());
        assertEquals(12345, course.getCRN());
        assertEquals("CS 101", course.getCourseID());
        assertEquals("A1", course.getCourseAttribute());
        assertEquals("Intro to Computer Science", course.getCourseTitle());
        assertEquals("John Smith", course.getCourseInstructor());
        assertEquals("3", course.getCreditHours());
        assertEquals("MW", course.getMeetDays());
        assertEquals("10:00 AM - 11:15 AM", course.getMeetTime());
        assertEquals(50, course.getProjectedEnrollment());
        assertEquals(25, course.getCurrentEnrollment());
        assertEquals("Open", course.getStatus());
    }
}
