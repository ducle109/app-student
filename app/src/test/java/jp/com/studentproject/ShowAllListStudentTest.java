package jp.com.studentproject;

import android.app.Dialog;
import android.content.Context;
import android.widget.ListView;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowAllListStudentTest {

    private CustomAdapter customAdapter;

    private ShowAllListStudent showAllListStudent;
    private List<Student> studentList;
    private Student student1;
    private Student student2;
    private ListView listView;
    private Dialog dialog;
    private Context context;
    private DatabaseSQLite db;

    @Rule
    public ActivityTestRule<ShowAllListStudent> activityRule =
            new ActivityTestRule<>(ShowAllListStudent.class);

    @Before
    public void setUp() throws Exception {
        showAllListStudent = Robolectric.buildActivity(ShowAllListStudent.class).get();
        activityRule.getActivity();
        //device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        context = showAllListStudent.getApplicationContext();
        db = new DatabaseSQLite(context);
        listView = (ListView) showAllListStudent.findViewById(R.id.lvShowStudent);
        student1 = new Student();
        student1.setId(1);
        student1.setName("aaaa");
        student1.setAge(22);
        student1.setSex("Male");
        student1.setPhoneNumber("aaaa");
        student1.setDate("2019");
        student1.setStClass("12A1");
        student1.setChairman("leduc");
        student1.setHobby("game");
        student1.setGrade("good");
        db.addAStudent(student1);


        student2 = new Student();
        student2.setId(2);
        student2.setName("aaaa");
        student2.setAge(22);
        student2.setSex("Male");
        student2.setPhoneNumber("aaaa");
        student2.setDate("2019");
        student2.setStClass("12A1");
        student2.setChairman("leduc");
        student2.setHobby("game");
        student2.setGrade("good");
        db.addAStudent(student2);


        // update database
        student1.setId(1);
        student1.setPhoneNumber("12345");
        student1.setDate("2015");
        student1.setStClass("12A5");
        student1.setChairman("hai");
        student1.setHobby("music");
        student1.setGrade("very good");
        db.updateStudentInformation(student1);

        Robolectric.buildActivity(ShowAllListStudent.class).create().get();


    }

    @Test
    public void testOnItemClickButton() {
        // lvShowStudent setOnClick Listener
        studentList = db.getAllStudent();

        showAllListStudent.clickListViewButton();

        onData(anything())
                .inAdapterView(withId(R.id.lvShowStudent))
                .atPosition(0)
                .check(matches(isDisplayed()))
                .perform(click(), closeSoftKeyboard());
        //student1 = studentList.get(0);
        //showAllListStudent.showInformationDialog(student1);
        onView(withText("Information"))
                .inRoot(isDialog()) // <---
                .check(matches(not(isDisplayed())));

        onView(withId(R.id.txt_get_name)).check(matches(withText("aaaa")));
        onView(withId(R.id.txt_get_age)).check(matches(withText("22")));
        onView(withId(R.id.txt_get_sex)).check(matches(withText("Male")));
        onView(withId(R.id.txt_get_phone)).check(matches(withText("12345")));
        onView(withId(R.id.txt_get_class)).check(matches(withText("12A5")));
        onView(withId(R.id.txt_get_chairman)).check(matches(withText("hai")));
        onView(withId(R.id.txt_get_hobby)).check(matches(withText("music")));
        onView(withId(R.id.txt_get_grade)).check(matches(withText("very good")));
    }
    @Test
    public void testOnItemLongClickButton() {
        studentList = db.getAllStudent();
        //showAllListStudent.clickListViewButton();
        onData(anything())
                .inAdapterView(withId(R.id.lvShowStudent))
                .atPosition(0)
                .check(matches(isDisplayed()))
                .perform(longClick());
        /*

       */
        //student1 = studentList.get(0);
        //showAllListStudent.showEditDialog(student1);
        /*onView(withText("Information"))
                .inRoot(isDialog()) // <---
                .check(matches(not(isDisplayed())));

        onView(withId(R.id.txt_get_name)).check(matches(withText("aaaa")));
        onView(withId(R.id.txt_get_age)).check(matches(withText("22")));
        onView(withId(R.id.txt_get_sex)).check(matches(withText("Male")));
        onView(withId(R.id.txt_get_phone)).check(matches(withText("12345")));
        onView(withId(R.id.txt_get_class)).check(matches(withText("12A5")));
        onView(withId(R.id.txt_get_chairman)).check(matches(withText("hai")));
        onView(withId(R.id.txt_get_hobby)).check(matches(withText("music")));
        onView(withId(R.id.txt_get_grade)).check(matches(withText("very good")));*/
    }


    @Test
    public void showEditDialog() {
        onView(withId(R.id.lvShowStudent)).perform(click()).inRoot(isDialog()).check(matches(isDisplayed()));
        showAllListStudent.showEditDialog(student1);
        dialog = new Dialog(context, R.style.Dialog);
        dialog.setTitle("Notification");
        dialog.setContentView(R.layout.custom_dialog);


    }


    @Test
    public void getShowInformation() {
        studentList = db.getAllStudent();
        Student student = studentList.get(0);
        showAllListStudent.showInformationDialog(student);
        showAllListStudent.getShowInformation(student);
        // Click on the button that shows the dialog

    }

    @Test
    public void showAlertDialog() {
    }


    @Test
    public void setAdapter() {
        showAllListStudent.setAdapter();
    }

    @Test
    public void testCustomAdapter() {
        customAdapter = new CustomAdapter(showAllListStudent, R.layout.activity_show_all_list_student, studentList);
        listView.setAdapter(customAdapter);
        assertEquals(customAdapter.getItem(0).getId(), 1);
        assertEquals(customAdapter.getItem(0).getName(), "aaaa");
        assertEquals(customAdapter.getItem(0).getAge(), 22);
        assertEquals(customAdapter.getItem(0).getSex(), "Male");
        assertEquals(customAdapter.getItem(0).getPhoneNumber(), "12345");
        assertEquals(customAdapter.getItem(0).getDate(), "2015");
        assertEquals(customAdapter.getItem(0).getStClass(), "12A5");
        assertEquals(customAdapter.getItem(0).getChairman(), "hai");
        assertEquals(customAdapter.getItem(0).getHobby(), "music");
        assertEquals(customAdapter.getItem(0).getGrade(), "very good");

        onData(anything())
                .inAdapterView(withId(R.id.lvShowStudent))
                .atPosition(1)
                .onChildView(withId(R.id.tv_id))
                .check(matches(withText("2")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void whenAddCalledAnswered() {
        ShowAllListStudent myList = mock(ShowAllListStudent.class);

    }

    @Test
    public void showToast() {
        String str = "save";

        showAllListStudent.showToast(str);

    }

    // kiem tra xem text co hua noi dung kia hay ko
    //onView(withId(R.id.text_simple)).check(matches(withText("Hello Espresso!")));

        //onView(withId(R.id.btn_add_student)).perform(click());
        /*onView(withText(str)).
                inRoot(withDecorView(not(is(showAllListStudent.getWindow().getDecorView())))).
                check(matches(isDisplayed()));*/
        //Textの内容をチェックしたい場合
        //onView(withText(str)).check(matches(isDisplayed()));

        //viewの存在をチェックしたい場合
        //onView(allOf(withId(viewId), isDisplayed())).check(matches(isDisplayed()));

        //onView(withId(buttonId)).perform(click());

        /*onData(anything())
                .inAdapterView(withId(R.id.listView1))
                .atPosition(INDEX)
                .onChildView(withText(CHECK_TEXT))
                .check(matches(isDisplayed()))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .perform(click());*/

}