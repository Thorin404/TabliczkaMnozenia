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
import android.widget.Toast;

import java.util.Random;



public class Ekran_gry extends Activity {
    ProgressBar progressBar;
    Button buttonStartProgress0,buttonStartProgress1;
    Button przy0,przy1,przy2,przy3,przy4,przy5,przy6,przy7,przy8,przy9;
    String string,zmie;
    int a=0,b=0,c,d,e,s;
    Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_gry);
        buttonStartProgress0 = (Button) findViewById(R.id.button10);
        buttonStartProgress1 = (Button) findViewById(R.id.button11);
        final TextView textview = (TextView)findViewById(R.id.odp);
        przy0= (Button) findViewById(R.id.button0);
        przy1= (Button) findViewById(R.id.button1);
        przy2= (Button) findViewById(R.id.button2);
        przy3= (Button) findViewById(R.id.button3);
        przy4= (Button) findViewById(R.id.button4);
        przy5= (Button) findViewById(R.id.button5);
        przy6= (Button) findViewById(R.id.button6);
        przy7= (Button) findViewById(R.id.button7);
        przy8= (Button) findViewById(R.id.button8);
        przy9= (Button) findViewById(R.id.button9);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //działanie x'ów
        buttonStartProgress0.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    a=1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    a=0;
                }
                if (a==1 && b==1){
                    sendon();
                    a=2;
                    b=2;
                }
                else if (a==2 &&b==2) {
                    new BackgroundAsyncTask().execute();
                    a=0;
                    b=0;
                }
                return true;
            }
        });
        buttonStartProgress1.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    b=1;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    b=0;
                }
                if (a==1 && b==1){
                    sendon();
                    a=2;
                    b=2;
                }
                else if (a==2 &&b==2) {
                    new BackgroundAsyncTask().execute();
                    a=0;
                    b=0;
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

    }

    //wpisywanie w odpowiedź
    private void setOnClickListener(final Button przy, final TextView textview){
        przy.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                string = (String) przy.getText().toString();
                textview.setText(textview.getText().toString() + string);
            }});
    }

    //odliczanie czasu
    public class BackgroundAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        int myProgress;
        final TextView tvopd = (TextView)findViewById(R.id.odp);
        final TextView tvpyt = (TextView)findViewById(R.id.pyt);

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            Toast.makeText(Ekran_gry.this,
                    "koniec czasu", Toast.LENGTH_SHORT).show();
            sprawdz();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            Toast.makeText(Ekran_gry.this,
                    "pośpiesz sie czas ucieka", Toast.LENGTH_SHORT).show();
            myProgress = 100;
            //losowanie liczb c i d
            //losuj(c);
            c = rand.nextInt(10) + 1;
            //losuj(d);
            d = rand.nextInt(10) + 1;
            s=c*d;
            //wyświetlanie pytania
            string = c+" * "+d;
            tvpyt.setText(string);
            //czyszczenie odpowiedzi
            string = "";
            tvopd.setText(string);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            while (myProgress > 0) {
                myProgress--;
                publishProgress(myProgress);
                SystemClock.sleep(10);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            progressBar.setProgress(values[0]);
        }

    }

    //wyjscie do menu
    public void sendMenu(View view) {
        finish();
    }

    //wyłącza instrukcje włącza pytania
    public void sendon() {
        View view;
        view = findViewById(R.id.inst);
        view.setVisibility(View.INVISIBLE);
        view = findViewById(R.id.pyt);
        view.setVisibility(View.VISIBLE);
        view = findViewById(R.id.odp);
        view.setVisibility(View.VISIBLE);
        buttonStartProgress0.setClickable(false);
        buttonStartProgress1.setClickable(false);
    }

    //włącza instrukcje wyłącza pytania
    public void sendoff() {
        View view;
        view = findViewById(R.id.inst);
        view.setVisibility(View.VISIBLE);
        view = findViewById(R.id.pyt);
        view.setVisibility(View.INVISIBLE);
        view = findViewById(R.id.odp);
        view.setVisibility(View.INVISIBLE);
        view = findViewById(R.id.good);
        view.setVisibility(View.INVISIBLE);
        view = findViewById(R.id.bad);
        view.setVisibility(View.INVISIBLE);
        buttonStartProgress0.setClickable(true);
        buttonStartProgress1.setClickable(true);
    }

    //sprawdz
    public void sprawdz() {
        final TextView tvopd = (TextView)findViewById(R.id.odp);
        // zamiana stringa na int + sprawdzenie
        zmie = tvopd.getText().toString();
        int convert=Integer.valueOf(zmie);
        //dobrze
        if(convert==s) {
            good();
        }
        //zle
        else {
            bad();
        }
    }

    //dobrze
    public void good() {
        View view;
        view = findViewById(R.id.good);
        view.setVisibility(View.VISIBLE);
        SystemClock.sleep(1000);
        sendoff();
    }
    public void bad() {
        View view;
        view = findViewById(R.id.bad);
        view.setVisibility(View.VISIBLE);
        view = findViewById(R.id.pyt);
        view.setVisibility(View.INVISIBLE);
        view = findViewById(R.id.odp);
        view.setVisibility(View.INVISIBLE);
        SystemClock.sleep(1000);
        sendoff();
    }

    // losowanie liczb
    public int losuj(int a){
        e = rand.nextInt(100) + 1;
        {
            if (e >= 0 && e <= 8) {
                a = 1;
            } else if (e >= 9 && e <= 19) {
                a = 2;
            } else if (e >= 20 && e <= 30) {
                a = 3;
            } else if (e >= 31 && e <= 41) {
                a = 4;
            } else if (e >= 42 && e <= 52) {
                a = 5;
            } else if (e >= 53 && e <= 64) {
                a = 6;
            } else if (e >= 65 && e <= 76) {
                a = 7;
            } else if (e >= 77 && e <= 88) {
                a = 8;
            } else {
                a = 9;
            }
            return a;
        }
    }

}