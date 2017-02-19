package com.example.thorin.tabliczkamnozenia;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ekran_about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_about);
        TextView myTextView=(TextView)findViewById(R.id.textView);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        myTextView.setTypeface(typeFace);
    }
}
