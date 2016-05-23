package me.jamesbernard.gpacalculator;

import java.util.ArrayList;

/**
 * Created by james on 5/20/2016.
 */
public class Course {
    private ArrayList<AssignmentType> assignments = new ArrayList<AssignmentType>();
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    private int creditHours = 1;
    private Grade grade;
    private String professor;
    private int meetTimes;

    public Course(String title, int creditHours){
        this.grade = new Grade();
        this.grade.setTitle(title + " Course");
        this.creditHours = creditHours;
    }

    public double calculateFinalGrade(){

        for(Grade grade : this.grades){

        }

        return 0.0;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getMeetTimes() {
        return meetTimes;
    }

    public void setMeetTimes(int meetTimes) {
        this.meetTimes = meetTimes;
    }


}
