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
        zmienfont(R.id.button0);
        zmienfont(R.id.button1);
        zmienfont(R.id.button2);
        zmienfont(R.id.button3);
        zmienfont(R.id.button4);
    }

    private void zmienfont(int id){
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        TextView button=(TextView)findViewById(id);
        button.setTypeface(typeFace);
    }

    /** akcje po wciśnięciu przycisków */
    public void sendPlay(View view) {
        Intent intent = new Intent(this, Ekran_gry.class);
        startActivity(intent);
    }
    public void sendDiff(View view) {
        Intent intent = new Intent(this, Ekran_poziom.class);
        startActivity(intent);
    }
    public void sendAchiev(View view) {
        Intent intent = new Intent(this, Ekran_achiev.class);
        startActivity(intent);
    }
    public void sendAbout(View view) {
        Intent intent = new Intent(this, Ekran_about.class);
        startActivity(intent);
    }
    public void sendExit(View view) {
        System.exit(0);
    }

}