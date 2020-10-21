package com.example.tasktracker.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tasktracker.Task;

import java.util.List;

public class TaskRepository {
    
    // singleton
    private static TaskRepository sInstance;
    
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mTaskList;
    private TaskDatabase mDatabase;
    
    private TaskRepository(Application application) {
        mDatabase = TaskDatabase.getInstance(application);
        mTaskDao = mDatabase.taskDao();
        mTaskList = mTaskDao.getAllTasks();
    }
    
    public static TaskRepository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (TaskRepository.class) {
                if (sInstance == null) {
                    sInstance = new TaskRepository(application);
                }
            }
        }
        return sInstance;
    }
    
    // returns LiveData from Room, so the query is executed on separate thread
    public LiveData<List<Task>> getTaskList() {
        return mTaskList;
    }
    
    public LiveData<List<Task>> getTasksFromCategory(String category) {
        return mTaskDao.getCategory(category);
    }
    
    // call other queries on non-UI (background) thread, using ExecutorService
    public void insert(Task task) {
        // lambda to implement the run() method of Executor
        TaskDatabase.databaseExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }
    
    public void update(Task task) {
        TaskDatabase.databaseExecutor.execute(() -> {
            mTaskDao.update(task);
        });
    }
    
    public void delete(Task task) {
        TaskDatabase.databaseExecutor.execute(() -> {
            mTaskDao.delete(task);
        });
    }
    
}
