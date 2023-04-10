package com.example.sqldemo3;

public class CourseModel {
    private int id;
    private int CRN;
    // Example: CHEM 150
    private String courseID;
    // Example: C150
    private String courseAttribute;
    // Example: Emerging Diseases
    private String courseTitle;
    private String courseInstructor;
    private int creditHours;
    private String meetDays;
    private String meetTime;
    // Use the following two attributes to calculate seats available
    private int projectedEnrollment;
    private int currentEnrollment;
    // Example: Open
    private String status;

    // Constructor
    public CourseModel(int id, int CRN, String courseID, String courseAttribute, String courseTitle,
                       String courseInstructor, int creditHours, String meetDays, String meetTime,
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

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
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

    // toString
    @Override
    public String toString() {
        return "CourseModel{" +
                "id=" + id +
                ", CRN=" + CRN +
                ", courseID='" + courseID + '\'' +
                ", courseAttribute='" + courseAttribute + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseInstructor='" + courseInstructor + '\'' +
                ", creditHours=" + creditHours +
                ", meetDays='" + meetDays + '\'' +
                ", meetTime='" + meetTime + '\'' +
                ", projectedEnrollment=" + projectedEnrollment +
                ", currentEnrollment=" + currentEnrollment +
                ", status='" + status + '\'' +
                '}';
    }
}
