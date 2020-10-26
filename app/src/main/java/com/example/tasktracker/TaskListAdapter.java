package com.example.tasktracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasktracker.databinding.TaskListItemBinding;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    
    private final LayoutInflater inflater;
    private List<Task> taskList;
    private static final String TAG = TaskListAdapter.class.getSimpleName();
    
    public TaskListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(TaskListItemBinding.inflate(inflater));
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (taskList != null) {
            Task current = taskList.get(position);
            holder.binding.taskListItemDueDate.setText(current.getDueDate());
            holder.binding.taskListItemTitle.setText(current.getTitle());
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
        
        private final TaskListItemBinding binding;
    
        public ViewHolder(TaskListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
