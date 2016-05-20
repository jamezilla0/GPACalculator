package me.jamesbernard.gpacalculator;

import android.graphics.Color;

/**
 * Created by james on 5/18/2016.
 */
public class Settings {
    private static int backgroundColor = Color.parseColor("#e74c3c");

    private static void setBackgroundColor(String color){
        backgroundColor = Color.parseColor(color);
    }

    public static int getGpaBgColor(double gpa){
        // A
        if(gpa >= 4.00) setBackgroundColor("#2ecc71");
        // A-
        else if(gpa >= 3.67) setBackgroundColor("#27ae60");
        // B+
        else if(gpa >= 3.33) setBackgroundColor("#1abc9c");
        // B
        else if(gpa >= 3.00) setBackgroundColor("#f1c40f");
        // B-
        else if(gpa >= 2.67) setBackgroundColor("#f39c12");
        // C+
        else if(gpa >= 2.33) setBackgroundColor("#e67e22");
        // C
        else if(gpa >= 2.00) setBackgroundColor("#d35400");
        // D
        else if(gpa >= 1.00) setBackgroundColor("#e74c3c");
        // F
        else setBackgroundColor("#c0392b");

        return getBgColor();
    }

    public static int getBgColor(){
        return  backgroundColor;
    }
}
