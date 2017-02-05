package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Ekran_gry extends Activity {
    ProgressBar progressBar;
    Button buttonStartProgress0, buttonStartProgress1;
    Button przy0, przy1, przy2, przy3, przy4;
    Button przy5, przy6, przy7, przy8, przy9;
    String string,zmie;
    int a = 0, b = 0, c, d, s, licz = 1, dbr = 0;
    Random rand = new Random();
    AsyncTask<Void, Integer, Void> task;
    //pobieranie liczby pytan i czasu na odpowiedz
    int liczbap = single_poziom.getpytan();
    int cznodp = single_poziom.getczas();

    //przy tworzeniu
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_gry);
        buttonStartProgress0 = (Button) findViewById(R.id.button10);
        buttonStartProgress1 = (Button) findViewById(R.id.button11);
        final TextView textview = (TextView)findViewById(R.id.odp);
        przy0 = (Button) findViewById(R.id.button0);
        przy1 = (Button) findViewById(R.id.button1);
        przy2 = (Button) findViewById(R.id.button2);
        przy3 = (Button) findViewById(R.id.button3);
        przy4 = (Button) findViewById(R.id.button4);
        przy5 = (Button) findViewById(R.id.button5);
        przy6 = (Button) findViewById(R.id.button6);
        przy7 = (Button) findViewById(R.id.button7);
        przy8 = (Button) findViewById(R.id.button8);
        przy9 = (Button) findViewById(R.id.button9);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //działanie x'ów
        buttonStartProgress0.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    a = 0;
                }
                if (a == 1 && b == 1){
                    sendon();
                    a = 0;
                    b = 0;
                    task = new BackgroundAsyncTask().execute();
                }
                return true;
            }
        });
        buttonStartProgress1.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    b = 1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    b = 0;
                }
                if (a == 1 && b == 1){
                    sendon();
                    a = 0;
                    b = 0;
                    task = new BackgroundAsyncTask().execute();
                }
                return true;
            }
        });

        //klikanie cyferek
        setOnClickListener(przy0, textview);
        setOnClickListener(przy1, textview);
        setOnClickListener(przy2, textview);
        setOnClickListener(przy3, textview);
        setOnClickListener(przy4, textview);
        setOnClickListener(przy5, textview);
        setOnClickListener(przy6, textview);
        setOnClickListener(przy7, textview);
        setOnClickListener(przy8, textview);
        setOnClickListener(przy9, textview);

        //wylaczenie klawiatury
        klawoff();

        final TextView tvzad = (TextView)findViewById(R.id.zadanie);
        tvzad.setText("Zadanie 0/" + liczbap);
    }

    @Override
    public void onDestroy(){
        task.cancel(true);
        super.onDestroy();
    }

    //wpisywanie w odpowiedź
    public void setOnClickListener(final Button przy, final TextView textview){
        przy.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                string = (String) przy.getText().toString();
                textview.setText(textview.getText().toString() + string);
                sprawdz();
            }});
    }

    //odliczanie czasu
    public class BackgroundAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        int myProgress;
        final TextView tvopd = (TextView)findViewById(R.id.odp);
        final TextView tvpyt = (TextView)findViewById(R.id.pyt);
        final TextView tvzad = (TextView)findViewById(R.id.zadanie);


        @Override
        protected void onPostExecute(Void result) {
            if (licz == liczbap) koniec();
            licz++;
            //sprawdzenie wyniku na koniec czasu
            zmie = tvopd.getText().toString();
            if (zmie.equals("")) {
                bad();
            }
            else {
                int convert = Integer.valueOf(zmie);
                if (convert != s) bad();
            }
            klawoff();
        }

        @Override
        protected void onPreExecute() {
            myProgress = 100;
            //losowanie liczb c i d
            c = losuj();
            d = losuj();
            s = c * d;
            //wyświetlanie pytania
            tvpyt.setText(c + " * " + d + " =");
            //czyszczenie odpowiedzi
            tvopd.setText("");
            tvzad.setText("Zadanie " + licz + "/" + liczbap);
            klawon();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (; myProgress > -1 && !this.isCancelled(); myProgress--) {
                publishProgress(myProgress);
                SystemClock.sleep(cznodp);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled(Void result){
            if (licz == liczbap) koniec();
            licz++;
            progressBar.setProgress(0);
            klawoff();
            super.onCancelled();
        }
    }

    //wyłączanie i właczanie napisów
    private void findAndMakeVisible(int id){
        View view = findViewById(id);
        view.setVisibility(View.VISIBLE);
    }

    private void findAndMakeInvisible(int id){
        View view = findViewById(id);
        view.setVisibility(View.INVISIBLE);
    }

    //sprawdzanie wyniku
    private void sprawdz() {
        final TextView tvopd = (TextView) findViewById(R.id.odp);
        // zamiana stringa na int + sprawdzenie
        zmie = tvopd.getText().toString();
        int convert = Integer.valueOf(zmie);
        //dobrze
        if (convert == s) good();
            //zle
        else if (convert > s) {
            bad();
            task.cancel(true);
        }
        else if (s > 9 && convert < s && convert > 9) {
            bad();
            task.cancel(true);
        }
        else if (s < 10 && convert < s) {
            bad();
            task.cancel(true);
        }
    }

    //wyjscie do menu
    public void sendMenu(View view) {
        koniec();
    }

    //wyłącza instrukcje włącza pytania
    public void sendon() {
        findAndMakeInvisible(R.id.inst);
        findAndMakeInvisible(R.id.bad);
        findAndMakeInvisible(R.id.good);
        findAndMakeVisible(R.id.pyt);
        findAndMakeVisible(R.id.odp);
    }

    //dobra odpowiedz
    public void good() {
        dbr++;
        task.cancel(true);
        findAndMakeVisible(R.id.good);
        klawoff();
    }
    //zla odpowiedz
    public void bad() {
        task.cancel(true);
        findAndMakeVisible(R.id.bad);
        findAndMakeInvisible(R.id.pyt);
        findAndMakeInvisible(R.id.odp);
        klawoff();
    }

    //wyłaczanie klawiatury
    public void klawoff() {
        buttonStartProgress0.setEnabled(true);
        buttonStartProgress1.setEnabled(true);
        przy0.setEnabled(false);
        przy1.setEnabled(false);
        przy2.setEnabled(false);
        przy3.setEnabled(false);
        przy4.setEnabled(false);
        przy5.setEnabled(false);
        przy6.setEnabled(false);
        przy7.setEnabled(false);
        przy8.setEnabled(false);
        przy9.setEnabled(false);
    }

    //wlaczanie klawiatury
    public void klawon() {
        buttonStartProgress0.setEnabled(false);
        buttonStartProgress1.setEnabled(false);
        przy0.setEnabled(true);
        przy1.setEnabled(true);
        przy2.setEnabled(true);
        przy3.setEnabled(true);
        przy4.setEnabled(true);
        przy5.setEnabled(true);
        przy6.setEnabled(true);
        przy7.setEnabled(true);
        przy8.setEnabled(true);
        przy9.setEnabled(true);
    }

    // losowanie liczb
    private int losuj(){
        int a,b = rand.nextInt(100) + 1;
        if (b >= 0 && b <= 8) a = 1;
        else if (b >= 9 && b <= 19) a = 2;
        else if (b >= 20 && b <= 30)  a = 3;
        else if (b >= 31 && b <= 41)  a = 4;
        else if (b >= 42 && b <= 52)  a = 5;
        else if (b >= 53 && b <= 64)  a = 6;
        else if (b >= 65 && b <= 76)  a = 7;
        else if (b >= 77 && b <= 88)  a = 8;
        else a = 9;
        return a;
    }

    //wyswietla wynik i konczy
    void koniec(){
        final TextView tvwynik = (TextView)findViewById(R.id.wynik);
        findAndMakeInvisible(R.id.inst);
        findAndMakeInvisible(R.id.bad);
        findAndMakeInvisible(R.id.good);
        findAndMakeInvisible(R.id.pyt);
        findAndMakeInvisible(R.id.odp);
        findAndMakeVisible(R.id.wynik);
        tvwynik.setText("Poprawnych\nodpowiedzi " + dbr + "/" + liczbap);
    }
}