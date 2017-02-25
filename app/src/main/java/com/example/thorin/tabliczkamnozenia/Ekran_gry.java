package com.example.thorin.tabliczkamnozenia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ekran_gry extends Activity {
    ProgressBar progressBar;
    Button przy, przy0, przy1, przy2, przy3, przy4;
    Button przy5, przy6, przy7, przy8, przy9, przy10, przy11;
    Button[] przytab = {przy0, przy1, przy2, przy3, przy4,
            przy5, przy6, przy7, przy8, przy9, przy10, przy11};
    String string, zmie;
    int a = 0, b = 0, c, d, s, licz = 0, dbr = 0;
    int liczbap, cznodp;
    AsyncTask<Void, Integer, Void> task;
    public static String PREFS_ACHIEV;

    //przy tworzeniu
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_gry);
        //pobieranie liczby pytan i czasu na odpowiedz
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        int poziom = settings.getInt("poziom", 0);
        liczbap = single_poziom.getpytan(poziom);
        cznodp = single_poziom.getczas(poziom);

        final TextView textview = (TextView) findViewById(R.id.odp);
        przytab[0] = (Button) findViewById(R.id.button0);
        przytab[1] = (Button) findViewById(R.id.button1);
        przytab[2] = (Button) findViewById(R.id.button2);
        przytab[3] = (Button) findViewById(R.id.button3);
        przytab[4] = (Button) findViewById(R.id.button4);
        przytab[5] = (Button) findViewById(R.id.button5);
        przytab[6] = (Button) findViewById(R.id.button6);
        przytab[7] = (Button) findViewById(R.id.button7);
        przytab[8] = (Button) findViewById(R.id.button8);
        przytab[9] = (Button) findViewById(R.id.button9);
        przytab[10] = (Button) findViewById(R.id.button10);
        przytab[11] = (Button) findViewById(R.id.button11);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //działanie x'ów
        przytab[10].setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    a = 1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    a = 0;
                }
                if (a == 1 && b == 1) {
                    sendon();
                    a = b = 0;
                    task = new BackgroundAsyncTask().execute();
                }
                return true;
            }
        });
        przytab[11].setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    b = 1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    b = 0;
                }
                if (a == 1 && b == 1) {
                    sendon();
                    a = b = 0;
                    task = new BackgroundAsyncTask().execute();
                }
                return true;
            }
        });

        //klikanie cyferek
        for (int i = 0; i < 10; i++) {
            setOnClickListener(przytab[i], textview);
        }

        //wylaczenie klawiatury
        numer(false);

        final TextView tvzad = (TextView) findViewById(R.id.zadanie);
        tvzad.setText("Zadanie " + licz + "/" + liczbap);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/EraserRegular.ttf");
        for (int i = 0; i < 12; i++) {
            przytab[i].setTypeface(typeFace);
        }
        final TextView tvpyt = (TextView) findViewById(R.id.pyt);
        final TextView tvbad = (TextView) findViewById(R.id.bad);
        final TextView tvgood = (TextView) findViewById(R.id.good);
        final TextView tvinst = (TextView) findViewById(R.id.inst);
        final TextView tvwynik = (TextView) findViewById(R.id.wynik);
        przy = (Button) findViewById(R.id.button);
        tvzad.setTypeface(typeFace);
        tvpyt.setTypeface(typeFace);
        tvgood.setTypeface(typeFace);
        tvbad.setTypeface(typeFace);
        tvinst.setTypeface(typeFace);
        tvwynik.setTypeface(typeFace);
        przy.setTypeface(typeFace);
        textview.setTypeface(typeFace);
    }

    @Override
    public void onDestroy() {
        if (licz != 0) task.cancel(true);
        //przekazanie wyniku do achievmentow
        float cos = liczbap, soc = dbr;
        //****************** tutaj jest całe zapisywanie i ogarnianie ile razy ktoś wygrał
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        int poziom = settings.getInt("poziom", 0);
        PREFS_ACHIEV = ClassGra.jakipoziom(poziom);
        settings = getSharedPreferences(PREFS_ACHIEV, 0);
        int roznica = settings.getInt("Achiev_roz", 0);
        int days = settings.getInt("Achiev_days", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("result", soc / cos);
        if ((soc / cos) == 1) {
            int wons = settings.getInt("Achiev_wons", 0);
            String data1zw = settings.getString("Achiev_date", "2017,2,27");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date date = new Date();
            if (roznica == 0) {
                wons = 0;
                editor.putInt("Achiev_wons", wons);
                roznica = 1;
                editor.putInt("Achiev_roz", roznica);
                Date now = new Date();
                String dateString = dateFormat.format(now);
                editor.putString("Achiev_date", dateString);
            }
            if (roznica == 1) {
                wons++;
                days++;
                editor.putInt("Achiev_wons", wons);
                editor.putInt("Achiev_days", days);
            }
            if (wons >= 2) {
                try {
                    Date pierwszyraz = dateFormat.parse(data1zw);
                    long roz = ClassGra.difference(date, pierwszyraz);
                    if (roz <= 14) {
                        editor.putInt("Achiev_days2", 1);
                        if (wons >= 4 && roz <= 30) {
                            editor.putInt("Achiev_days4", 1);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        editor.apply();
        //******************
        super.onDestroy();
    }

    //wpisywanie w odpowiedź
    public void setOnClickListener(final Button przy, final TextView textview) {
        przy.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                string = przy.getText().toString();
                textview.setText(textview.getText().toString() + string);
                sprawdz();
            }
        });
    }

    //odliczanie czasu
    public class BackgroundAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        int myProgress;
        final TextView tvopd = (TextView) findViewById(R.id.odp);
        final TextView tvpyt = (TextView) findViewById(R.id.pyt);
        final TextView tvzad = (TextView) findViewById(R.id.zadanie);

        @Override
        protected void onPostExecute(Void result) {
            numer(false);
            if (licz == liczbap) koniec();
            licz++;
            //sprawdzenie wyniku na koniec czasu
            zmie = tvopd.getText().toString();
            if (zmie.equals("")) {
                bad();
            } else {
                int convert = Integer.valueOf(zmie);
                if (convert != s) bad();
            }
        }

        @Override
        protected void onPreExecute() {
            if (licz == 0) licz++;
            myProgress = 100;
            //losowanie liczb c i d
            c = ClassGra.losuj();
            d = ClassGra.losuj();
            s = c * d;
            //wyświetlanie pytania
            tvpyt.setText(c + " * " + d + " =");
            //czyszczenie odpowiedzi
            tvopd.setText("");
            tvzad.setText("Zadanie " + licz + "/" + liczbap);
            numer(true);
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
        protected void onCancelled(Void result) {
            numer(false);
            if (licz == liczbap) koniec();
            licz++;
            progressBar.setProgress(0);
            super.onCancelled();
        }
    }

    //wyłączanie i właczanie napisów
    private void findAndMakeVisible(int id) {
        View view = findViewById(id);
        view.setVisibility(View.VISIBLE);
    }

    private void findAndMakeInvisible(int id) {
        View view = findViewById(id);
        view.setVisibility(View.INVISIBLE);
    }

    //sprawdzanie wyniku
    private void sprawdz() {
        final TextView tvopd = (TextView) findViewById(R.id.odp);
        // zamiana stringa na int + sprawdzenie
        zmie = tvopd.getText().toString();
        int convert = Integer.valueOf(zmie);
        //sprawdzanie
        if (convert == s) {
            good();
        } else if (convert > s) {
            bad();
        } else if (s > 9 && convert < s && convert > 9) {
            bad();
        } else if (s < 10 && convert < s) {
            bad();
        }
    }

    //wyjscie do menu
    public void sendMenu(View view) {
        finish();
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
        numer(false);
    }

    //zla odpowiedz
    public void bad() {
        task.cancel(true);
        findAndMakeVisible(R.id.bad);
        findAndMakeInvisible(R.id.pyt);
        findAndMakeInvisible(R.id.odp);
        numer(false);
    }

    //true wlacza numeryczna, false wylacza numeryczna
    void numer(boolean a) {
        for (int i = 0; i < 12; i++) {
            przytab[i].setEnabled(a);
            if (i > 9) przytab[i].setEnabled(!a);
        }
    }

    //wyswietla wynik i konczy
    void koniec() {
        final TextView tvwynik = (TextView) findViewById(R.id.wynik);
        przytab[10].setEnabled(false);
        przytab[11].setEnabled(false);
        findAndMakeInvisible(R.id.inst);
        findAndMakeInvisible(R.id.bad);
        findAndMakeInvisible(R.id.good);
        findAndMakeInvisible(R.id.pyt);
        findAndMakeInvisible(R.id.odp);
        findAndMakeVisible(R.id.wynik);
        tvwynik.setText("Poprawnych\nodpowiedzi " + dbr + "/" + liczbap);
    }
}