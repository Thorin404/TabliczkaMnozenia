package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFont(R.id.button0);
        changeFont(R.id.button1);
        changeFont(R.id.button2);
        changeFont(R.id.button3);
        changeFont(R.id.button4);
    }

    private void changeFont(int id){
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        TextView button=(TextView)findViewById(id);
        button.setTypeface(typeFace);
    }

    /** akcje po wciśnięciu przycisków */
    public void sendPlay(View view) {
        Intent intent = new Intent(this, ScreenGame.class);
        startActivity(intent);
    }
    public void sendDiff(View view) {
        Intent intent = new Intent(this, ScreenLevel.class);
        startActivity(intent);
    }
    public void sendAchiev(View view) {
        Intent intent = new Intent(this, ScreenAchievement.class);
        startActivity(intent);
    }
    public void sendAbout(View view) {
        Intent intent = new Intent(this, ScreenAbout.class);
        startActivity(intent);
    }
    public void sendExit(View view) {
        System.exit(0);
    }

}