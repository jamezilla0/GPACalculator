package me.jamesbernard.gpacalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.graphics.Color;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Holds a list of all menu buttons for this activity;
    public static ArrayList<MenuButton> menuButtons = new ArrayList<MenuButton>();
    // Holds a list of all textlabels for this activity
    public static ArrayList<TextLabel> textLabels = new ArrayList<TextLabel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout
        RelativeLayout mainLayout = new RelativeLayout(this);


        // gpa input button
        MenuButton giButton = new MenuButton(this, 1, "Grade Input");
        // gpa input click listener.
        giButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                // Initialize intent to change activity from this to GradeInput activity
                Intent intent = new Intent(MainActivity.this, GradeInput.class);
                // Start activity from intent
                startActivity(intent);
            }
        });
        // Set the horizontal position of the button
        giButton.hrPosition("center");
        // Garbage collect
        giButton.finalize();

        // Login button
        MenuButton lnButton = new MenuButton(this, 2, "Login");
        lnButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Initialize intent to change activity from this to LoginPanel activity
                Intent intent = new Intent(MainActivity.this, LoginPanel.class);
                // Start activity from intent
                startActivity(intent);
            }
        });
        // Set the horizontal position of the button
        lnButton.hrPosition("left");
        // Garbage collect
        lnButton.finalize();

        // Register button
        MenuButton rgButton = new MenuButton(this, 3, "Register");

        // Registration click event
        rgButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Intialize intent to change activity from this to RegistrationPanel activity.
                Intent intent = new Intent(MainActivity.this, RegistrationPanel.class);
                // Start activity from intent
                startActivity(intent);
            }
        });
        // Horizontal position of button
        rgButton.hrPosition("right");
        // Garbage collection
        rgButton.finalize();
        // Main title of the activity
        TextLabel mainTitle = new TextLabel(this, 4, "GPA");
        // Position of the title
        mainTitle.placeTopCenter();
        // Initial gpa value
        String gpaVal = "0.0";
        // initial letter grade of gpa, corresponds to gpa value.
        String gpaLetter = "F";
        // Get previous intent params into a bundle
        Bundle bundle = getIntent().getExtras();
        // If bundle is not null
        if(bundle != null){
            // get the gpa from bundle params.
            gpaVal = String.valueOf(bundle.get("GPA"));
            // get gpa letter value from bundle params
            gpaLetter = String.valueOf(bundle.get("GPALetter"));
        }
        // Create a new text label to display the GPA value
        TextLabel gpaOutPut = new TextLabel(this, 5, gpaVal);
        // Position the textlabel
        gpaOutPut.placeCenterX();
        // Give the label some breathing room
        gpaOutPut.setTopMargin(150);
        // Finalizes any changed margins
        gpaOutPut.saveMargins();
        //Garbage collection
        gpaOutPut.finalize();
        // Create a text label for the gpa letter ie: A, B+, F
        TextLabel gpaLetterOutPut = new TextLabel(this, 6, gpaLetter);
        // Position the label
        gpaLetterOutPut.placeCenterX();
        // Give the label some breathing room
        gpaLetterOutPut.setTopMargin(300);
        // finalize the margins
        gpaLetterOutPut.saveMargins();
        // Garbage collection
        gpaLetterOutPut.finalize();

        // Add button Widgets to main layout
        mainLayout.addView(giButton, giButton.getDetails());
        mainLayout.addView(lnButton, lnButton.getDetails());
        mainLayout.addView(rgButton, rgButton.getDetails());

//        for(MenuButton button : MainActivity.menuButtons){
//           if(!button.added){
//               mainLayout.addView(button, button.getDetails());
//               button.added = true;
//           }
//
//        }

        // Add text label widgets to main layout.
        mainLayout.addView(mainTitle, mainTitle.getDetails());
        mainLayout.addView(gpaOutPut, gpaOutPut.getDetails());
        mainLayout.addView(gpaLetterOutPut, gpaLetterOutPut.getDetails());

//        for(TextLabel textLabel : MainActivity.textLabels){
//           if(!textLabel.added){
//               mainLayout.addView(textLabel, textLabel.getDetails());
//               textLabel.added = true;
//           }
//
//        }

        //Set Content VIew
        setContentView(mainLayout);
        // Change this layouts background based off of the current gpa
        mainLayout.setBackgroundColor(Settings.getGpaBgColor(Double.parseDouble(gpaOutPut.getText().toString())));
    }
}
