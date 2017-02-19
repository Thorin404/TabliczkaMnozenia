package com.example.thorin.tabliczkamnozenia;

import android.graphics.Typeface;
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

    private void achievon(){
        if (single_achiev.achiev0()) findAndCheck(R.id.checkedTextView0);
        if (single_achiev.achiev1()) findAndCheck(R.id.checkedTextView1);
        if (single_achiev.achiev2()) findAndCheck(R.id.checkedTextView2);
        if (single_achiev.achiev3()) findAndCheck(R.id.checkedTextView3);
        if (single_achiev.achiev4()) findAndCheck(R.id.checkedTextView4);
        if (single_achiev.achiev5()) findAndCheck(R.id.checkedTextView5);
        if (single_achiev.achiev6()) findAndCheck(R.id.checkedTextView6);
        if (single_achiev.achiev7()) findAndCheck(R.id.checkedTextView7);
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
        single_achiev.clear();
        achievoff();
    }
}
