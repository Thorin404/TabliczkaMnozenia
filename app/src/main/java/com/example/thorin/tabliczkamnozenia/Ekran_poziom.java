package com.example.thorin.tabliczkamnozenia;

import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ekran_poziom extends AppCompatActivity {

    Button przy1, przy2, przy3, przy4, przy5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_poziom);
        przy1 = (Button) findViewById(R.id.button1);
        przy2 = (Button) findViewById(R.id.button2);
        przy3 = (Button) findViewById(R.id.button3);
        przy4 = (Button) findViewById(R.id.button4);
        przy5 = (Button) findViewById(R.id.button5);
        przy1.setEnabled(true);
        przy2.setEnabled(true);
        przy3.setEnabled(true);
        przy4.setEnabled(true);
        przy5.setEnabled(true);
        if (single_poziom.getpoziom() == 1) przy1.setEnabled(false);
        else if (single_poziom.getpoziom() == 2) przy2.setEnabled(false);
        else if (single_poziom.getpoziom() == 3) przy3.setEnabled(false);
        else if (single_poziom.getpoziom() == 4) przy4.setEnabled(false);
        else if (single_poziom.getpoziom() == 5) przy5.setEnabled(false);
    }
    //bardzo latwy
    public void sendDiff1(View view) {
        przenies(1);
    }
    //latwy
    public void sendDiff2(View view) {
        przenies(2);
    }
    //sredni
    public void sendDiff3(View view) {
        przenies(3);
    }
    //trudny
    public void sendDiff4(View view) {
        przenies(4);
    }
    //bardzo trudny
    public void sendDiff5(View view) {
        przenies(5);
    }
    //costamkolwiek
    private void przenies(int a){
        single_poziom.setpoziom(a);
        finish();
    }
}