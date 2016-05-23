package me.jamesbernard.gpacalculator;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.zip.Inflater;


/**
 * Created by james on 5/18/2016.
 * A tool kit that I created that uses many resources to convert and transform specific properties.
 */
public class JBToolKit {
    private Resources resources;
    private Display display;
    private DisplayMetrics displayMetrics;
    private LayoutInflater layoutInflator;

    public JBToolKit(Resources res){
        this.resources = res;
    }
    public int convertDipToPx(int amount){

        // First param = what are we trying to convert, second param = howmany? third param = information about the screen.
        int convertedPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, amount, this.resources.getDisplayMetrics());

        return convertedPx;
    }
    public int getOrientation(){
        // 1 = portrait; 2 = landscape;
        return this.resources.getConfiguration().orientation;
    }

    public void setDefaultDisplay(Display defaultDisplay){
        this.display = defaultDisplay;
    }

    public void setDisplayMetrics(DisplayMetrics displayMetrics){
        this.displayMetrics = displayMetrics;
        this.display.getMetrics(this.displayMetrics);
    }

    public int getDeviceWidth(){
        return this.displayMetrics.widthPixels;
    }

    public int getDeviceHeight(){
        return this.displayMetrics.heightPixels;
    }

    public void setLayoutInflater(LayoutInflater inflater){
        this.layoutInflator = inflater;
    }

    public void showPopup(View anchorView, int layout){
        View popupView = this.layoutInflator.inflate(layout, null);
        PopupWindow popupWindow = new PopupWindow(popupView,  RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        anchorView.getLocationOnScreen(location);

        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0], location[1] + anchorView.getHeight());
    }

    public boolean filledOut(String name, TextView input){
        if(input.isDirty() || input.getText().toString().trim().equals("")){
            input.setHint(name + " is required");
            return false;
        }

        return true;
    }
}
