package me.jamesbernard.gpacalculator;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static java.lang.Math.*;

/**
 * Created by james on 5/18/2016.
 */
public class TextLabel extends TextView {
    private RelativeLayout.LayoutParams details;
    private JBToolKit JBToolKit = new JBToolKit(getResources());
    private int leftMargin = 0;
    private int topMargin = 0;
    private int rightMargin = 0;
    private int bottomMargin = 0;
    public boolean added = false;

    public TextLabel(Context context, int id, String text){
        super(context);
        int ot = JBToolKit.getOrientation();
        int txtSize = 150;

        this.details = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        this.setText(text);
        this.setTextColor(Color.parseColor("#ecf0f1"));
        this.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        if(ot == 1)  this.setTextSize(txtSize);
        else if(ot == 2) {
            this.setTextSize(txtSize / 1.5);
            this.setTopMargin(10);
            this.saveMargins();
        }
    }

    public RelativeLayout.LayoutParams getDetails(){
        return this.details;
    }

    public void placeTopCenter(){
        this.placeTop();
        this.placeCenterX();
        this.placeCenterY();
    }

    public void placeCenterXY(){
        this.placeCenterX();
        this.placeCenterY();
    }

    public void placeTop(){
        this.details.addRule(RelativeLayout.ALIGN_PARENT_TOP);
    }

    public void placeCenterX(){
        this.details.addRule(RelativeLayout.CENTER_HORIZONTAL);
    }

    public void placeCenterY(){
        this.details.addRule(RelativeLayout.CENTER_VERTICAL);
    }

    // Call this when you want to place this object somewhere agaisnt another object
    public void place(String where, int id){
        switch (where.toLowerCase()){
            case "above":
                this.details.addRule(RelativeLayout.ABOVE, id);
                break;
            case "bellow":
                this.details.addRule(RelativeLayout.BELOW, id);
                break;
            case "left":
                this.details.addRule(RelativeLayout.LEFT_OF, id);
                break;
            case "right":
                this.details.addRule(RelativeLayout.RIGHT_OF, id);
                break;
        }
    }

    public void saveMargins(){
        this.details.setMargins(this.getLeftMargin(), this.getTopMargin(), this.getRightMargin(), this.getBottomMargin());
    }

    public void setTextSize(Double amount){
        this.setTextSize(round(amount));
    }
    // When ever we set a text size
    public void setTextSize(int amount){
        super.setTextSize(amount);
        // keep the text  container's width and height relative to the amount
        this.setWidth((int) abs(amount * 2.85));
        this.setHeight((int) abs(amount * 1.42));
    }

    public void setWidth(int amount){
        super.setWidth(JBToolKit.convertDipToPx(amount));
    }

    public void setHeight(int amount){
        super.setHeight(JBToolKit.convertDipToPx(amount));
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = JBToolKit.convertDipToPx(leftMargin);
    }

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = JBToolKit.convertDipToPx(topMargin);
    }

    public int getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = JBToolKit.convertDipToPx(rightMargin);
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = JBToolKit.convertDipToPx(bottomMargin);
    }

    public void finalize(){
        MainActivity.textLabels.add(this);
    }
}
