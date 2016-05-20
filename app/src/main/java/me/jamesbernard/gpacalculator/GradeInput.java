package me.jamesbernard.gpacalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GradeInput extends AppCompatActivity {
    JBToolKit JBToolKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JBToolKit = new JBToolKit(getResources());

        setContentView(R.layout.activity_grade_input);

        final ArrayList<Grade> gradeList = new ArrayList<Grade>();

        final ArrayAdapter<Grade> bgcAdapter = new GradeAdapter(this, gradeList);
        final ListView GradeList = (ListView) findViewById(R.id.GradeList);
        GradeList.setAdapter(bgcAdapter);

        GradeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grade grade = (Grade) parent.getItemAtPosition(position);

                JBToolKit.showPopup(view, R.layout.grade_input_menu);
                Toast.makeText(GradeInput.this, String.valueOf(grade.getValue()), Toast.LENGTH_SHORT).show();
            }
        });

        Button addGradeButton = (Button) findViewById(R.id.AddGrade);

        assert addGradeButton != null;
        addGradeButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Random ran = new Random();
                Grade grade = new Grade(ran.nextInt(100 - 1) + 1);
                String test = "Course";

                switch (ran.nextInt(4 - 1) + 1){
                    case 3:
                        test = "Homework";
                        break;
                    case 2:
                        test = "exam";
                        break;
                }

                grade.setTitle(test + " Grade");
                grade.setType(test);

                gradeList.add(grade);
                bgcAdapter.notifyDataSetChanged();
            }
        });

        Button calculateGpaButton = (Button) findViewById(R.id.Calculate);

        calculateGpaButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Double gpaVal = Calculator.calculate(gradeList);
                Double gpa = Grade.decrypt.parseToGpa(gpaVal);
                Bundle bundle = new Bundle();
                bundle.putDouble("GPA", gpa);
                bundle.putString("GPALetter", Grade.decrypt.decryptNumberGrade(gpaVal));

                Intent intent = new Intent(GradeInput.this, MainActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}
