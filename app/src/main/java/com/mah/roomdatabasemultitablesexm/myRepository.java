package com.mah.roomdatabasemultitablesexm;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public class myRepository {
    private StudentDao studentDao;
    private CourseDao courseDao;

    myRepository(Application application){
        myRoomDatabase db = myRoomDatabase.getDataBase(application);

        studentDao = db.StudentDao();
        courseDao = db.CourseDao();
    }

    void insertCourse(Course course) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.insertCourse(course);
        });

    }

    void updateCourse(Course course) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.updateCourse(course);
        });

    }

    void deleteCourse(Course course) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourse(course);
        });

    }

    LiveData<List<Course>> getAllCourses(){
        return courseDao.getAllCourses();
    }

    LiveData<List<Course>> getAllCoursesByCourseId(int courseId){
        return courseDao.getAllCoursesByCourseId(courseId);
    }


    void insertStudent(Student student) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.insertStudent(student);
        });
    }

    void updateStudent(Student student) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(student);
        });
    }


    void deleteStudent(Student student) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(student);
        });
    }

    LiveData<List<Student>> getAllStudent() {
        return studentDao.getAllStudent();
    }

    LiveData<List<Student>> getAllStudentByCourseId(int studentId) {
        return studentDao.getAllStudentByCourseId(studentId);
    }

}
