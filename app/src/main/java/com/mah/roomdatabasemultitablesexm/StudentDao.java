package com.mah.roomdatabasemultitablesexm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudent();

    @Query("SELECT * FROM Student WHERE course_id = :CourseId ")
    LiveData<List<Student>> getAllStudentByCourseId(int CourseId);


}
