package jp.com.studentproject;

import android.app.Dialog;
import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Espresso_DemoTest {
    private Espresso_Demo espressoDemo;
    private Context context;
    private Dialog dialog;

    @Rule
    public ActivityTestRule<Espresso_Demo> activityRule =
            new ActivityTestRule<>(Espresso_Demo.class);


    @Before
    public void setUp() throws Exception {
        //espressoDemo = Robolectric.buildActivity(Espresso_Demo.class).create().get();
        espressoDemo = activityRule.getActivity();
        context = espressoDemo.getApplicationContext();

    }

    @Test
    public void testButton() {
        //onView(withText("notification")).inRoot(isDialog()).check(matches(isDisplayed()));

    }
    @Test
    public void customDialog() {
        onView(withId(R.id.btn_espresso)).perform(click());
        //onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.btn_espresso), withText("click"))).perform(click()).inRoot(isDialog()).check(matches(isDisplayed()));

        //onView(withText("hello")).check(matches(isDisplayed()));
        onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed())).perform(pressBack());
        //onView(withId(R.id.txt_msg)).check(matches(allOf(withText("do you want to click?"), isDisplayed())));
    }

}