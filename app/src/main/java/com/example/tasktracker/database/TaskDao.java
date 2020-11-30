package com.example.tasktracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tasktracker.Task;

import java.util.List;

@Dao
public interface TaskDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);
    
    @Delete
    void delete(Task task);
    
    @Update
    void update(Task task);
    
    @Query("SELECT * FROM task_table ORDER BY due_date ASC")
    LiveData<List<Task>> getAllTasks();
    
    @Query("SELECT * FROM task_table WHERE category IS :category ORDER BY due_date ASC")
    LiveData<List<Task>> getCategory(String category);
    
    @Query("DELETE FROM task_table WHERE category IS :category")
    void deleteCategory(String category);
}
