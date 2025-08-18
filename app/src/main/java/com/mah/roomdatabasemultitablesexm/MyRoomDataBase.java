package com.mah.roomdatabasemultitablesexm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class ,Course.class},version = 1,exportSchema = false)
abstract class myRoomDatabase extends RoomDatabase {

    public abstract StudentDao StudentDao();

    public abstract CourseDao CourseDao();


    private static volatile myRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static myRoomDatabase getDataBase(final Context context) {

        if (INSTANCE == null) {
            synchronized (myRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    myRoomDatabase.class, "my_database")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}