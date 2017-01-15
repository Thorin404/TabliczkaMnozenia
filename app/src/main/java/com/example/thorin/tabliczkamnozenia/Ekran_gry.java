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




public class Ekran_gry extends Activity {
    ProgressBar progressBar;
    Button buttonStartProgress0,buttonStartProgress1;
    Button przy0,przy1,przy2,przy3,przy4,przy5,przy6,przy7,przy8,przy9;
    String string;
    int a=0,b=0;

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
        //progressBar.setProgress(100);

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
        final TextView textview = (TextView)findViewById(R.id.odp);

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            Toast.makeText(Ekran_gry.this,
                    "czas minął", Toast.LENGTH_LONG).show();
            sendoff();
            string = "";
            textview.setText(string);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            Toast.makeText(Ekran_gry.this,
                    "pośpiesz sie czas ucieka", Toast.LENGTH_SHORT).show();
            myProgress = 100;
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
        buttonStartProgress0.setClickable(true);
        buttonStartProgress1.setClickable(true);
    }
}
