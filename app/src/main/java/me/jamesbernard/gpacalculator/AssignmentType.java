package me.jamesbernard.gpacalculator;

import java.util.ArrayList;

/**
 * Created by james on 5/20/2016.
 */
public class AssignmentType {
    public String name;
    public double percentage;
    public ArrayList<Grade> grades = new ArrayList<Grade>();
    public AssignmentType(String name, double percentage){
        this.name = name;
        this.percentage = percentage;
    }


}
