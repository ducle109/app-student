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

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowAllListStudentTest {

    private CustomAdapter customAdapter;

    private ShowAllListStudent showAllListStudent;
    private List<Student> listStudent;
    private Student student1;
    private Student student2;
    private ListView listView;
    private Dialog dialog;
    private Context context;
    public DatabaseSQLite db;

    @Rule
    public ActivityTestRule<ShowAllListStudent> activityRule =
            new ActivityTestRule<>(ShowAllListStudent.class);

    @Before
    public void setUp() throws Exception {
        showAllListStudent = Robolectric.buildActivity(ShowAllListStudent.class).create().get();
        showAllListStudent = activityRule.getActivity();
        context = showAllListStudent.getApplicationContext();
        db = new DatabaseSQLite(context);

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


        listView = showAllListStudent.findViewById(R.id.lvShowStudent);
        dialog = new Dialog(context);

    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(showAllListStudent);

    }

    @Test
    public void showInformationDialog() {
        listStudent = db.getAllStudent();

        List<Student> list = new ArrayList<Student>();
        list.add(student1);
        list.add(student2);


        for(int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).getId(), listStudent.get(i).getId());
            assertEquals(list.get(i).getName(), listStudent.get(i).getName());
            assertEquals(list.get(i).getAge(), listStudent.get(i).getAge());
            assertEquals(list.get(i).getSex(), listStudent.get(i).getSex());
            assertEquals(list.get(i).getPhoneNumber(), listStudent.get(i).getPhoneNumber());
            assertEquals(list.get(i).getDate(), listStudent.get(i).getDate());
            assertEquals(list.get(i).getStClass(), listStudent.get(i).getStClass());
            assertEquals(list.get(i).getChairman(), listStudent.get(i).getChairman());
            assertEquals(list.get(i).getHobby(), listStudent.get(i).getHobby());
            assertEquals(list.get(i).getGrade(), listStudent.get(i).getGrade());
        }

    }



    @Test
    public void showEditDialog() {
        onView(withId(R.id.lvShowStudent)).perform(click()).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.tv_id)).check(matches(isDisplayed()));

    }


    @Test
    public void getShowInformation() {
        listStudent = db.getAllStudent();
        Student student = listStudent.get(0);
        showAllListStudent.showInformationDialog(student);
        showAllListStudent.getShowInformation(student);
        // Click on the button that shows the dialog

    }

    @Test
    public void showAlertDialog() {


    }


    @Test
    public void setAdapter() {
        listStudent = db.getAllStudent();
        customAdapter = new CustomAdapter(showAllListStudent, R.layout.activity_show_all_list_student, listStudent);
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
    public void showToast() {
        String str = "save";

        showAllListStudent.showToast(str);

        //onView(withText(str)).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        /*onView(withText(str)).
                inRoot(withDecorView(not(is(showAllListStudent.getWindow().getDecorView())))).
                check(matches(isDisplayed()));*/

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