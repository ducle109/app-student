package jp.com.studentproject;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Student_ManagementTest {
    private Student_Management studentManagement;

    private CustomAdapter customAdapter;
    private List<Student> listStudent;
    private ListView listView;
    private Student student1;

    private Context context;
    public DatabaseSQLite db;

    private Button btnUpdate;

    private EditText editName;
    private EditText editAge;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private EditText editPhone;

    @Rule
    public ActivityTestRule<Student_Management> activityRule =
            new ActivityTestRule<>(Student_Management.class);

    @Before
    public void setUp() throws Exception {
        studentManagement = Robolectric.buildActivity(Student_Management.class).create().get();
        studentManagement = activityRule.getActivity();
        context = studentManagement.getApplicationContext();
        db = new DatabaseSQLite(context);

        student1 = new Student();
        student1.setId(1);
        student1.setName("le");
        student1.setAge(22);
        student1.setSex("Male");
        student1.setPhoneNumber("aaaa");
        student1.setDate("2019");
        student1.setStClass("12A1");
        student1.setChairman("leduc");
        student1.setHobby("game");
        student1.setGrade("good");
        db.addAStudent(student1);

        Espresso.closeSoftKeyboard();
        listView = (ListView) studentManagement.findViewById(R.id.lvStudent);
        listStudent = db.getAllStudent();

        btnUpdate = (Button) studentManagement.findViewById(R.id.btn_update);

        editName = (EditText) studentManagement.findViewById(R.id.edt_name);
        editAge = (EditText) studentManagement.findViewById(R.id.edt_age);
        radioButtonMale = (RadioButton) studentManagement.findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) studentManagement.findViewById(R.id.radioButton_female);
        editPhone = (EditText) studentManagement.findViewById(R.id.edt_number);


    }

    @Test
    public void testButtonSaveNotNull() {
        Student student = createStudent();
        assertNotNull(student);
        //db.addStudent(student);
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_save)).perform(click());
        //onView(withText("Added !!!")).inRoot(isDialog()).check(matches(isDisplayed()));

    } @Test
    public void testButtonSaveNull() {
        Student student = createStudentNull();
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_save)).perform(click());

    }
    public Student createStudentNull() {
        return null;
    }

    public Student createStudent() {
        String name = "";
        int age = 0;
        String sex = "";
        String phone = "";

        editName.setText("hello");
        editAge.setText("22");
        editPhone.setText("555");
        radioButtonMale.setChecked(true);
        radioButtonFemale.setChecked(true);

        name = editName.getText().toString();
        age = Integer.parseInt(String.valueOf(editAge.getText()));
        phone = editPhone.getText().toString();
        sex = radioButtonMale.getText().toString();
        closeSoftKeyboard();
        return new Student(name, age, sex, phone);
    }

    @Test
    public void testUpdateListStudent() {
        studentManagement.updateListStudent();

        //assertNotNull(listStudent);
    }

    @Test
    public void testOnItemClickListView() {
        /*onData(anything())
                .inAdapterView(withId(R.id.lvStudent))
                .atPosition(0)
                .check(matches(isDisplayed()))
                .perform(click());*/
    }

    @Test
    public void testButtonUpdate() {
        /*customAdapter = new CustomAdapter(studentManagement, R.layout.show_all_student, listStudent);
        listView.setAdapter(customAdapter);

        btnUpdate.setEnabled(true);
        *//*onData(anything())
                .inAdapterView(withId(R.id.lvStudent))
                .atPosition(0)
                .onChildView(withId(R.id.tv_name))
                .check(matches(withText("le")))
                .check(matches(isDisplayed()));*//*
        onData(anything())
                .inAdapterView(withId(R.id.lvStudent))
                .atPosition(0)
                .onChildView(withId(R.id.tv_name))
                .check(matches(withText("le")))
                .check(matches(isDisplayed()));
        onData(hasToString(startsWith("Item Text")))
                .inAdapterView(withId(R.id.lvStudent)).atPosition(0)
                .perform(click());*/
    }


    @Test
    public void testLvStudent() {
        Espresso.closeSoftKeyboard();
        //onView(withId(R.id.lvStudent)).perform(click());
    }
    @Test
    public void showAlertDialog() {
    }

    @Test
    public void initWidget() {
    }

    @Test
    public void setAdapter() {
        // 2回目から呼ぶと値があります。一回目はNullから宣言する
        studentManagement.setAdapter();

    }

    @Test
    public void setTextNull() {
        studentManagement.setTextNull();
        onView(withId(R.id.edt_id)).check(matches(withText("")));
        onView(withId(R.id.edt_name)).perform(typeText(""));
        onView(withId(R.id.edt_age)).perform(typeText(""));
        onView(withId(R.id.edt_number)).perform(typeText(""));
        onView(withId(R.id.radioButton_male)).check(matches(not(isChecked())));
        onView(withId(R.id.radioButton_female)).check(matches(not(isChecked())));
    }


    @Test
    public void showToast() {
    }
}