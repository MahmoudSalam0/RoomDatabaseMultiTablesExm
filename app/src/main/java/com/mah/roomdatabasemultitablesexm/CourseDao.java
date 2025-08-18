package com.mah.roomdatabasemultitablesexm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {

        @Insert
        void insertCourse(Course course);

        @Update
        void updateCourse(Course course);

        @Delete
        void deleteCourse(Course course);

        @Query("SELECT * FROM Course")
        LiveData<List<Course>> getAllCourses();

        @Query("SELECT * FROM Course WHERE id = :CourseId ")
        LiveData<List<Course>> getAllCoursesByCourseId(int CourseId);


}
