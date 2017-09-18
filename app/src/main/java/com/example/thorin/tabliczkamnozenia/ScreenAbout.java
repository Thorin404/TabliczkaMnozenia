package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScreenAbout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_about);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTypeface(typeFace);
        Button buttonView = (Button) findViewById(R.id.button_learn);
        buttonView.setTypeface(typeFace);
    }

    public void sendLearn(View view) {
        Intent intent = new Intent(this, ScreenLearn.class);
        startActivity(intent);
    }
}
