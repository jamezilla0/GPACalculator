package me.jamesbernard.gpacalculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.ContentValues;

public class CourseForm extends AppCompatActivity {
    private DBManager dbManager;
    private ContentValues cValues = new ContentValues();
    private Cursor cursor;
    private TextView courseName;
    private TextView courseCreditHours;
    private TextView courseProfessor;
    private TextView courseMeetTimes;
    private Bundle bundle = new Bundle();
    private Intent intent;
    private JBToolKit jbToolKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_form);
        Button addCourse = (Button) findViewById(R.id.addCourse);
        dbManager = new DBManager(this, null, null, 1);
        jbToolKit = new JBToolKit(getResources());

        courseName  = (TextView) findViewById(R.id.courseName);
        courseCreditHours  = (TextView) findViewById(R.id.courseCreditHours);
        courseProfessor  = (TextView) findViewById(R.id.courseProfessor);
        courseMeetTimes  = (TextView) findViewById(R.id.courseMeetTimes);
    }

    public void saveCourse(View view){
        SQLiteDatabase db = dbManager.getDB();
        CharSequence course_name = courseName.getText();
        CharSequence course_professor = courseProfessor.getText();
        CharSequence course_credit_hours = courseCreditHours.getText();
        CharSequence course_meet_times = courseMeetTimes.getText();

        if(!jbToolKit.filledOut("Course Name", courseName)) return;
        if(!jbToolKit.filledOut("Course Professor", courseProfessor)) return;
        if(!jbToolKit.filledOut("Course Meet Times", courseMeetTimes)) return;
        if(!jbToolKit.filledOut("Course Credit Hours ", courseCreditHours)) return;

        bundle.putString("courseName", course_name.toString());
        bundle.putString("courseProfessor", course_professor.toString());
        bundle.putInt("courseMeetTimes", Integer.valueOf(course_meet_times.toString()));
        bundle.putInt("courseCreditHours", Integer.valueOf(course_credit_hours.toString()));

        cursor = db.rawQuery("SELECT * FROM courses WHERE courses.name = ?", new String[]{bundle.getString("courseName")});
//        cursor.moveToFirst();

        if(cursor.getCount() <= 0){
            cValues.put("name", bundle.getString("courseName"));
            cValues.put("professor", bundle.getString("courseProfessor"));
            cValues.put("credit_hours", bundle.getInt("courseCreditHours"));
            cValues.put("meet_times", bundle.getInt("courseMeetTimes"));

            db.insert("courses", null, cValues);

            Log.i("DB:Insert", "Course: " + bundle.getString("courseName") + " was inserted into the db");
        }

        dbManager.logDatabase("SELECT * FROM courses WHERE 1", null);

        db.close();

        intent = new Intent(CourseForm.this, MainActivity.class);
        startActivity(intent, bundle);
    }
}
