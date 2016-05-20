package me.jamesbernard.gpacalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by james on 5/18/2016.
 */
public class GradeAdapter extends ArrayAdapter<Grade> {
    public GradeAdapter(Context context, ArrayList<Grade> grades) {
        super(context, R.layout.grade_list_layout, grades);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater bgcInflater = LayoutInflater.from(getContext());
        View gradeListView = bgcInflater.inflate(R.layout.grade_list_layout, parent, false);
        Grade grade = getItem(position);

        TextView gradeTitle = (TextView) gradeListView.findViewById(R.id.GradeTitle);
        TextView gradeLetterLabel = (TextView) gradeListView.findViewById(R.id.GradeLetterLabel);
        TextView gradeNumberLabel = (TextView) gradeListView.findViewById(R.id.GradeNumberLabel);
        ImageView gradeIcon = (ImageView) gradeListView.findViewById(R.id.GradeIcon);

        gradeTitle.setText(grade.getTitle());
        gradeLetterLabel.setText(grade.getLetterGrade());
        gradeNumberLabel.setText(String.valueOf(grade.getValue()));

        int resId;
        switch (grade.getType().toLowerCase().trim()){
            case "exam":
                resId = R.mipmap.exam;
                break;
            case "homework":
                resId = R.mipmap.homework;
                break;
            default:
                resId = R.mipmap.course;
                break;
        }

        gradeIcon.setImageResource(resId);

        return gradeListView;
    }
}
