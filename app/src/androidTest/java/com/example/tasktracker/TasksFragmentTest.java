package com.example.tasktracker;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.tasktracker.ui.TasksFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TasksFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule
            = new ActivityScenarioRule<>(new Intent(ApplicationProvider.getApplicationContext(),
            MainActivity.class));
    
    @Test
    public void startUpUI() {
        // should load TasksFragment
        onView(withId(R.id.tasksFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void switchToAddTasksFragment() {
        // when drawer layout is pressed, should switch to appropriate fragment
        onView(withId(R.id.nav_current_tasks)).perform(click());
        onView(withId(R.id.addTaskFragment)).check(matches(isDisplayed()));
    }
    

}
