package com.example.tasktracker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasktracker.databinding.TaskListItemBinding;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    
    private static final String TAG = TaskListAdapter.class.getSimpleName();
    private List<Task> taskList;
    
    public TaskListAdapter() {
    
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskListItemBinding binding = TaskListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (taskList != null) {
            Task current = taskList.get(position);
            holder.binding.taskListItemTitle.setText(current.getTitle());
            holder.binding.taskListItemDueDate.setText(current.getDueDate());
            holder.binding.taskListItemDueTime.setText(current.getDueTime());
            Log.d(TAG, current.getDueDate() + " " + current.getTitle());
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
    
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        
        private final TaskListItemBinding binding;
        
        public ViewHolder(TaskListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }
        
        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
        }



//        interface ITaskViewHolderListener {
//            *
//             * Called when a task list item is clicked
//             * @param position the position of the task in the list
//
//            void onTaskItemClicked(int position);
//        }
    }
}
