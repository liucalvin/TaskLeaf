package com.example.tasktracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tasktracker.databinding.FragmentTaskDetailsBinding;

import org.jetbrains.annotations.NotNull;

public class TaskDetails extends Fragment {
    
    private static final String TAG = "TaskDetails";
    private FragmentTaskDetailsBinding binding;
    
    public TaskDetails() {
        // Required empty public constructor
    }
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTaskDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}