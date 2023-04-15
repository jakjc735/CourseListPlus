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
                "John Smith", "3", "MW", "10:00 AM - 11:15 AM", 50, 25, "Open");

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

    /**
     * This tests if the CourseModel class can successfully update its attributes with the setter method.
     * @author amirshariatmadari
     */
    @Test
    public void testCourseModelSetters() {
        // Create a new CourseModel object
        CourseModel course = new CourseModel(1, 12345, "CS 101", "A1", "Intro to Computer Science",
                "John Smith", "3", "MW", "10:00 AM - 11:15 AM", 50, 25, "Open");

        // Test the setters for all the attributes
        course.setId(2);
        assertEquals(2, course.getId());

        course.setCRN(67890);
        assertEquals(67890, course.getCRN());

        course.setCourseID("MATH 101");
        assertEquals("MATH 101", course.getCourseID());

        course.setCourseAttribute("B1");
        assertEquals("B1", course.getCourseAttribute());

        course.setCourseTitle("Intro to Calculus");
        assertEquals("Intro to Calculus", course.getCourseTitle());

        course.setCourseInstructor("Jane Doe");
        assertEquals("Jane Doe", course.getCourseInstructor());

        course.setCreditHours("4");
        assertEquals("4", course.getCreditHours());

        course.setMeetDays("TR");
        assertEquals("TR", course.getMeetDays());

        course.setMeetTime("1:00 PM - 2:15 PM");
        assertEquals("1:00 PM - 2:15 PM", course.getMeetTime());

        course.setProjectedEnrollment(60);
        assertEquals(60, course.getProjectedEnrollment());

        course.setCurrentEnrollment(30);
        assertEquals(30, course.getCurrentEnrollment());

        course.setStatus("Closed");
        assertEquals("Closed", course.getStatus());
    }
}
