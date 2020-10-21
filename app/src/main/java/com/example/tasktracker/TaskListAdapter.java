package com.example.tasktracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    
    private final LayoutInflater inflater;
    private List<Task> taskList;
    
    public TaskListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.task_list_item, parent, false);
        return new ViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (taskList != null) {
            Task current = taskList.get(position);
            holder.title.setText(current.getTitle());
            holder.dueDate.setText(current.getDueDate());
        } else {
            // data not ready yet
        }
    }
    
    @Override
    public int getItemCount() {
        return (taskList != null ? taskList.size() : 0);
    }
    
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        
        private final TextView title, dueDate;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_list_item_title);
            dueDate = itemView.findViewById(R.id.task_list_item_due_date);
        }
    }
}
