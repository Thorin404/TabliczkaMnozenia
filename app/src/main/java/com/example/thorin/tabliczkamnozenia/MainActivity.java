package com.example.thorin.tabliczkamnozenia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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