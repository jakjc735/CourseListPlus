package com.example.courselistplus;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * The class definition for the course objects
 *
 * @author abdih
 */
public class CourseModel implements Parcelable {
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
    // Use the following two attributes to calculate overall/average rating
    private int totalRating;
    private int numRatings;
    private String courseDescription;

    /**
     * Course object constructor
     *
     * @param id                  the id (primary key) of the course in the database
     * @param CRN                 the course pre-fix number. Should be unique to each course each semester
     * @param courseID            The subject and 3 digit course number e.g. ANTH 150
     * @param courseAttribute     course attributes for degreeworks e.g. C150 for Coll 150
     * @param courseTitle         Name of the Course
     * @param courseInstructor    Instructor teaching the course
     * @param creditHours         The number of credits hours assigned to the course
     * @param meetDays            Days of the week course meets. First letter of day of week used with R for thursday
     * @param meetTime            Meeting time of the course in military time and HHMM-HHMM format
     * @param projectedEnrollment Number of available seats in the course
     * @param currentEnrollment   Number of students registered for the course
     * @param status              If the course is open for enrollment or not e.g. Open, Closed
     * @param totalRating         Sum total of all stars received across all ratings
     * @param numRatings          Total number of students who have left a rating
     * @param courseDescription   Course description from open course list website
     */
    public CourseModel(int id, int CRN, String courseID, String courseAttribute, String courseTitle,
                       String courseInstructor, String creditHours, String meetDays, String meetTime,
                       int projectedEnrollment, int currentEnrollment, String status, int totalRating,
                       int numRatings, String courseDescription) {
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
        this.totalRating = totalRating;
        this.numRatings = numRatings;
        this.courseDescription = courseDescription;
    }

    protected CourseModel(Parcel in) {
        this.id = in.readInt();
        this.CRN = in.readInt();
        this.courseID = in.readString();
        this.courseAttribute = in.readString();
        this.courseTitle = in.readString();
        this.courseInstructor = in.readString();
        this.creditHours = in.readString();
        this.meetDays = in.readString();
        this.meetTime = in.readString();
        this.projectedEnrollment = in.readInt();
        this.currentEnrollment = in.readInt();
        this.status = in.readString();
        this.totalRating = in.readInt();
        this.numRatings = in.readInt();
        this.courseDescription = in.readString();
    }

    public static final Creator<CourseModel> CREATOR = new Creator<CourseModel>() {
        @Override
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        @Override
        public CourseModel[] newArray(int size) {
            return new CourseModel[size];
        }
    };

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

    public int getTotalRating() {
        return totalRating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getOverallRating() {
        // This performs int division and truncates
        //TODO @abdih Make the rating more precise
        return totalRating / numRatings;
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
                ", overallRating=" + this.getOverallRating() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeInt(this.id);
        parcel.writeInt(this.CRN);
        parcel.writeString(this.courseID);
        parcel.writeString(this.courseAttribute);
        parcel.writeString(this.courseTitle);
        parcel.writeString(this.courseInstructor);
        parcel.writeString(this.creditHours);
        parcel.writeString(this.meetDays);
        parcel.writeString(this.meetTime);
        parcel.writeInt(this.projectedEnrollment);
        parcel.writeInt(this.currentEnrollment);
        parcel.writeString(this.status);
        parcel.writeInt(this.totalRating);
        parcel.writeInt(this.numRatings);
        parcel.writeString(this.courseDescription);
    }
}