package me.jamesbernard.gpacalculator;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Steven
 */
public class Calculator {
    // Returns an object of results, [0] = (value of grade)[double], [1] = (letter of grade)[string]
    public static Double getGpa(ArrayList<Grade> gradeList){
        int count = gradeList.size();
        double total = 0;
        
        for(Grade grade : gradeList){
            total += grade.getValue();
//            System.out.println("Grade Value: " + grade.getValue());
        }
        
//        System.out.println("Total score: " + total);
        
        double gpa = total / count;

        return gpa;
    }

    public static Double getGpaStrict(ArrayList<Grade> gradeList){
        // Weighted gpa algorithm

        return 0.0;
    }
}
