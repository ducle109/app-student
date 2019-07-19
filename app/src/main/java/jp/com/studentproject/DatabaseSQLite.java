package jp.com.studentproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Student_Manager";
    private static final String TABLE_NAME = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String SEX = "sex";
    private static final String PHONENUMBER = "phoneNumber";

    private Context context;
    // create database
    public DatabaseSQLite(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    public String createStudent() {
        String sqlQuery = "CREATE TABLE "+ TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT," +
                AGE + " INTEGER," +
                SEX + " TEXT," +
                PHONENUMBER + " INTEGER)";
        return sqlQuery;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createStudent());

        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
        //db.execSQL(sqlQuery);
        Log.d("checkMsg", "onCreate: ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    /*
   Add new a student
     */
    public void addStudent(Student student) {
        // write data in database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(AGE, student.getAge());
        values.put(SEX, student.getSex());
        values.put(PHONENUMBER, student.getPhoneNumber());
        // insert data in database
        db.insert(TABLE_NAME, null, values);
        // close database
        db.close();
    }

    /*
    Select a student by ID
     */
    public Student getStudentById(int id) {
        // take data from database
        SQLiteDatabase db = this.getReadableDatabase();

        // step 1
        Cursor cursor = db.query(TABLE_NAME, new String[] {
                        ID, NAME, AGE, SEX, PHONENUMBER }, ID + "=?",
                new String[] {String.valueOf(id) },
                null, null, null, null);
        // step 2
       /*
       String selectQuery = "SELECT  * FROM " + TABLE_NAME + "WHERE "+ ID + " = ?";
       Cursor cursor = db.rawQuery(selectQuery, null);
       */
        if(cursor != null) {
            // doc tung tu 1 theo hang
            cursor.moveToFirst();
        }

        Student student = new Student(cursor.getString(1), cursor.getInt(2),
                cursor.getString(3),cursor.getInt(4));
        cursor.close();
        db.close();
        return student;
    }


    /*
    Update name of student
     */
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, student.getName());
        values.put(AGE, student.getAge());
        values.put(SEX, student.getSex());
        values.put(PHONENUMBER, student.getPhoneNumber());

        return db.update(TABLE_NAME, values, ID + " = ?",
                new String[] {String.valueOf(student.getId())});
    }

    /*
   Delete a student by ID
    */

    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int data = db.delete(TABLE_NAME,ID+"=?",new String[] {String.valueOf(id)});

        return data;
    }

    /*
   Search a student by ID
    */
    public List<Student> getStudent(String name) {
        List<Student> list = new ArrayList<Student>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME, AGE, SEX, PHONENUMBER}, NAME + " = ?",
                new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            Student student = new Student(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getString(3),cursor.getInt(4));
            list.add(student);
        }
        cursor.close();
        db.close();
        return list;
    }

    /*
     Getting All Student (in tat ca hs)
      */
    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<Student>();
        // SELECT ALL QUERY
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // If there is still data, still take data
        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                // offset 0, 1, 2, 3 == (cot 1, cot 2, cot 3, va no bat dau tu vi tri 0)
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setAge(cursor.getInt(2));
                student.setSex(cursor.getString(3));
                student.setPhoneNumber(cursor.getInt(4));
                list.add(student);

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /*
  Get Count Student in Table Student (lay ra tong so dong trong TABLE)
   */
    public int getStudentsCount() {
        String countQuety = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuety, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
