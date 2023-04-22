package com.example.courselistplus;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The class definition for the course objects
 *
 * @author abdih
 */
public class CourseModel {
    // Columns displayed for a course on W&M's open course list
    private int id;
    private int CRN;
    // Example: CHEM 150
    private String courseID;
    // Example: C150
    private String courseAttribute;
    // Example: Emerging Diseases
    private String courseTitle;
    private String courseInstructor;
    private String creditHours;
    private String meetDays;
    private String meetTime;
    // Use the following two attributes to calculate seats available
    private int projectedEnrollment;
    private int currentEnrollment;
    // Example: Open
    private String status;
    // Total number of stars received across all ratings from all students
    private int totalRating;
    // Total number of students who have left a rating. Helps to calculate average/overall rating
    private int numRatings;

    /**
     * Course object constructor
     *
     * @param id the id (primary key) of the course in the database
     * @param CRN the course pre-fix number. Should be unique to each course each semester
     * @param courseID The subject and 3 digit course number e.g. ANTH 150
     * @param courseAttribute course attributes for degreeworks e.g. C150 for Coll 150
     * @param courseTitle Name of the Course
     * @param courseInstructor Instructor teaching the course
     * @param creditHours The number of credits hours assigned to the course
     * @param meetDays Days of the week course meets. First letter of day of week used with R for thursday
     * @param meetTime Meeting time of the course in military time and HHMM-HHMM format
     * @param projectedEnrollment Number of available seats in the course
     * @param currentEnrollment Number of students registered for the course
     * @param status If the course is open for enrollment or not e.g. Open, Closed
     */
    public CourseModel(int id, int CRN, String courseID, String courseAttribute, String courseTitle,
                       String courseInstructor, String creditHours, String meetDays, String meetTime,
                       int projectedEnrollment, int currentEnrollment, String status) {
        this.id = id;
        this.CRN = CRN;
        this.courseID = courseID;
        this.courseAttribute = courseAttribute;
        this.courseTitle = courseTitle;
        this.courseInstructor = courseInstructor;
        this.creditHours = creditHours;
        this.meetDays = meetDays;
        this.meetTime = meetTime;
        this.projectedEnrollment = projectedEnrollment;
        this.currentEnrollment = currentEnrollment;
        this.status = status;

        // We are randomly generating ratings for each course since we do not have this data yet
        numRatings = ThreadLocalRandom.current().nextInt(1, 11);
        // Need to generate numRatings number of random course ratings, 1-5
        for(int i = 0; i < numRatings; i++){
            totalRating += ThreadLocalRandom.current().nextInt(1,6);
        }
    }

    /**
     * "Empty" constructor to declare but not fully instantiate a CourseModel object
     * Useful for when the object's fields are not ready to be populated all at once.
     *
     * @param id the id (primary key) of the course in the database
     */
    public CourseModel(int id){
        this.id = id;

        // We are randomly generating ratings for each course since we do not have this data yet
        numRatings = ThreadLocalRandom.current().nextInt(1, 11);
        // Need to generate numRatings number of random course ratings, 1-5
        for(int i = 0; i < numRatings; i++){
            totalRating += ThreadLocalRandom.current().nextInt(1,6);
        }
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseAttribute() {
        return courseAttribute;
    }

    public void setCourseAttribute(String courseAttribute) {
        this.courseAttribute = courseAttribute;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

    public String getMeetDays() {
        return meetDays;
    }

    public void setMeetDays(String meetDays) {
        this.meetDays = meetDays;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public int getProjectedEnrollment() {
        return projectedEnrollment;
    }

    public void setProjectedEnrollment(int projectedEnrollment) {
        this.projectedEnrollment = projectedEnrollment;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOverallRating(){
        // This performs int division and truncates
        //TODO @abdihassan Make the rating more precise
        return this.totalRating/this.numRatings;
    }

    public void addRating(int courseRating){
        if((1 <= courseRating) && (courseRating <= 5)){
            this.totalRating += courseRating;
            this.numRatings += 1;
        }
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "id=" + id +
                ", CRN=" + CRN +
                ", courseID='" + courseID + '\'' +
                ", courseAttribute='" + courseAttribute + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseInstructor='" + courseInstructor + '\'' +
                ", creditHours='" + creditHours + '\'' +
                ", meetDays='" + meetDays + '\'' +
                ", meetTime='" + meetTime + '\'' +
                ", projectedEnrollment=" + projectedEnrollment +
                ", currentEnrollment=" + currentEnrollment +
                ", status='" + status + '\'' +
                ", courseRating=" + this.getOverallRating() +
                '}';
    }


}