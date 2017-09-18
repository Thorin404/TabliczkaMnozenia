package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class ScreenAchievement extends Activity {
    public static String PREFS_ACHIEV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        PREFS_ACHIEV = ClassGra.whichLevel(settings.getInt("poziom", 0));
        setContentView(R.layout.activity_screen_achievement);
        achievementOn();
        changeFont(R.id.checkedTextView0);
        changeFont(R.id.checkedTextView1);
        changeFont(R.id.checkedTextView2);
        changeFont(R.id.checkedTextView3);
        changeFont(R.id.checkedTextView4);
        changeFont(R.id.checkedTextView5);
        changeFont(R.id.checkedTextView6);
        changeFont(R.id.checkedTextView7);
        changeFont(R.id.button);
        changeFont(R.id.achiev);
        View view = findViewById(R.id.checkedTextView7);
        view.setVisibility(View.INVISIBLE);
        if (settings.getInt("poziom", 0) == 4) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void changeFont(int id){
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        TextView button1=(TextView)findViewById(id);
        button1.setTypeface(typeFace);
    }

    private void achievementOn(){
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

    private void achievementOff(){
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

    public void sendDelete(View view) {
        clear();
        achievementOff();
        SystemClock.sleep(500);
        finish();
    }

    private void clear(){
        SharedPreferences settings = getSharedPreferences(PREFS_ACHIEV, 0);
        SharedPreferences.Editor editor = settings.edit();
        for (int i = 0; i < 8; i++) {
            editor.putInt("Achiev"+i, 0);
        }
        editor.putInt("Achiev_days", 1);
        editor.putInt("Achiev_days2", 1);
        editor.putInt("Achiev_days4", 1);
        editor.putInt("Achiev_wons", 0);
        editor.putInt("Achiev_YearSaved", 1970);
        editor.putInt("Achiev_DaySaved", 1);
        editor.apply();
    }
}
