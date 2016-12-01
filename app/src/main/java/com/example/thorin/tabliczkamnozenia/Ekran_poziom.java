package com.example.thorin.tabliczkamnozenia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Ekran_poziom extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.thorin.tabliczkamnozenia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_poziom);
    }
    public void sendDiff1(View view) {
        String message = "Bardzo łatwy";
        sendDiff(view, message);
    }
    public void sendDiff2(View view) {
        String message = "Łatwy";
        sendDiff(view, message);
    }
    public void sendDiff3(View view) {
        String message = "Średni";
        sendDiff(view, message);
    }
    public void sendDiff4(View view) {
        String message = "Trudny";
        sendDiff(view, message);
    }
    public void sendDiff5(View view) {
        String message = "Bardzo trudny";
        sendDiff(view, message);
    }

    private void sendDiff(View view, String message){
        Intent intent = new Intent(this, Ekran_gry.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        finish();
    }
}