package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class Ekran_achiev extends Activity {
    public static String PREFS_ACHIEV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        int poziom = settings.getInt("poziom", 0);
        if(poziom == 0) PREFS_ACHIEV = "achiev_P0";
        else if(poziom == 1) PREFS_ACHIEV = "achiev_P1";
        else if(poziom == 2) PREFS_ACHIEV = "achiev_P2";
        else if(poziom == 3) PREFS_ACHIEV = "achiev_P3";
        else PREFS_ACHIEV = "achiev_P4";
        setContentView(R.layout.activity_ekran_achiev);
        setachiev();
        achievon();
        zmienfont(R.id.checkedTextView0);
        zmienfont(R.id.checkedTextView1);
        zmienfont(R.id.checkedTextView2);
        zmienfont(R.id.checkedTextView3);
        zmienfont(R.id.checkedTextView4);
        zmienfont(R.id.checkedTextView5);
        zmienfont(R.id.checkedTextView6);
        zmienfont(R.id.checkedTextView7);
        zmienfont(R.id.button);
        zmienfont(R.id.achiev);
        View view = findViewById(R.id.checkedTextView7);
        view.setVisibility(View.INVISIBLE);
        if (poziom == 4) view.setVisibility(View.VISIBLE);
    }

    private void zmienfont(int id){
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        TextView button1=(TextView)findViewById(id);
        button1.setTypeface(typeFace);
    }

    private void setachiev(){
        SharedPreferences settings = getSharedPreferences(PREFS_ACHIEV, 0);
        float result = settings.getFloat("result", 0f);
        int days = settings.getInt("Achiev_days", 0);
        int wons = settings.getInt("Achiev_wons", 0);
        int roznica = 1;
        settings = getSharedPreferences(PREFS_ACHIEV,0);
        SharedPreferences.Editor editor = settings.edit();
        if (result >= 0.5) editor.putInt("Achiev0", 1);
        if (result >= 0.75) editor.putInt("Achiev1", 1);
        if (result == 1) {
            editor.putInt("Achiev2", 1);
            if (roznica == 1) {
                wons = 0;
                days++;
                editor.putInt("Achiev_days", days);
            } else if (roznica == 0) {
                wons++;
                editor.putInt("Achiev_wons", wons);
            }
            if (days >= 3) editor.putInt("Achiev3", 1);
            if (days >= 7) editor.putInt("Achiev4", 1);
            editor.putInt("Achiev5", 1);
            editor.putInt("Achiev6", 1);
        }
        if (result == 0.44) editor.putInt("Achiev7", 1);

        // Commit the edits!
        editor.commit();
    }

    private void achievon(){
        SharedPreferences settings = getSharedPreferences(PREFS_ACHIEV, 0);
        if (settings.getInt("Achiev0", 0) == 1) findAndCheck(R.id.checkedTextView0);
        if (settings.getInt("Achiev1", 0) == 1) findAndCheck(R.id.checkedTextView1);
        if (settings.getInt("Achiev2", 0) == 1) findAndCheck(R.id.checkedTextView2);
        if (settings.getInt("Achiev3", 0) == 1) findAndCheck(R.id.checkedTextView3);
        if (settings.getInt("Achiev4", 0) == 1) findAndCheck(R.id.checkedTextView4);
        if (settings.getInt("Achiev5", 0) == 1) findAndCheck(R.id.checkedTextView5);
        if (settings.getInt("Achiev6", 0) == 1) findAndCheck(R.id.checkedTextView6);
        if (settings.getInt("Achiev7", 0) == 1) findAndCheck(R.id.checkedTextView7);
    }

    private void achievoff(){
        findAndUncheck(R.id.checkedTextView0);
        findAndUncheck(R.id.checkedTextView1);
        findAndUncheck(R.id.checkedTextView2);
        findAndUncheck(R.id.checkedTextView3);
        findAndUncheck(R.id.checkedTextView4);
        findAndUncheck(R.id.checkedTextView5);
        findAndUncheck(R.id.checkedTextView6);
        findAndUncheck(R.id.checkedTextView7);
    }

    private void findAndCheck(int id){
        final CheckedTextView ctv= (CheckedTextView)findViewById(id);
        ctv.setChecked(true);
    }

    private void findAndUncheck(int id){
        final CheckedTextView ctv= (CheckedTextView)findViewById(id);
        ctv.setChecked(false);
    }

    public void sendUsun(View view) {
        czyszczenie();
        achievoff();
    }

    private void czyszczenie(){
        SharedPreferences settings = getSharedPreferences(PREFS_ACHIEV, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("result", 0f);
        editor.putInt("Achiev0", 0);
        editor.putInt("Achiev1", 0);
        editor.putInt("Achiev2", 0);
        editor.putInt("Achiev3", 0);
        editor.putInt("Achiev4", 0);
        editor.putInt("Achiev5", 0);
        editor.putInt("Achiev6", 0);
        editor.putInt("Achiev7", 0);
        editor.commit();
    }
}
