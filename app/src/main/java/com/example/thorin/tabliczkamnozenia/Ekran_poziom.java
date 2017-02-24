package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

public class Ekran_poziom extends Activity {

    Button przy0, przy1, przy2, przy3, przy4;
    Button[] przytab = {przy0, przy1, przy2, przy3, przy4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        int poziom = settings.getInt("poziom", 0);
        setContentView(R.layout.activity_ekran_poziom);
        przytab[0] = (Button) findViewById(R.id.button0);
        przytab[1] = (Button) findViewById(R.id.button1);
        przytab[2] = (Button) findViewById(R.id.button2);
        przytab[3] = (Button) findViewById(R.id.button3);
        przytab[4] = (Button) findViewById(R.id.button4);
        for (int i = 0; i < 5; i++) {
            przytab[i].setEnabled(true);
        }
        if (poziom == 0) przytab[0].setEnabled(false);
        else if (poziom == 1) przytab[1].setEnabled(false);
        else if (poziom == 2) przytab[2].setEnabled(false);
        else if (poziom == 3) przytab[3].setEnabled(false);
        else if (poziom == 4) przytab[4].setEnabled(false);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        for (int i = 0; i < 5; i++){
            przytab[i].setTypeface(typeFace);
        }
    }
    //bardzo latwy
    public void sendDiff1(View view) {
        przenies(0);
    }
    //latwy
    public void sendDiff2(View view) {
        przenies(1);
    }
    //sredni
    public void sendDiff3(View view) {
        przenies(2);
    }
    //trudny
    public void sendDiff4(View view) {
        przenies(3);
    }
    //bardzo trudny
    public void sendDiff5(View view) {
        przenies(4);
    }
    //costamkolwiek
    private void przenies(int a){
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("poziom",a);
        editor.apply();
        finish();
    }
}