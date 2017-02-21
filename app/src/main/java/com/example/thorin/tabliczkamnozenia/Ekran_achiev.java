package com.example.thorin.tabliczkamnozenia;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class Ekran_achiev extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    private void zmienfont(int id){
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");
        TextView button1=(TextView)findViewById(id);
        button1.setTypeface(typeFace);
    }

    void setachiev(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Ekran_achiev.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        if (single_achiev.getwin() >= 0.5) myEditor.putInt("Achiev0", 1);
        if (single_achiev.getwin() >= 0.75) myEditor.putInt("Achiev1", 1);
        if (single_achiev.getwin() == 1) myEditor.putInt("Achiev2", 1);
        if (single_achiev.getwin() == 1) {
            myEditor.putInt("Achiev3", 1);
        }
        if (single_achiev.getwin() == 1) {
            myEditor.putInt("Achiev4", 1);
        }
        if (single_achiev.getwin() == 1) {
            myEditor.putInt("Achiev5", 1);
        }
        if (single_achiev.getwin() == 1) {
            myEditor.putInt("Achiev6", 1);
        }
        if (single_achiev.getwin() == 0.44) myEditor.putInt("Achiev7", 1);

        myEditor.commit();
    }

    private void achievon(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Ekran_achiev.this);
        if (myPreferences.getInt("Achiev0", 0) == 1) findAndCheck(R.id.checkedTextView0);
        if (myPreferences.getInt("Achiev1", 0) == 1) findAndCheck(R.id.checkedTextView1);
        if (myPreferences.getInt("Achiev2", 0) == 1) findAndCheck(R.id.checkedTextView2);
        if (myPreferences.getInt("Achiev3", 0) == 1) findAndCheck(R.id.checkedTextView3);
        if (myPreferences.getInt("Achiev4", 0) == 1) findAndCheck(R.id.checkedTextView4);
        if (myPreferences.getInt("Achiev5", 0) == 1) findAndCheck(R.id.checkedTextView5);
        if (myPreferences.getInt("Achiev6", 0) == 1) findAndCheck(R.id.checkedTextView6);
        if (myPreferences.getInt("Achiev7", 0) == 1) findAndCheck(R.id.checkedTextView7);
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
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Ekran_achiev.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("Achiev0",0);
        myEditor.putInt("Achiev1",0);
        myEditor.putInt("Achiev2",0);
        myEditor.putInt("Achiev3",0);
        myEditor.putInt("Achiev4",0);
        myEditor.putInt("Achiev5",0);
        myEditor.putInt("Achiev6",0);
        myEditor.putInt("Achiev7",0);
        myEditor.commit();
    }
}
