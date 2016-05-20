package me.jamesbernard.gpacalculator;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by james on 5/9/2016.
 */
public class MenuButton extends Button {
    private static final String TAG = "jamesMessage";
    private static int xRoomLeft = 0;
    private RelativeLayout.LayoutParams details;
    private JBToolKit JBToolKit = new JBToolKit(getResources());
    private int deviceWidth;
    private int deviceHeight;
    public boolean added = false;

//    public MenuButton(){
//
//    }

    public MenuButton(Context context, int id, String text){
        super(context);

        int ot = JBToolKit.getOrientation();
        int dPad = 30;
        int dWidth = 135;
        int dTxtSize = 12;

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        JBToolKit.setDefaultDisplay(wm.getDefaultDisplay());
        JBToolKit.setDisplayMetrics(new DisplayMetrics());


       this.details = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        this.details.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);


        this.setId(id);
        this.setText(text);
//        this.setTextColor(Color.parseColor("#fff"));
        this.setBackgroundColor(Color.parseColor("#2980b9"));

        if(ot == 2){
            dPad *= 1.5;
            dWidth *= 1.5;
            dTxtSize *= 1.5;
        }

        this.setPadding(dPad, dPad, dPad, dPad);
        this.setWidth(dWidth);
        this.setTextSize(dTxtSize);
        this.setTextColor(Color.parseColor("#ecf0f1"));


    }

//    public void hrPosition(){
//        this.hrPostion("Center");
//    }

    public void hrPosition(String direction){
        switch (direction){
            case "left":
                this.details.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case "center":
                this.details.addRule(RelativeLayout.CENTER_HORIZONTAL);
                break;
            case "right":
                this.details.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
        }

    }

    public RelativeLayout.LayoutParams getDetails(){
        return this.details;
    }

    public void setWidth(int amount){
        super.setWidth(JBToolKit.convertDipToPx(amount));
    }

    public void setHeight(int amount){
        super.setHeight(JBToolKit.convertDipToPx(amount));
    }

    public void finalize(){
        MainActivity.menuButtons.add(this);
    }
}
