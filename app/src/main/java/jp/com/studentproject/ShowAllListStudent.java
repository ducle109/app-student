package jp.com.studentproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.List;

public class ShowAllListStudent extends AppCompatActivity {

    private ListView lvStudent;
    private DatabaseSQLite databaseSQLite;
    private CustomAdapter customAdapter;
    private List<Student> studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_list_student);
        lvStudent = (ListView) findViewById(R.id.lvStudent);

        databaseSQLite = new DatabaseSQLite(this);
        studentList = databaseSQLite.getAllStudent();
        setAdapter();
    }


    public void setAdapter() {
        if(customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.activity_show_all_list_student, studentList);
            lvStudent.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount() -1);
        }
    }

}
