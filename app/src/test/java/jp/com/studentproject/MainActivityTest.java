package jp.com.studentproject;

import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    private MainActivity activity;
    private Context context;

    @Rule
    public ActivityTestRule<ShowAllListStudent> activityRule =
            new ActivityTestRule<>(ShowAllListStudent.class);

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        activityRule.getActivity();
        context = activity.getApplicationContext();
    }
    @Test
    public void testButton() {
        onView(withText(R.string.list_function_add)).perform(click());
    }


}