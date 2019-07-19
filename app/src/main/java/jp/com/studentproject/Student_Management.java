package jp.com.studentproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

import java.util.List;

public class Student_Management extends AppCompatActivity {
    private EditText editId;
    private EditText editName;
    private EditText editAge;
    private EditText editPhone;
    private ListView lvStudent;

    private Button btnSave;
    private Button btnUpdate;
    private Button btnDelete;
    private RadioGroup radioGroupCharacter;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;

    private DatabaseSQLite databaseSQLite;
    private CustomAdapter customAdapter;
    private List<Student> studentList;

    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__management);

        databaseSQLite = new DatabaseSQLite(this);
        initWidget();
        studentList = databaseSQLite.getAllStudent();
        setAdapter();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = createStudent();
                // Check if there is data
                if (student != null) {
                    databaseSQLite.addStudent(student);
                    showToast("Added !!!");
                    updateListStudent();
                    setAdapter();
                    setTextNull();
                }
            }
        });

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = studentList.get(position);
                editId.setText(String.valueOf(student.getId()));
                editName.setText(student.getName());
                editAge.setText(student.getAge()+ "");
                String str = "Male";
                if(str.equals(student.getSex())) {
                    radioButtonMale.setChecked(true);
                } else {
                    radioButtonFemale.setChecked(true);
                }
                editPhone.setText(student.getPhoneNumber() + "");
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);

                pos = position;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setId(Integer.parseInt(String.valueOf(editId.getText())));
                student.setName(editName.getText() + "");
                student.setAge(Integer.parseInt(editAge.getText() + ""));
                if(radioButtonMale.isChecked()) {
                    student.setSex("Male");
                } else {
                    student.setSex("Famle");
                }
                student.setPhoneNumber(Integer.parseInt(editPhone.getText() + ""));
                int result = databaseSQLite.updateStudent(student);
                showToast("Updated !!!");
                if(result > 0) {
                    updateListStudent();
                    setTextNull();
                }
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }

        });

    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete A Student");
        builder.setMessage("You May Want To Delete ?");
        builder.setIcon(R.drawable.question);
        builder.setCancelable(false);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Student student = studentList.get(pos);
                int result = databaseSQLite.deleteStudent(student.getId());
                showToast("Deleted !!!");
                if(result > 0) {
                    updateListStudent();
                    setTextNull();
                } else {
                    showToast("Delete fail");
                }
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // check input data
    private Student createStudent() {
        String name = "";
        int age = 0;
        String sex = "";
        int phone = 0;
        Student student = null;

        if(editName.getText().toString().length() == 0) {
            showToast("Please enter the NAME");
        } else {
            name = editName.getText().toString();
        }

        if(editAge.getText().toString().length() == 0) {
            showToast("Please enter the AGE");
        } else {
            age = Integer.parseInt(editAge.getText() + "");
        }

        if(radioButtonMale.isChecked()) {
            sex = (String) radioButtonMale.getText();
        }

        if(radioButtonFemale.isChecked()) {
            sex = (String) radioButtonFemale.getText();
        }

        if(editPhone.getText().toString().length() == 0) {
            showToast("Please enter the PHONE");
        } else {
            phone = Integer.parseInt(editPhone.getText() + "");
        }
        if(name != "" && age < 0 && sex != "") {
            student = new Student(name, age, sex, phone);
        } else {
            showToast("Please complete all information");
        }

        return student;
    }

    public void initWidget() {
        editId      = (EditText) findViewById(R.id.edt_id);
        editName    = (EditText) findViewById(R.id.edt_name);
        editAge    = (EditText) findViewById(R.id.edt_age);
        editPhone  = (EditText) findViewById(R.id.edt_number);

        btnSave     = (Button) findViewById(R.id.btn_save);
        btnUpdate   = (Button) findViewById(R.id.btn_update);
        btnDelete   = (Button) findViewById(R.id.btn_delete);
        radioGroupCharacter   = (RadioGroup) findViewById(R.id.radioGroup_character);
        radioButtonMale        = (RadioButton) findViewById(R.id.radioButton_male);
        radioButtonFemale        = (RadioButton) findViewById(R.id.radioButton_female);

        lvStudent = (ListView) findViewById(R.id.lvStudent);
    }

    public void setAdapter() {
        if(customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.show_all_student, studentList);
            lvStudent.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount() -1);
        }
    }

    public void updateListStudent() {
        studentList.clear();
        studentList.addAll(databaseSQLite.getAllStudent());
        if(customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }
    public void setTextNull() {
        editName.setText("");
        editAge.setText("");
        editPhone.setText("");
    }

    public void showToast(String msg) {
        Toast.makeText(Student_Management.this, msg, Toast.LENGTH_LONG).show();
    }
}
