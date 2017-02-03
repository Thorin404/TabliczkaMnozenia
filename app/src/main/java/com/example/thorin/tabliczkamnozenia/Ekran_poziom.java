package com.example.thorin.tabliczkamnozenia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Ekran_poziom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_poziom);
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
    //o
    private void przenies(int a){
        Intent i = new Intent(Ekran_poziom.this, Ekran_gry.class);
        i.putExtra("POZ",a);
        startActivity(i);
    }
}
