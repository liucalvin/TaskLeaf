package com.example.tasktracker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabase.Callback;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tasktracker.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Task.class, version = 2)
public abstract class TaskDatabase extends RoomDatabase {
    
    public abstract TaskDao taskDao();
    
    // singleton pattern
    private static volatile TaskDatabase sInstance;
    private static final String DATABASE_NAME = "task_database";
    
    // create an ExecutorService to sun database ops asynchronously on background thread
    private static final int NUM_OF_THREADS = 4;
    public static final ExecutorService databaseExecutor =
            Executors.newFixedThreadPool(NUM_OF_THREADS);
    
    
    // returns the singleton, using double checked locking
    static TaskDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (TaskDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, DATABASE_NAME)
                                        .fallbackToDestructiveMigration()
                                        .build();
                }
            }
        }
        
        return sInstance;
    }
    
    
}
