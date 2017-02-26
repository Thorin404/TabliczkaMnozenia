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
    Button buttonMenu;
    Button[] buttonsArray = new Button[12];
    String string, zmie;
    int firstX = 0, secondX = 0, numberFirst, numberSecond, score, count = 0, goodAnswer = 0;
    int questionNumber, answerTime;
    AsyncTask<Void, Integer, Void> task;
    public static String PREFS_ACHIEV;

    //buttonMenu tworzeniu
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_gry);
        //pobieranie liczby pytan i czasu na odpowiedz
        SharedPreferences settings = getSharedPreferences("poziom", 0);
        int poziom = settings.getInt("poziom", 0);
        PREFS_ACHIEV = ClassGra.whichLevel(poziom);
        questionNumber = SingleLevel.getQuestion(poziom);
        answerTime = SingleLevel.getTime(poziom);

        final TextView textview = (TextView) findViewById(R.id.odp);
        buttonsArray[0] = (Button) findViewById(R.id.button0);
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
        buttonsArray[11] = (Button) findViewById(R.id.button11);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //działanie x'ów
        buttonsArray[10].setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    firstX = 1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    firstX = 0;
                }
                if (firstX == 1 && secondX == 1) {
                    sendOn();
                    firstX = secondX = 0;
                    task = new BackgroundAsyncTask().execute();
                }
                return true;
            }
        });
        buttonsArray[11].setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    secondX = 1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    secondX = 0;
                }
                if (firstX == 1 && secondX == 1) {
                    sendOn();
                    firstX = secondX = 0;
                    task = new BackgroundAsyncTask().execute();
                }
                return true;
            }
        });

        //klikanie cyferek
        for (int i = 0; i < 10; i++) {
            setOnClickListener(buttonsArray[i], textview);
        }

        //wylaczenie klawiatury
        numeric(false);

        final TextView tvzad = (TextView) findViewById(R.id.zadanie);
        tvzad.setText("Zadanie " + count + "/" + questionNumber);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/EraserRegular.ttf");
        for (int i = 0; i < 12; i++) {
            buttonsArray[i].setTypeface(typeFace);
        }
        final TextView tvpyt = (TextView) findViewById(R.id.pyt);
        final TextView tvbad = (TextView) findViewById(R.id.bad);
        final TextView tvgood = (TextView) findViewById(R.id.good);
        final TextView tvinst = (TextView) findViewById(R.id.inst);
        final TextView tvwynik = (TextView) findViewById(R.id.wynik);
        buttonMenu = (Button) findViewById(R.id.button);
        tvzad.setTypeface(typeFace);
        tvpyt.setTypeface(typeFace);
        tvgood.setTypeface(typeFace);
        tvbad.setTypeface(typeFace);
        tvinst.setTypeface(typeFace);
        tvwynik.setTypeface(typeFace);
        buttonMenu.setTypeface(typeFace);
        textview.setTypeface(typeFace);
    }

    @Override
    public void onDestroy() {
        if (count != 0) task.cancel(true);
        float result = (float) goodAnswer / (float) questionNumber;
        //****************** tutaj jest całe zapisywanie i ogarnianie ile razy ktoś wygrał
        SharedPreferences settings = getSharedPreferences(PREFS_ACHIEV, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (result == 1f) {
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
        if (result >= 0.5f) editor.putInt("Achiev0", 1);
        if (result >= 0.75f) editor.putInt("Achiev1", 1);
        if (result == 1f) editor.putInt("Achiev2", 1);
        if (settings.getInt("Achiev_days", 0) >= 3) editor.putInt("Achiev3", 1);
        if (settings.getInt("Achiev_days", 0) >= 7) editor.putInt("Achiev4", 1);
        if (settings.getInt("Achiev_days2", 0) >= 14) editor.putInt("Achiev5", 1);
        if (settings.getInt("Achiev_days4", 0) >= 30) editor.putInt("Achiev6", 1);
        if (result == 0.44f) editor.putInt("Achiev7", 1);
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
            if (count == questionNumber) end();
            count++;
            //sprawdzenie wyniku na koniec czasu
            zmie = tvopd.getText().toString();
            if (zmie.equals("")) {
                bad();
            } else {
                int convert = Integer.valueOf(zmie);
                if (convert != score) bad();
            }
        }

        @Override
        protected void onPreExecute() {
            if (count == 0) count++;
            myProgress = 100;
            //losowanie liczb numberFirst i numberSecond
            numberFirst = ClassGra.randomize();
            numberSecond = ClassGra.randomize();
            score = numberFirst * numberSecond;
            //wyświetlanie pytania
            tvpyt.setText(numberFirst + " * " + numberSecond + " =");
            //czyszczenie odpowiedzi
            tvopd.setText("");
            tvzad.setText("Zadanie " + count + "/" + questionNumber);
            numeric(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (; myProgress > -1 && !this.isCancelled(); myProgress--) {
                publishProgress(myProgress);
                SystemClock.sleep(answerTime);
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
            if (count == questionNumber) end();
            count++;
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
        int scoreNew = Integer.valueOf(zmie);
        //sprawdzanie
        if (scoreNew == score) {
            good();
        } else if (scoreNew > score) {
            bad();
        } else if (score > 9 && scoreNew < score && scoreNew > 9){
            bad();
        } else if (score < 10 && scoreNew < score){
            bad();
        }
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
        goodAnswer++;
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
            buttonsArray[i].setEnabled(a);
            if (i > 9) buttonsArray[i].setEnabled(!a);
        }
    }

    //wyswietla wynik i konczy
    void end() {
        final TextView tvwynik = (TextView) findViewById(R.id.wynik);
        buttonsArray[10].setEnabled(false);
        buttonsArray[11].setEnabled(false);
        findAndMakeInvisible(R.id.inst);
        findAndMakeInvisible(R.id.bad);
        findAndMakeInvisible(R.id.good);
        findAndMakeInvisible(R.id.pyt);
        findAndMakeInvisible(R.id.odp);
        findAndMakeVisible(R.id.wynik);
        float result = (float) goodAnswer / (float) questionNumber * 100;
        tvwynik.setText("Udzielono " + goodAnswer + " poprawnych\nodpowiedzi co daje " + result + "%");
    }
}