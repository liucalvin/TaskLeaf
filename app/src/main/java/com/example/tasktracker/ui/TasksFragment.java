package com.example.tasktracker.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasktracker.R;
import com.example.tasktracker.Task;
import com.example.tasktracker.TaskListAdapter;
import com.example.tasktracker.TaskViewModel;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TasksFragment extends Fragment {
    
    private static final String TAG = TasksFragment.class.getSimpleName();
    private TextView tasksLeft;
    private RecyclerView taskRecyclerView;
    private TaskViewModel taskViewModel;
    
    public TasksFragment() {
        // Required empty public constructor
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get view model from vm provider
        taskViewModel = new ViewModelProvider(getActivity()).get(TaskViewModel.class);
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        
        tasksLeft = view.findViewById(R.id.fragment_tasks_left_to_complete);
        taskRecyclerView = view.findViewById(R.id.fragment_tasks_recycler_view);
        
        return view;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TaskListAdapter adapter = new TaskListAdapter(getActivity());
        taskRecyclerView.setAdapter(adapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksLeft.setText(String.format(Locale.CANADA, getString(R.string.tasks_left_title), adapter.getItemCount()));
    
        // observe data
        taskViewModel.getTaskList().observe(requireActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> taskList) {
                // update cached copy of tasklist
                adapter.setTaskList(taskList);
                Log.d(TAG, "Task List changed!");
                adapter.notifyDataSetChanged();
            }
        });
    
    }
}