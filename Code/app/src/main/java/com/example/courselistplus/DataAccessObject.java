package com.example.courselistplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides data operations without exposing database details
 *
 * @author abdih
 */
public class DataAccessObject extends SQLiteOpenHelper {

    // Constants to save time/avoid errors when referring to database elements
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "courses.db";
    private static final String COURSES_TABLE = "COURSES_TABLE";
    private static final String COLUMN_CRN = "CRN";
    private static final String COLUMN_COURSE_ID = "COURSE_ID";
    private static final String COLUMN_COURSE_ATTRIBUTE = "COURSE_ATTRIBUTE";
    private static final String COLUMN_COURSE_TITLE = "COURSE_TITLE";
    private static final String COLUMN_COURSE_INSTRUCTOR = "COURSE_INSTRUCTOR";
    private static final String COLUMN_CREDIT_HOURS = "CREDIT_HOURS";
    private static final String COLUMN_MEET_DAYS = "MEET_DAYS";
    private static final String COLUMN_MEET_TIME = "MEET_TIME";
    private static final String COLUMN_PROJECTED_ENROLLMENT = "PROJECTED_ENROLLEMENT";
    private static final String COLUMN_CURRENT_ENROLLMENT = "CURRENT_ENROLLMENT";
    private static final String COLUMN_STATUS = "STATUS";
    private static final String COLUMN_TOTAL_RATING = "TOTAL_RATING";
    private static final String COLUMN_NUM_RATINGS = "NUM_RATINGS";
    private static final String COLUMN_COURSE_DESCRIPTION = "COURSE_DESCRIPTION";

    /**
     * Data Access Object (DAO) constructor
     *
     * @param context Application context to use for locating paths to the the database
     */
    public DataAccessObject(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate is called whenever the app is freshly installed to create and launch the database
     *
     * @param sqLiteDatabase The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + COURSES_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CRN + " INT, " + COLUMN_COURSE_ID + " TEXT, " + COLUMN_COURSE_ATTRIBUTE + " TEXT, " +
                COLUMN_COURSE_TITLE + " TEXT, " + COLUMN_COURSE_INSTRUCTOR + " TEXT, " + COLUMN_CREDIT_HOURS +
                " INT, " + COLUMN_MEET_DAYS + " TEXT, " + COLUMN_MEET_TIME + " TEXT, " + COLUMN_PROJECTED_ENROLLMENT
                + " INT, " + COLUMN_CURRENT_ENROLLMENT + " INT, " + COLUMN_STATUS + " TEXT, " + COLUMN_TOTAL_RATING
                + " INT, " + COLUMN_NUM_RATINGS + " INT, " + COLUMN_COURSE_DESCRIPTION + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    /**
     * Unused method, typically intended to store code regarding updates to the database version.
     *
     * @param sqLiteDatabase The database.
     * @param oldVersion     The old database version.
     * @param newVersion     The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    /**
     * Helper method for adding an item into the database
     *
     * @param courseModel The course to add
     */
    protected void addOne(CourseModel courseModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE CRN= " + courseModel.getCRN();
        Cursor cursor = db.rawQuery(queryString, null);

        // Prevents inserting a course if it's already in the database
        if (cursor.moveToFirst()) {
            // duplicate insertion, do nothing
        } else {
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
            cv.put(COLUMN_TOTAL_RATING, courseModel.getTotalRating());
            cv.put(COLUMN_NUM_RATINGS, courseModel.getNumRatings());
            cv.put(COLUMN_COURSE_DESCRIPTION, courseModel.getCourseDescription());

            // insert method returns success or failure (-1), can use to check success
            db.insert(COURSES_TABLE, null, cv);
        }

        cursor.close();
        db.close();
    }

    /**
     * User query processing method, matches the the query by filter type then passes along to
     * the appropriate helper method
     *
     * @param filter the filter the user is searching based on, e.g. Instructor
     * @param query  the query the user typed into the search view on the UI, e.g. Kemper, Peter
     * @return List of courses (Course Model objects) from the database matching the query
     */
    public List<CourseModel> getMatchingCourses(String filter, String query) {
        List<CourseModel> returnList = new ArrayList<>();

        switch (filter) {
            case "CRN":
                returnList = getMatchingCRN(query);
                break;
            case "Course Attribute":
                returnList = getMatchingCourseAttribute(query);
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

    /**
     * Method to return all the courses currently in the database
     *
     * @return List of all courses (Course Model objects) in the database
     */
    public List<CourseModel> getAllCourses() {
        List<CourseModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COURSES_TABLE;
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
                String creditHours = cursor.getString(6);
                String meetDays = cursor.getString(7);
                String meetTime = cursor.getString(8);
                int projectedEnrollment = cursor.getInt(9);
                int currentEnrollment = cursor.getInt(10);
                String status = cursor.getString(11);
                int totalRating = cursor.getInt(12);
                int numRatings = cursor.getInt(13);
                String courseDescription = cursor.getString(14);

                CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                        CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                        creditHours, meetDays, meetTime, projectedEnrollment,
                        currentEnrollment, status, totalRating, numRatings, courseDescription);

                returnList.add(newCourseModel);
            } while (cursor.moveToNext());
        } else {
            // failure, is the database empty?
        }

        // close both the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }

    /**
     * Method to process the query of a user searching for a course by CRN
     *
     * @param query the query the user typed into the search view on the UI, e.g. 25722
     * @return List of courses (Course Model objects) from the database matching the query
     */
    private List<CourseModel> getMatchingCRN(String query) {
        List<CourseModel> returnList = new ArrayList<>();

        // TODO: Add input validation (protect against invalid CRNs)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE " + COLUMN_CRN + "= " + query;
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
                    String creditHours = cursor.getString(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);
                    int totalRating = cursor.getInt(12);
                    int numRatings = cursor.getInt(13);
                    String courseDescription = cursor.getString(14);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status, totalRating, numRatings, courseDescription);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }
            // close both the cursor and the db when done
            cursor.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    /**
     * Method to process the query of a user searching for a course by Credit Hours
     *
     * @param query the query the user typed into the search view on the UI, e.g. 4
     * @return List of courses (Course Model objects) from the database matching the query
     */
    private List<CourseModel> getMatchingCredits(String query) {
        List<CourseModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // TODO: Add input validation (protect against invalid Credits)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE " + COLUMN_CREDIT_HOURS +
                    "= \"" + query + "\"";

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    String creditHours = cursor.getString(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);
                    int totalRating = cursor.getInt(12);
                    int numRatings = cursor.getInt(13);
                    String courseDescription = cursor.getString(14);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status, totalRating, numRatings, courseDescription);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }

            // close both the cursor and the db when done
            cursor.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return returnList;
    }

    /**
     * Method to process the query of a user searching for a course by Meeting Days
     *
     * @param query the query the user typed into the search view on the UI, e.g. MWF
     * @return List of courses (Course Model objects) from the database matching the query
     */
    private List<CourseModel> getMatchingMeetDays(String query) {
        List<CourseModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // TODO: Add input validation (protect against invalid Meeting Days)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE " + COLUMN_MEET_DAYS +
                    "= \"" + query + "\"";

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    String creditHours = cursor.getString(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);
                    int totalRating = cursor.getInt(12);
                    int numRatings = cursor.getInt(13);
                    String courseDescription = cursor.getString(14);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status, totalRating, numRatings, courseDescription);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }

            // close both the cursor and the db when done
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return returnList;
    }

    /**
     * Method to process the query of a user searching for a course by Meeting Time
     *
     * @param query the query the user typed into the search view on the UI, e.g. 1200-1250
     * @return List of courses (Course Model objects) from the database matching the query
     */
    private List<CourseModel> getMatchingMeetTime(String query) {
        List<CourseModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // TODO: Add input validation (protect against invalid Meeting Times)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE " + COLUMN_MEET_TIME +
                    "= \"" + query + "\"";

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    String creditHours = cursor.getString(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);
                    int totalRating = cursor.getInt(12);
                    int numRatings = cursor.getInt(13);
                    String courseDescription = cursor.getString(14);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status, totalRating, numRatings, courseDescription);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }
            // close both the cursor and the db when done
            cursor.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return returnList;
    }

    /**
     * Method to process the query of a user searching for a course by Instructor
     *
     * @param query the query the user typed into the search view on the UI, e.g. Kemper, Peter
     * @return List of courses (Course Model objects) from the database matching the query
     */
    private List<CourseModel> getMatchingInstructor(String query) {
        List<CourseModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // TODO: Add input validation (protect against invalid Instructors)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE " +
                    COLUMN_COURSE_INSTRUCTOR + "= \"" + query + "\"";

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    String creditHours = cursor.getString(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);
                    int totalRating = cursor.getInt(12);
                    int numRatings = cursor.getInt(13);
                    String courseDescription = cursor.getString(14);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status, totalRating, numRatings, courseDescription);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }

            // close both the cursor and the db when done
            cursor.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return returnList;
    }

    /**
     * Method to process the query of a user searching for a course by course attribute
     *
     * @param query the query the user typed into the search view on the UI, e.g. C200
     * @return List of courses (Course Model objects) from the database matching the query
     */
    private List<CourseModel> getMatchingCourseAttribute(String query) {
        List<CourseModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // TODO: Add input validation (protect against invalid course attributes)
        try {
            String queryString = "SELECT * FROM " + COURSES_TABLE + " WHERE " +
                    COLUMN_COURSE_ATTRIBUTE + " LIKE \"%" + query + "%\"";

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int coursePrimaryKey = cursor.getInt(0);
                    int CRN = cursor.getInt(1);
                    String courseID = cursor.getString(2);
                    String courseAttribute = cursor.getString(3);
                    String courseTitle = cursor.getString(4);
                    String courseInstructor = cursor.getString(5);
                    String creditHours = cursor.getString(6);
                    String meetDays = cursor.getString(7);
                    String meetTime = cursor.getString(8);
                    int projectedEnrollment = cursor.getInt(9);
                    int currentEnrollment = cursor.getInt(10);
                    String status = cursor.getString(11);
                    int totalRating = cursor.getInt(12);
                    int numRatings = cursor.getInt(13);
                    String courseDescription = cursor.getString(14);

                    CourseModel newCourseModel = new CourseModel(coursePrimaryKey,
                            CRN, courseID, courseAttribute, courseTitle, courseInstructor,
                            creditHours, meetDays, meetTime, projectedEnrollment,
                            currentEnrollment, status, totalRating, numRatings, courseDescription);

                    returnList.add(newCourseModel);
                } while (cursor.moveToNext());
            }

            // close both the cursor and the db when done
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return returnList;
    }
}