package com.example.tasktracker.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasktracker.R;
import com.example.tasktracker.Task;
import com.example.tasktracker.TaskViewModel;

public class AddTaskFragment extends Fragment {
    
    private TextView title, description, category, dueDate, dueTime;
    private Button addTaskButton;
    
    public AddTaskFragment() {
        // Required empty public constructor
    }
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        
        addTaskButton = view.findViewById(R.id.fragment_add_task_add_button);
        title = view.findViewById(R.id.fragment_add_task_title_et);
        description = view.findViewById(R.id.fragment_add_task_desc_et);
        category = view.findViewById(R.id.fragment_add_task_category_et);
        dueDate = view.findViewById(R.id.fragment_add_task_due_date_et);
        dueTime = view.findViewById(R.id.fragment_add_task_due_time_et);
        return view;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText().toString())) {
                    Toast.makeText(getActivity(), R.string.task_title_empty_error, Toast.LENGTH_SHORT).show();
                } else {
                    Task task = new Task(title.getText().toString(),
                            description.getText().toString(),
                            category.getText().toString(),
                            dueDate.getText().toString());
                    TaskViewModel taskViewModel = new ViewModelProvider(getActivity()).get(TaskViewModel.class);
                    taskViewModel.insert(task);
                    Toast.makeText(getActivity(), R.string.task_added, Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
}