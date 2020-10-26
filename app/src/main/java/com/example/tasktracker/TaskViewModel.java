package com.example.tasktracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tasktracker.database.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    
    private TaskRepository mRepository;
    private LiveData<List<Task>> mTaskList;
    
    // don't reference contexts with shorter lifecycles than VM
    public TaskViewModel(Application application) {
        super(application);
        mRepository = TaskRepository.getInstance(application);
        mTaskList = mRepository.getTaskList();
    }
    
    public void insert(Task task) {
        mRepository.insert(task);
    }
    
    public void update(Task task) {
        mRepository.update(task);
    }
    
    public void delete(Task task) {
        mRepository.delete(task);
    }
    
    public LiveData<List<Task>> getTaskList() {
        return mTaskList;
    }
    
    public int getTaskCount() {
        if (mTaskList.getValue() != null) {
            return mTaskList.getValue().size();
        } else {
            return 0;
        }
    }
    
    public LiveData<List<Task>> getTasksFromCategory(String category) {
        return mRepository.getTasksFromCategory(category);
    }
}
