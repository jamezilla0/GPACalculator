package me.jamesbernard.gpacalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseInfoFragment extends Fragment {
    private TextView courseName;
    private TextView letterGrade;
    private TextView numberGrade;
    private TextView gradeAverage;

    public CourseInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_info_fragment_layout, container, false);
        courseName = (TextView) view.findViewById(R.id.courseName);
        letterGrade = (TextView) view.findViewById(R.id.letterGrade);
        numberGrade = (TextView) view.findViewById(R.id.numberGrade);
        gradeAverage = (TextView) view.findViewById(R.id.gradeAverage);

        Bundle bundle = getArguments();

        Log.i("Column Id", String.valueOf(bundle.getInt("id")));
        Log.i("Column Name", bundle.getString("name"));

        courseName.setText(bundle.getString("name"));
        letterGrade.setText(bundle.getString("grade_letter"));
        numberGrade.setText(String.valueOf(bundle.getInt("grade_number")));
        gradeAverage.setText(String.valueOf(bundle.getFloat("grade_point_average")));

        // Inflate the layout for this fragment
        return view;
    }

}
