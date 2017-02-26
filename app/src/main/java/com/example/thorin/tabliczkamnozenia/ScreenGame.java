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

import java.util.Calendar;

public class ScreenGame extends Activity {
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
        liczbap = SingleLevel.getQuestion(poziom);
        cznodp = SingleLevel.getTime(poziom);

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
                    sendOn();
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
                    sendOn();
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
        numeric(false);

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
        PREFS_ACHIEV = ClassGra.whichLevel(poziom);
        settings = getSharedPreferences(PREFS_ACHIEV, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("result", soc / cos);
        if ((soc / cos) == 1) {
            int days = settings.getInt("Achiev_days", 1);
            int days2 = settings.getInt("Achiev_days2", 1);
            int days4 = settings.getInt("Achiev_days4", 1);
            int wons = settings.getInt("Achiev_wons", 0);

            Calendar calendar = Calendar.getInstance();
            int yearNow = calendar.get(Calendar.YEAR);
            int dayOfYearNow = calendar.get(Calendar.DAY_OF_YEAR);
            int yearSaved = settings.getInt("Achiev_YearSaved", 1970);
            int dayOfYearSaved = settings.getInt("Achiev_DaySaved", 1);

            int roznica = ClassGra.difference(yearNow, dayOfYearNow, yearSaved, dayOfYearSaved);

            if (roznica == 0) {
                wons++;
                editor.putInt("Achiev_wons", wons);
                if (wons >= 2) editor.putInt("Achiev_days2p", wons);
                if (wons >= 4) editor.putInt("Achiev_days4p", wons);
            } else if (roznica == 1) {
                editor.putInt("Achiev_wons", 0);
                editor.putInt("Achiev_days", ++days);
                if (settings.getInt("Achiev_days2p", 0) >= 2) editor.putInt("Achiev_days2", ++days2);
                if (settings.getInt("Achiev_days4p", 0) >= 4) editor.putInt("Achiev_days4", ++days4);
            } else {
                editor.putInt("Achiev_days", 1);
                editor.putInt("Achiev_days2", 1);
                editor.putInt("Achiev_days4", 1);
            }
            editor.putInt("Achiev_YearSaved", yearNow);
            editor.putInt("Achiev_DaySaved", dayOfYearNow);
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
            numeric(false);
            if (licz == liczbap) end();
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
            c = ClassGra.randomize();
            d = ClassGra.randomize();
            s = c * d;
            //wyświetlanie pytania
            tvpyt.setText(c + " * " + d + " =");
            //czyszczenie odpowiedzi
            tvopd.setText("");
            tvzad.setText("Zadanie " + licz + "/" + liczbap);
            numeric(true);
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
            numeric(false);
            if (licz == liczbap) end();
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
        if (isGood(convert)) {
            good();
        } else if (isBad(convert)) {
            bad();
        }
    }

    private boolean isBad(int convert){
        return isTooBig(convert) || isTwoDigitAndTooSmall(convert) || isOneDigitAndTooSmall(convert);
    }

    private boolean isOneDigitAndTooSmall(int convert) {
        return s < 10 && convert < s;
    }

    private boolean isTwoDigitAndTooSmall(int convert) {
        return s > 9 && convert < s && convert > 9;
    }

    private boolean isTooBig(int convert) {
        return convert > s;
    }

    private boolean isGood(int convert) {
        return convert == s;
    }

    //wyjscie do menu
    public void sendMenu(View view) {
        finish();
    }

    //wyłącza instrukcje włącza pytania
    public void sendOn() {
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
        numeric(false);
    }

    //zla odpowiedz
    public void bad() {
        task.cancel(true);
        findAndMakeVisible(R.id.bad);
        findAndMakeInvisible(R.id.pyt);
        findAndMakeInvisible(R.id.odp);
        numeric(false);
    }

    //true wlacza numeryczna, false wylacza numeryczna
    void numeric(boolean a) {
        for (int i = 0; i < 12; i++) {
            przytab[i].setEnabled(a);
            if (i > 9) przytab[i].setEnabled(!a);
        }
    }

    //wyswietla wynik i konczy
    void end() {
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