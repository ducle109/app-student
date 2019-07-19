package jp.com.studentproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.List;

import static jp.com.studentproject.R.*;

public class School_Schedule extends AppCompatActivity implements View.OnClickListener {

    private DatabaseSQLiteSchool databaseSQLiteSchool;
    private List<Schoole_Time> studentList;

    private Button btnModify;
    private EditText edtText;
    private TableLayout tbLayoutStudent;
    private int idSchool;
    int data[][] = new int[7][7] ;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_school__schedule);
        init();

        databaseSQLiteSchool = new DatabaseSQLiteSchool(this);
        databaseSQLiteSchool.createDefaultNotesIfNeed();
        studentList =  databaseSQLiteSchool.getAllSchooleTime();
        getShowData();

        btnModify.setOnClickListener(this);

    }

    // click edit time
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        Schoole_Time schooleTime = new Schoole_Time();
        schooleTime.setId((idSchool/10)+1);
        switch (idSchool%10) {
            case 0:
                schooleTime.setSession(edtText.getText()+"");
                break;
            case 1:
                schooleTime.setTime(edtText.getText()+"");
                break;
            case 2:
                schooleTime.setSubjects_monday(edtText.getText()+"");
                break;
            case 3:
                schooleTime.setSubjects_tuesday(edtText.getText()+"");
                break;
            case 4:
                schooleTime.setSubjects_wednesday(edtText.getText()+"");
            break;
            case 5:
                schooleTime.setSubjects_thursday(edtText.getText()+"");
                break;
            case 6:
                schooleTime.setSubjects_friday(edtText.getText()+"");
                break;
        }

        int result = databaseSQLiteSchool.updateStudent(schooleTime, idSchool % 10);
        Button button = (Button) findViewById(idSchool);
        button.setText(edtText.getText()+"");


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void getShowData() {
        for(int i = 0; i < 7; i++) {
            TableRow tableRow = new TableRow(this);
            for(int j = 0; j < 7; j++) {
                Schoole_Time schooleTime = studentList.get(i);
                Button button = new Button(this);
                data[i][j] = 10*i + j;
                // id 行
                button.setId(data[i][j]);

                switch (j) {
                    case 0:
                        button.setText(schooleTime.getSession()+"");
                        break;
                    case 1:
                        button.setHeight(50);
                        button.setTextSize(11);
                        button.setTranslationY(-15);
                        button.setText(schooleTime.getTime()+"");
                        break;
                    case 2:
                        button.setText(schooleTime.getSubjects_monday()+"");
                        break;
                    case 3:
                        button.setText(schooleTime.getSubjects_tuesday()+"");
                        break;
                    case 4:
                        button.setText(schooleTime.getSubjects_wednesday()+"");
                        break;
                    case 5:
                        button.setText(schooleTime.getSubjects_thursday()+"");
                        break;
                    case 6:
                        button.setText(schooleTime.getSubjects_friday()+"");
                        break;
                }
                button.setBackground(getResources().getDrawable(drawable.custom_button));

                tableRow.addView(button);
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Button bt = (Button) view;
                        String str = bt.getText().toString();
                        idSchool = bt.getId();
                        edtText.setText(str);
                        return false;
                    }
                });

            }

            tbLayoutStudent.addView(tableRow);
        }
    }


    public void init() {
        btnModify             = (Button) findViewById(id.btnModify);
        edtText               = (EditText) findViewById(id.edtText);
        tbLayoutStudent      = (TableLayout) findViewById(id.tbLayoutStudent);
    }


    public void showToast(String msg) {
        Toast.makeText(School_Schedule.this, msg, Toast.LENGTH_SHORT).show();
    }


}
