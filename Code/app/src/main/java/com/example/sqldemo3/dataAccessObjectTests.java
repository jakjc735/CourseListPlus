package com.example.sqldemo3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

import android.database.sqlite.SQLiteDatabase;

import androidx.core.database.sqlite.SQLiteDatabaseKt;
import androidx.test.InstrumentationRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class dataAccessObjectTests {

    private DataAccessObject dataAccessObject;
    SQLiteDatabase db;

    @Before
    public void setUp(){
        dataAccessObject = new DataAccessObject(InstrumentationRegistry.getTargetContext());
        db = dataAccessObject.getWritableDatabase();
    }

    @After
    public void finish() {
        db.close();
    }

//    @Test
//    public void testPreConditions() {
//        assertNotNull(db);
//    }
}
