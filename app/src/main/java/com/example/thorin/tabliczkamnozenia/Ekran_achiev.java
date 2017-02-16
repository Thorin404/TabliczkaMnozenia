package com.example.thorin.tabliczkamnozenia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckedTextView;

public class Ekran_achiev extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_achiev);
        final CheckedTextView ctv1= (CheckedTextView)findViewById(R.id.checkedTextView1);
        final CheckedTextView ctv2= (CheckedTextView)findViewById(R.id.checkedTextView2);
        final CheckedTextView ctv3= (CheckedTextView)findViewById(R.id.checkedTextView3);
        final CheckedTextView ctv4= (CheckedTextView)findViewById(R.id.checkedTextView4);
        final CheckedTextView ctv5= (CheckedTextView)findViewById(R.id.checkedTextView5);
        final CheckedTextView ctv6= (CheckedTextView)findViewById(R.id.checkedTextView6);
        final CheckedTextView ctv7= (CheckedTextView)findViewById(R.id.checkedTextView7);
        final CheckedTextView ctv8= (CheckedTextView)findViewById(R.id.checkedTextView8);
        //pobieranie achievmentów
        if (single_achiev.achiev1()) ctv1.setChecked(true);
        if (single_achiev.achiev2()) ctv2.setChecked(true);
        if (single_achiev.achiev3()) ctv3.setChecked(true);
        if (single_achiev.achiev4()) ctv4.setChecked(true);
        if (single_achiev.achiev5()) ctv5.setChecked(true);
        if (single_achiev.achiev6()) ctv6.setChecked(true);
        if (single_achiev.achiev7()) ctv7.setChecked(true);
        if (single_achiev.achiev8()) ctv8.setChecked(true);
    }
}
