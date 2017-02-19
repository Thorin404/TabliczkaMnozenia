package com.example.thorin.tabliczkamnozenia;

import android.graphics.Typeface;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ekran_poziom extends AppCompatActivity {

    Button przy0, przy1, przy2, przy3, przy4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_poziom);
        przy0 = (Button) findViewById(R.id.button0);
        przy1 = (Button) findViewById(R.id.button1);
        przy2 = (Button) findViewById(R.id.button2);
        przy3 = (Button) findViewById(R.id.button3);
        przy4 = (Button) findViewById(R.id.button4);
        przy0.setEnabled(true);
        przy1.setEnabled(true);
        przy2.setEnabled(true);
        przy3.setEnabled(true);
        przy4.setEnabled(true);
        if (single_poziom.getpoziom() == 0) przy0.setEnabled(false);
        else if (single_poziom.getpoziom() == 1) przy1.setEnabled(false);
        else if (single_poziom.getpoziom() == 2) przy2.setEnabled(false);
        else if (single_poziom.getpoziom() == 3) przy3.setEnabled(false);
        else if (single_poziom.getpoziom() == 4) przy4.setEnabled(false);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        przy0.setTypeface(typeFace);
        przy1.setTypeface(typeFace);
        przy2.setTypeface(typeFace);
        przy3.setTypeface(typeFace);
        przy4.setTypeface(typeFace);

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
        single_poziom.setpoziom(a);
        finish();
    }
}