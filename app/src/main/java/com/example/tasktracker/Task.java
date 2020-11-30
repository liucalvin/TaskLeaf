package com.example.tasktracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "task_table")
public class Task implements Serializable {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String category;
    @ColumnInfo(name = "due_date")
    private String dueDate;
    @ColumnInfo(name = "due_time")
    private String dueTime;
    
    public Task(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getDueDate() {
        return dueDate;
    }
    
    public String getDueTime() {
        return dueTime;
    }
    
    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
