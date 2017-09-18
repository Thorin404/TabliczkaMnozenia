package com.example.thorin.tabliczkamnozenia;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScreenLearn extends AppCompatActivity {
    Button[] buttonsArray = new Button[11];
    private boolean toggleBool = true;
    int abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_learn);


        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/EraserRegular.ttf");

        buttonsArray[1] = (Button) findViewById(R.id.button1);
        buttonsArray[2] = (Button) findViewById(R.id.button2);
        buttonsArray[3] = (Button) findViewById(R.id.button3);
        buttonsArray[4] = (Button) findViewById(R.id.button4);
        buttonsArray[5] = (Button) findViewById(R.id.button5);
        buttonsArray[6] = (Button) findViewById(R.id.button6);
        buttonsArray[7] = (Button) findViewById(R.id.button7);
        buttonsArray[8] = (Button) findViewById(R.id.button8);
        buttonsArray[9] = (Button) findViewById(R.id.button9);
        buttonsArray[10] = (Button) findViewById(R.id.button10);

        for (int i = 1; i < 11; i++) {
            buttonsArray[i].setTypeface(typeFace);
        }

        TextView tv = (TextView) findViewById(R.id.text_learn);
        tv.setTypeface(typeFace);
        tv = (TextView) findViewById(R.id.buttonMenu);
        tv.setTypeface(typeFace);
        tv = (TextView) findViewById(R.id.button);
        tv.setTypeface(typeFace);
        tv = (TextView) findViewById((R.id.multiplication));
        tv.setTypeface(typeFace);
        tv = (TextView) findViewById(R.id.multi_instruction);
        tv.setTypeface(typeFace);
    }

    //klik wyświetlanego mnozenia
    public void sendLearn1(View view) {
        changeMultiplication(1);
    }
    public void sendLearn2(View view) {
        changeMultiplication(2);
    }
    public void sendLearn3(View view) {
        changeMultiplication(3);
    }
    public void sendLearn4(View view) {
        changeMultiplication(4);
    }
    public void sendLearn5(View view) {
        changeMultiplication(5);
    }
    public void sendLearn6(View view) {
        changeMultiplication(6);
    }
    public void sendLearn7(View view) {
        changeMultiplication(7);
    }
    public void sendLearn8(View view) {
        changeMultiplication(8);
    }
    public void sendLearn9(View view) {
        changeMultiplication(9);
    }
    public void sendLearn10(View view) {
        changeMultiplication(10);
    }

    //zmiana wyswietlanego mnozenia
    private void changeMultiplication(int a) {
        abc = a;
        TextView tvMulti = (TextView) findViewById(R.id.multiplication);
        tvMulti.setVisibility(View.VISIBLE);
        TextView view1 = (TextView) findViewById(R.id.multi_instruction);
        view1.setVisibility(View.INVISIBLE);
        for (int i = 1; i < 11; i++) {
            buttonsArray[i].setEnabled(true);
        }
        buttonsArray[a].setEnabled(false);
        display(a);
    }

    private void display(int a){
        TextView tvMulti = (TextView) findViewById(R.id.multiplication);
        tvMulti.setText(R.string.text_multi_begin);
        for (int i=1; i < 11; i++) {
            String old = (String) tvMulti.getText();
            if (toggleBool) {
                tvMulti.setText(old + a + " * " + i + " = " + (a * i) + "\n");
            } else {
                tvMulti.setText(old + i + " * " + a + " = " + (a * i) + "\n");
            }
        }
    }

    //wcisniecie toggle
    public void sendToggle(View view){
        toggleBool=!toggleBool;
        display(abc);
    }

    //wyjscie do menu
    public void sendMenu(View view) {
        finish();
    }
}
