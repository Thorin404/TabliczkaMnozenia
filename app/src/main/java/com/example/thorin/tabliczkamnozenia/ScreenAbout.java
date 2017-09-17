package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class ScreenAbout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_about);
        TextView myTextView=(TextView)findViewById(R.id.textView);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        myTextView.setTypeface(typeFace);
    }
}
