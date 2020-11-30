package com.example.tasktracker.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasktracker.R;
import com.example.tasktracker.Task;
import com.example.tasktracker.TaskViewModel;
import com.example.tasktracker.databinding.FragmentAddTaskBinding;

import java.util.Objects;

public class AddTaskFragment extends Fragment {
    
    private static final String TAG = AddTaskFragment.class.getSimpleName();
    private FragmentAddTaskBinding binding;
    
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
        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        binding.fragmentAddTaskAddButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.fragmentAddTaskTitleEt.getText().toString())) {
                Toast.makeText(getActivity(), R.string.task_title_empty_error, Toast.LENGTH_SHORT).show();
            } else {
                Task task = new Task(binding.fragmentAddTaskTitleEt.getText().toString());
                task.setCategory(binding.fragmentAddTaskCategoryEt.toString());
                task.setDescription(binding.fragmentAddTaskDescEt.toString());
                task.setDueDate(binding.fragmentAddTaskDueDateEt.toString());
                task.setDueTime(binding.fragmentAddTaskDueTimeEt.toString());
                Log.d(TAG, binding.fragmentAddTaskDueDateEt.toString());
                TaskViewModel taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
                taskViewModel.insert(task);
                Toast.makeText(getActivity(), R.string.task_added, Toast.LENGTH_SHORT).show();
                clearAllFields();
            }
        });
        
    }
    
    private void clearAllFields() {
        binding.fragmentAddTaskTitleEt.setText("");
        binding.fragmentAddTaskCategoryEt.setText("");
        binding.fragmentAddTaskDescEt.setText("");
        binding.fragmentAddTaskDueDateEt.setText("");
        binding.fragmentAddTaskDueTimeEt.setText("");
    }
    
}