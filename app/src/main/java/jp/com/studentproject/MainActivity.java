package jp.com.studentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAddStudent;
    private Button btnShowStudent;
    private Button btnSearch;
    private Button btnSchedule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity(new Intent(MainActivity.this, MainActivity.class));

       init();
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Student_Management.class);
                startActivity(intent);
            }
        });
        btnShowStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAllListStudent.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchStudent.class);
                startActivity(intent);
            }
        });
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, School_Schedule.class);
                startActivity(intent);
            }
        });



    }
    public void init() {
        btnAddStudent           = (Button) findViewById(R.id.btn_add_student);
        btnShowStudent          = (Button) findViewById(R.id.btn_show);
        btnSearch                = (Button) findViewById(R.id.btn_search);
        btnSchedule              = (Button) findViewById(R.id.btn_timetable);

    }
}
