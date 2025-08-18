package com.mah.roomdatabasemultitablesexm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String courseName;
    private int noOfStudents;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public Course(String courseName, int noOfStudents) {
        this.courseName = courseName;
        this.noOfStudents = noOfStudents;
    }
}
