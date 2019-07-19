package jp.com.studentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import static android.view.View.INVISIBLE;

public class SearchStudent extends AppCompatActivity implements View.OnClickListener{
    private Button           btnSearch;
    private EditText         editTextSearch;
    private ListView        lvStudent;
    private LinearLayout     txtDisplay;
    private DatabaseSQLite databaseSQLite;
    private CustomAdapter customAdapter;
    private List<Student> studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);

        // initialize values
        init();
        databaseSQLite = new DatabaseSQLite(this);

        btnSearch.setOnClickListener(this);

    }

    // click button search
    @Override
    public void onClick(View view) {
        // キーボードを非表示する。
        hidenKeyBoard();
        String str = String.valueOf(editTextSearch.getText());
        // 入力したかどか判断する。
        if(str.length() == 0) {
            showToast("Please enter the name ??");
        } else {
            studentList = databaseSQLite.getStudent(str);
            if(studentList.isEmpty()) {
                txtDisplay.setVisibility(INVISIBLE);
                lvStudent.setVisibility(INVISIBLE);
                showToast("No " + str + " found");
            } else {
                txtDisplay.setVisibility(View.VISIBLE);
                lvStudent.setVisibility(View.VISIBLE);
                // データを読み込む
                setAdapter();
            }
        }


    }

    public void init() {
        btnSearch               = (Button) findViewById(R.id.btnSearch);
        editTextSearch         = (EditText) findViewById(R.id.editTextSearch);
        txtDisplay              = (LinearLayout) findViewById(R.id.txtDisplay);
        lvStudent              = (ListView)  findViewById(R.id.lvStudent);
    }

    public void showToast(String msg) {
        Toast.makeText(SearchStudent.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void hidenKeyBoard() {
        InputMethodManager inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethod.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    public void setAdapter() {
        customAdapter = new CustomAdapter(this, R.layout.show_all_student, studentList);
        customAdapter.notifyDataSetChanged();
        lvStudent.setAdapter(customAdapter);
    }
}
