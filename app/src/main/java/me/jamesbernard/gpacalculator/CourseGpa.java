package me.jamesbernard.gpacalculator;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class CourseGpa extends FragmentActivity{
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_gpa);

        viewPager = (ViewPager) findViewById(R.id.courseInfoContainer);

        CourseInfoSwipeAdapter swipeAdapter = new CourseInfoSwipeAdapter(getSupportFragmentManager(), new DBManager(this, null, null, 1));
        viewPager.setAdapter(swipeAdapter);
    }
}
