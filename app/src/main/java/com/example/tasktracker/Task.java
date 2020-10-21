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
    
    public Task(String title, String description, String category, String dueDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
    }
    
    public void setId(int id) {
        this.id = id;
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
}
