package com.example.courselistplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataAccessObject extends SQLiteOpenHelper {

    public static final String COURSES_TABLE = "COURSES_TABLE";
    public static final String COLUMN_CRN = "CRN";
    public static final String COLUMN_COURSE_ID = "COURSE_ID";
    public static final String COLUMN_COURSE_ATTRIBUTE = "COURSE_ATTRIBUTE";
    public static final String COLUMN_COURSE_TITLE = "COURSE_TITLE";
    public static final String COLUMN_COURSE_INSTRUCTOR = "COURSE_INSTRUCTOR";
    public static final String COLUMN_CREDIT_HOURS = "CREDIT_HOURS";
    public static final String COLUMN_MEET_DAYS = "MEET_DAYS";
    public static final String COLUMN_MEET_TIME = "MEET_TIME";
    public static final String COLUMN_PROJECTED_ENROLLMENT = "PROJECTED_ENROLLEMENT";
    public static final String COLUMN_CURRENT_ENROLLMENT = "CURRENT_ENROLLMENT";
    public static final String COLUMN_STATUS = "STATUS";

    public DataAccessObject(@Nullable Context context) {
        super(context, "courses.db", null, 1);
    }

    // This is called the first time a database is accessed.
    // There should be code in here to create a new database.
    // The tutorial I am using: https://www.youtube.com/watch?v=312RhjfetP8
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + COURSES_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CRN + " INT, " + COLUMN_COURSE_ID + " TEXT, " + COLUMN_COURSE_ATTRIBUTE + " TEXT, " +
                COLUMN_COURSE_TITLE + " TEXT, " + COLUMN_COURSE_INSTRUCTOR + " TEXT, " + COLUMN_CREDIT_HOURS +
                " INT, " + COLUMN_MEET_DAYS + " TEXT, " + COLUMN_MEET_TIME + " TEXT, " + COLUMN_PROJECTED_ENROLLMENT
                + " INT, " + COLUMN_CURRENT_ENROLLMENT + " INT, " + COLUMN_STATUS + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CourseModel courseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CRN, courseModel.getCRN());
        cv.put(COLUMN_COURSE_ID, courseModel.getCourseID());
        cv.put(COLUMN_COURSE_ATTRIBUTE, courseModel.getCourseAttribute());
        cv.put(COLUMN_COURSE_TITLE, courseModel.getCourseTitle());
        cv.put(COLUMN_COURSE_INSTRUCTOR, courseModel.getCourseInstructor());
        cv.put(COLUMN_CREDIT_HOURS, courseModel.getCreditHours());
        cv.put(COLUMN_MEET_DAYS, courseModel.getMeetDays());
        cv.put(COLUMN_MEET_TIME, courseModel.getMeetTime());
        cv.put(COLUMN_PROJECTED_ENROLLMENT, courseModel.getProjectedEnrollment());
        cv.put(COLUMN_CURRENT_ENROLLMENT, courseModel.getCurrentEnrollment());
        cv.put(COLUMN_STATUS, courseModel.getStatus());

        // insert method returns success or failure (-1), can use as our return
        long insert = db.insert(COURSES_TABLE, null, cv);

        return (insert == -1 ? false : true);
    }

    public List<CourseModel> getMatchingCourses(String filter, String query){
        List<CourseModel> returnList = new ArrayList<>();

        switch (filter) {
            case "CRN":
                returnList = getMatchingCRN(query);
                break;
            case "Credits":
                returnList = getMatchingCredits(query);
                break;
            case "Meet Days":
                returnList = getMatchingMeetDays(query);
                break;
            case "Meet Time":
                returnList = getMatchingMeetTime(query);
                break;
            case "Instructor":
                returnList = getMatchingInstructor(query);
                break;
        }

        return returnList;
    }

    public List<CourseModel> getAllCourses(){
        List<CourseModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COURSES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int coursePrimaryKey = cursor.getInt(0);
                int CRN = cursor.getInt(1);;
                String courseID = cursor.getString(2);
                String courseAttribute = cursor.getString(3);
                String courseTitle = cursor.getString(4);
                String courseInstructor = cursor.getString(5);
                int creditHours = cursor.getInt(6);
                String meetDays = cursor.getString(7);
                String meetTime = cursor.getString(8);
                int projectedEnrollment = cursor.getInt(9);;
                int currentEnrollment = cursor.getInt(10);;
                String status = cursor.getString(11);

                CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                        CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                        creditHours, meetDays, meetTime, projectedEnrollment,
                        currentEnrollment, status);

                returnList.add(newCourseModel);
            } while(cursor.moveToNext());
        } else {
            // failure, is the database empty?
        }

        // close both the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }

    // Method to process the query of a user searching for a course by CRN
    private List<CourseModel> getMatchingCRN(String query){
        List<CourseModel> returnList = new ArrayList<>();

        // TODO: Add input validation (protect against invalid CRNs)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE CRN= " + query;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    ;
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    int creditHours = cursor.getInt(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    ;
                    int currentEnrollment = cursor.getInt(10);
                    ;
                    String status = cursor.getString(11);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }
            // close both the cursor and the db when done
            cursor.close();
            db.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnList;
    }

    // Method to process the query of a user searching for a course by Credit Hours
    private List<CourseModel> getMatchingCredits(String query){
        List<CourseModel> returnList = new ArrayList<>();

        // TODO: Add input validation (protect against invalid Credits)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE CREDIT_HOURS= " + query;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    int creditHours = cursor.getInt(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }


            // close both the cursor and the db when done
            cursor.close();
            db.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return returnList;
    }

    // Method to process the query of a user searching for a course by Meeting Days
    private List<CourseModel> getMatchingMeetDays(String query){
        List<CourseModel> returnList = new ArrayList<>();

        // TODO: Add input validation (protect against invalid Meeting Days)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE MEET_DAYS= \"" + query + "\"";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    int creditHours = cursor.getInt(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }

            // close both the cursor and the db when done
            cursor.close();
            db.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnList;
    }

    // Method to process the query of a user searching for a course by Meeting Time
    private List<CourseModel> getMatchingMeetTime(String query){
        List<CourseModel> returnList = new ArrayList<>();

        // TODO: Add input validation (protect against invalid Meeting Times)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE MEET_TIME= \"" + query + "\"";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    ;
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    int creditHours = cursor.getInt(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    ;
                    int currentEnrollment = cursor.getInt(10);
                    ;
                    String status = cursor.getString(11);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }
            // close both the cursor and the db when done
            cursor.close();
            db.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnList;
    }

    // Method to process the query of a user searching for a course by Instructor
    private List<CourseModel> getMatchingInstructor(String query){
        List<CourseModel> returnList = new ArrayList<>();

        // TODO: Add input validation (protect against invalid Instructors)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE COURSE_INSTRUCTOR= \"" + query + "\"";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    ;
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    int creditHours = cursor.getInt(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    ;
                    int currentEnrollment = cursor.getInt(10);

                    String status = cursor.getString(11);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }

            // close both the cursor and the db when done
            cursor.close();
            db.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return returnList;
    }
}
