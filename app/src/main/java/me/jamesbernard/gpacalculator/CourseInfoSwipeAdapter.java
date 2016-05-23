package me.jamesbernard.gpacalculator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by james on 5/21/2016.
 */
public class CourseInfoSwipeAdapter extends FragmentStatePagerAdapter {
    private DBManager dbManager;
    private int courses = 0;
    private Cursor cursor;
    private SQLiteDatabase db;

    public CourseInfoSwipeAdapter(FragmentManager fm, DBManager dbManager) {
        super(fm);
        this.dbManager = dbManager;

        db = this.dbManager.getDB();
//        cursor = db.rawQuery("SELECT courses.*,  course_assignments.*, graded_assignments.* " +
//                "             ON course_assignments.course_id = courses.id " +
//                "             AND graded_asignments.course_id = courses.id ", null);
        cursor = db.rawQuery("SELECT * FROM courses where 1", null);
        courses = cursor.getCount();
    }

    @Override
    public Fragment getItem(int position) {
        if(!cursor.isAfterLast()){
            Fragment fragment = new CourseInfoFragment();
            Bundle bundle = new Bundle();
            Log.i("Course Count", String.valueOf(courses));
            Log.i("Current Position", String.valueOf(position));
            cursor.moveToPosition(position);

            bundle.putInt("id", cursor.getInt(cursor.getColumnIndex("id")));
            bundle.putInt("year", cursor.getInt(cursor.getColumnIndex("year")));
            bundle.putString("semester", cursor.getString(cursor.getColumnIndex("semester")));
            bundle.putInt("user_id", cursor.getInt(cursor.getColumnIndex("user_id")));
            bundle.putString("name", cursor.getString(cursor.getColumnIndex("name")));
            bundle.putString("professor", cursor.getString(cursor.getColumnIndex("professor")));
            bundle.putInt("credit_hours", cursor.getInt(cursor.getColumnIndex("credit_hours")));
            bundle.putInt("meet_times", cursor.getInt(cursor.getColumnIndex("meet_times")));
            bundle.putInt("grade_number", cursor.getInt(cursor.getColumnIndex("grade_number")));
            bundle.putString("grade_letter", cursor.getString(cursor.getColumnIndex("grade_letter")));
            bundle.putFloat("grade_point_average", cursor.getFloat(cursor.getColumnIndex("grade_point_average")));

            fragment.setArguments(bundle);

            return fragment;
        }else return null;

    }

    @Override
    public int getCount() {
        return courses;
    }
}
