package com.mah.roomdatabasemultitablesexm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    myRepository repository;


    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new myRepository(application);
    }

    void insertCourse(Course course) {


            repository.insertCourse(course);


    }

    void updateCourse(Course course) {


            repository.updateCourse(course);


    }

    void deleteCourse(Course course) {


            repository.deleteCourse(course);


    }

    LiveData<List<Course>> getAllCourses(){
        return repository.getAllCourses();
    }

    LiveData<List<Course>> getAllCoursesByCourseId(int courseId){
        return repository.getAllCoursesByCourseId(courseId);
    }


    void insertStudent(Student student) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            repository.insertStudent(student);
        });
    }

    void updateStudent(Student student) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            repository.updateStudent(student);
        });
    }


    void deleteStudent(Student student) {

        myRoomDatabase.databaseWriteExecutor.execute(() -> {
            repository.deleteStudent(student);
        });
    }

    LiveData<List<Student>> getAllStudent() {
        return repository.getAllStudent();
    }

    LiveData<List<Student>> getAllStudentByCourseId(int studentId) {
        return repository.getAllStudentByCourseId(studentId);
    }

}
