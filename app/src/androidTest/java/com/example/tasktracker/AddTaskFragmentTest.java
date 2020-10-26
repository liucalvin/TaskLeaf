package com.example.tasktracker;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.tasktracker.ui.TasksFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class AddTaskFragmentTest {
    
    @Rule
    public ActivityScenarioRule<MainActivity> rule
            = new ActivityScenarioRule<>(new Intent(ApplicationProvider.getApplicationContext(),
            MainActivity.class));
    
    @Before
    public void launchAddTaskFragment() {
        FragmentScenario<TasksFragment> scenario =
                FragmentScenario.launchInContainer(TasksFragment.class);
        scenario.recreate();
    }
    
    @Test
    public void testEditTextHints() {
        
        onView(withId(R.id.fragment_add_task_title_et)).check(matches(withHint("Title")));
        // add text, then delete
        onView(withId(R.id.fragment_add_task_title_et))
                .perform(typeText("Test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.fragment_add_task_title_et)).perform(clearText());
        
        // check if hint still visible
        onView(withId(R.id.fragment_add_task_title_et)).check(matches(withHint("Title")));
        
    }
    
    @Test
    public void displaysToastWhenTaskAdded() {
        // when no title, invalid input
        onView(withId(R.id.fragment_add_task_add_button)).perform(click());
        rule.getScenario().onActivity(activity -> {
            onView(withText(R.string.app_name + ": " + R.string.task_added))
                    .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                    .check(matches(withText(R.string.task_title_empty_error)));
        });
        
        // with title, valid input
        onView(withId(R.id.fragment_add_task_title_et))
                .perform(typeText("Test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.fragment_add_task_add_button)).perform(click());
        rule.getScenario().onActivity(activity2 -> {
            onView(withText(R.string.app_name + ": " + R.string.task_added))
                    .inRoot(withDecorView(not(activity2.getWindow().getDecorView())))
                    .check(matches(isDisplayed()));
        });
    }
}
