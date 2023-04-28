package com.example.courselistplus;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * This object holds student metadata (name, major, and academic year)
 *
 * @author amirshariatmadari
 */
public class studentDataObject implements Parcelable {
    private String name;
    private String major;
    private String year;

    public studentDataObject(String name, String major, String year) {
        this.name = name;
        this.major = major;
        this.year = year;
    }

    protected studentDataObject(Parcel in) {
        name = in.readString();
        major = in.readString();
        year = in.readString();
    }

    public static final Creator<studentDataObject> CREATOR = new Creator<studentDataObject>() {
        @Override
        public studentDataObject createFromParcel(Parcel in) {
            return new studentDataObject(in);
        }

        @Override
        public studentDataObject[] newArray(int size) {
            return new studentDataObject[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(major);
        parcel.writeString(year);
    }
}
