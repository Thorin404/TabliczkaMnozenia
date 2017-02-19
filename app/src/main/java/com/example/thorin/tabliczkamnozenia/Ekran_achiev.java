package com.example.thorin.tabliczkamnozenia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

public class Ekran_achiev extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        achievon();
    }

    private void achievon(){
        setContentView(R.layout.activity_ekran_achiev);
        final CheckedTextView ctv0= (CheckedTextView)findViewById(R.id.checkedTextView0);
        final CheckedTextView ctv1= (CheckedTextView)findViewById(R.id.checkedTextView1);
        final CheckedTextView ctv2= (CheckedTextView)findViewById(R.id.checkedTextView2);
        final CheckedTextView ctv3= (CheckedTextView)findViewById(R.id.checkedTextView3);
        final CheckedTextView ctv4= (CheckedTextView)findViewById(R.id.checkedTextView4);
        final CheckedTextView ctv5= (CheckedTextView)findViewById(R.id.checkedTextView5);
        final CheckedTextView ctv6= (CheckedTextView)findViewById(R.id.checkedTextView6);
        final CheckedTextView ctv7= (CheckedTextView)findViewById(R.id.checkedTextView7);
        if (single_achiev.achiev0()) ctv0.setChecked(true);
        if (single_achiev.achiev1()) ctv1.setChecked(true);
        if (single_achiev.achiev2()) ctv2.setChecked(true);
        if (single_achiev.achiev3()) ctv3.setChecked(true);
        if (single_achiev.achiev4()) ctv4.setChecked(true);
        if (single_achiev.achiev5()) ctv5.setChecked(true);
        if (single_achiev.achiev6()) ctv6.setChecked(true);
        if (single_achiev.achiev7()) ctv7.setChecked(true);
    }

    private void achievoff(){
        setContentView(R.layout.activity_ekran_achiev);
        final CheckedTextView ctv0= (CheckedTextView)findViewById(R.id.checkedTextView0);
        final CheckedTextView ctv1= (CheckedTextView)findViewById(R.id.checkedTextView1);
        final CheckedTextView ctv2= (CheckedTextView)findViewById(R.id.checkedTextView2);
        final CheckedTextView ctv3= (CheckedTextView)findViewById(R.id.checkedTextView3);
        final CheckedTextView ctv4= (CheckedTextView)findViewById(R.id.checkedTextView4);
        final CheckedTextView ctv5= (CheckedTextView)findViewById(R.id.checkedTextView5);
        final CheckedTextView ctv6= (CheckedTextView)findViewById(R.id.checkedTextView6);
        final CheckedTextView ctv7= (CheckedTextView)findViewById(R.id.checkedTextView7);
        if (single_achiev.achiev0()) ctv0.setChecked(false);
        if (single_achiev.achiev1()) ctv1.setChecked(false);
        if (single_achiev.achiev2()) ctv2.setChecked(false);
        if (single_achiev.achiev3()) ctv3.setChecked(false);
        if (single_achiev.achiev4()) ctv4.setChecked(false);
        if (single_achiev.achiev5()) ctv5.setChecked(false);
        if (single_achiev.achiev6()) ctv6.setChecked(false);
        if (single_achiev.achiev7()) ctv7.setChecked(false);
    }

    public void sendUsun(View view) {
        single_achiev.clear();
        achievoff();
    }
}
