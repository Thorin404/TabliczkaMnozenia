package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

public class ScreenLevel extends Activity {
    Button[] buttonsArray = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        int poziom = settings.getInt("poziom", 0);
        setContentView(R.layout.activity_ekran_poziom);
        buttonsArray[0] = (Button) findViewById(R.id.button0);
        buttonsArray[1] = (Button) findViewById(R.id.button1);
        buttonsArray[2] = (Button) findViewById(R.id.button2);
        buttonsArray[3] = (Button) findViewById(R.id.button3);
        buttonsArray[4] = (Button) findViewById(R.id.button4);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        for (int i = 0; i < 5; i++) {
            buttonsArray[i].setEnabled(true);
            buttonsArray[i].setTypeface(typeFace);
        }
        if (poziom == 0) buttonsArray[0].setEnabled(false);
        else if (poziom == 1) buttonsArray[1].setEnabled(false);
        else if (poziom == 2) buttonsArray[2].setEnabled(false);
        else if (poziom == 3) buttonsArray[3].setEnabled(false);
        else if (poziom == 4) buttonsArray[4].setEnabled(false);
    }
    //bardzo latwy
    public void sendDiff1(View view) {
        sendLevel(0);
    }
    //latwy
    public void sendDiff2(View view) {
        sendLevel(1);
    }
    //sredni
    public void sendDiff3(View view) {
        sendLevel(2);
    }
    //trudny
    public void sendDiff4(View view) {
        sendLevel(3);
    }
    //bardzo trudny
    public void sendDiff5(View view) {
        sendLevel(4);
    }
    //costamkolwiek
    private void sendLevel(int a){
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("poziom",a);
        editor.apply();
        finish();
    }
}