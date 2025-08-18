package com.mah.roomdatabasemultitablesexm;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Course.class, parentColumns = {"id"}, childColumns = {"course_id"}))
@TypeConverters(Converter.class)
public class Student
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String department;
    private byte[] photo;
    private Date  BirthDate;
    private int course_id;


    public Student(){}
    public Student(String name, byte[]photo, Date birthDate, String department, int course_id) {
        this.name = name;
        this.photo = photo;
        this.BirthDate = birthDate;
        this.department = department;
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
