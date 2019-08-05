package jp.com.studentproject;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class School_ScheduleTest {
    private School_Schedule schollActivity;
    private Context context;
    public DatabaseSQLite db;
    private int idSchool;
    private int setIdButton;
    private List<Schoole_Time> listStudent;

    private Button mBtnModify;
    private Button btnCancel;
    private TableLayout tbLayoutStudent;
    private EditText edtText;


    @Rule
    public ActivityTestRule<School_Schedule> activityRule =
            new ActivityTestRule<>(School_Schedule.class);

    @Before
    public void setUp() throws Exception {
        schollActivity = Robolectric.buildActivity(School_Schedule.class).create().get();
        schollActivity = activityRule.getActivity();
        context = schollActivity.getApplicationContext();
        db = new DatabaseSQLite(context);
        db.createDefaultNotesIfNeed();
        listStudent = db.getAllSchooleTime();

        mBtnModify          = (Button) schollActivity.findViewById(R.id.btnModify);
        btnCancel          = (Button) schollActivity.findViewById(R.id.btnCancel);
        edtText              = (EditText) schollActivity.findViewById(R.id.edtText);
        tbLayoutStudent     = (TableLayout) schollActivity.findViewById(R.id.tbLayoutStudent);

    }

    @Test
    public void testDatabase() {
        assertEquals(1, listStudent.get(0).getId());
        assertEquals("", listStudent.get(0).getSession());
        assertEquals("数学", listStudent.get(0).getSubjects_monday());
        assertEquals("体育", listStudent.get(0).getSubjects_tuesday());
        assertEquals("古典", listStudent.get(0).getSubjects_wednesday());
        assertEquals("歴史",  listStudent.get(0).getSubjects_thursday());
        assertEquals("化学", listStudent.get(0).getSubjects_friday());
        assertEquals("9:30\n~10:15", listStudent.get(0).getTime());


    }

    @Test
    public void testOnClickCreateCase0() {
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
        int num = 0;
        onView(withId(num)).perform(longClick());
        edtText.setText("aaa");

        Button button = (Button) schollActivity.findViewById(num);
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId(num);
        schooleTime.setSubjects_tuesday(edtText.getText().toString());
        db.updateStudent(schooleTime, num);
        onView(withId(R.id.btnModify)).perform(click(), closeSoftKeyboard());

        //assertEquals("aaa", button.getText());

        /*onView(withId(num)).check(matches(withTextColor(R.color.color_black)));
        onView(withId(R.id.edtText)).perform(typeText(""));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));*/

    }
    @Test
    public void testOnClickCreateCase1() {
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
        int num = 1;
        onView(withId(num)).perform(longClick());
        edtText.setText("aaa");

        Button button = (Button) schollActivity.findViewById(num);
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId(num);
        schooleTime.setSubjects_tuesday(edtText.getText().toString());
        db.updateStudent(schooleTime, num);
        onView(withId(R.id.btnModify)).perform(click(), closeSoftKeyboard());

        //assertEquals("aaa", button.getText());

        /*onView(withId(num)).check(matches(withTextColor(R.color.color_black)));
        onView(withId(R.id.edtText)).perform(typeText(""));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));*/

    }
    @Test
    public void testOnClickCreateCase2() {
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
        int num = 2;
        onView(withId(num)).perform(longClick());
        edtText.setText("aaa");

        Button button = (Button) schollActivity.findViewById(num);
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId(num);
        schooleTime.setSubjects_tuesday(edtText.getText().toString());
        db.updateStudent(schooleTime, num);
        onView(withId(R.id.btnModify)).perform(click(), closeSoftKeyboard());

        //assertEquals("aaa", button.getText());

        /*onView(withId(num)).check(matches(withTextColor(R.color.color_black)));
        onView(withId(R.id.edtText)).perform(typeText(""));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));*/

    }
    @Test
    public void testOnClickCreateCase3() {
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
        int num = 3;
        onView(withId(num)).perform(longClick());
        edtText.setText("aaa");

        Button button = (Button) schollActivity.findViewById(num);
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId(num);
        schooleTime.setSubjects_tuesday(edtText.getText().toString());
        db.updateStudent(schooleTime, num);
        onView(withId(R.id.btnModify)).perform(click(), closeSoftKeyboard());

        //assertEquals("aaa", button.getText());

        /*onView(withId(num)).check(matches(withTextColor(R.color.color_black)));
        onView(withId(R.id.edtText)).perform(typeText(""));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));*/

    }
    @Test
    public void testOnClickCreateCase4() {
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
        int num = 4;
        onView(withId(num)).perform(longClick());
        edtText.setText("aaa");

        Button button = (Button) schollActivity.findViewById(num);
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId(num);
        schooleTime.setSubjects_tuesday(edtText.getText().toString());
        db.updateStudent(schooleTime, num);
        onView(withId(R.id.btnModify)).perform(click(), closeSoftKeyboard());

        //assertEquals("aaa", button.getText());

        /*onView(withId(num)).check(matches(withTextColor(R.color.color_black)));
        onView(withId(R.id.edtText)).perform(typeText(""));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));*/

    }
    @Test
    public void testOnClickCreateCase5() {
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
        int num = 5;
        onView(withId(num)).perform(longClick());
        edtText.setText("aaa");

        Button button = (Button) schollActivity.findViewById(num);
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId(num);
        schooleTime.setSubjects_tuesday(edtText.getText().toString());
        db.updateStudent(schooleTime, num);
        onView(withId(R.id.btnModify)).perform(click(), closeSoftKeyboard());

        //assertEquals("aaa", button.getText());

        /*onView(withId(num)).check(matches(withTextColor(R.color.color_black)));
        onView(withId(R.id.edtText)).perform(typeText(""));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));*/

    }
    @Test
    public void testOnClickCreateCase6() {
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
        int num = 6;
        onView(withId(6)).perform(longClick());
        edtText.setText("aaa");

        Button button = (Button) schollActivity.findViewById(num);
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId(num);
        schooleTime.setSubjects_tuesday(edtText.getText().toString());
        db.updateStudent(schooleTime, num);
        onView(withId(R.id.btnModify)).perform(click(), closeSoftKeyboard());

        //assertEquals("aaa", button.getText());

        /*onView(withId(num)).check(matches(withTextColor(R.color.color_black)));
        onView(withId(R.id.edtText)).perform(typeText(""));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));*/

    }


    @Test
    public void clickCancel() throws InterruptedException {
        onView(withId(R.id.btnCancel)).check(matches(isDisplayed()));
        onView(withId(3)).perform(longClick());

        Button button = (Button) schollActivity.findViewById(3);
        onView(withId(R.id.btnCancel)).perform(click());
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        onView(withId(R.id.edtText)).check(matches(withText("")));
        onView(withId(R.id.btnModify)).check(matches(not(isEnabled())));
    }

    @Test
    public void getShowData() {
        int a = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                a = 10*i + j;
                Button button = (Button) schollActivity.findViewById(a);
                onView(withId(a)).check(matches(withText(button.getText().toString())));

            }
        }
        //Button button = (Button) schollActivity.findViewById(5);
        //assertEquals(5, button);
        onView(withId(6)).check(matches(isDisplayed()));
        onView(withId(6)).perform(closeSoftKeyboard(), longClick());
        onView(withId(R.id.edtText)).check(matches(withText("数学")));
        onView(withId(2)).check(matches(withTextColor(R.color.color_red)));

    }

    public static Matcher<View> withTextColor(final int expectedId) {
        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override
            protected boolean matchesSafely(TextView textView) {
                int colorId = ContextCompat.getColor(textView.getContext(), expectedId);
                return textView.getCurrentTextColor() == colorId;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
                description.appendValue(expectedId);
            }
        };
    }

}