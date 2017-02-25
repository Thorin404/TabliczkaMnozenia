package com.example.thorin.tabliczkamnozenia;

/**
 * Created by Thorin on 2017-02-03.
 */
public class single_poziom {

    static int getpytan(int poziom) {
        int pytan;
        if(poziom == 0) pytan = 1;
        else if(poziom == 1) pytan = 1;
        else if(poziom == 2) pytan = 1;
        else if(poziom == 3) pytan = 1;
        else pytan = 1;
        return pytan;
    }

    static int getczas(int poziom) {
        int czas;
        if(poziom == 0)czas = 300;
        else if(poziom == 1) czas = 200;
        else if(poziom == 2) czas = 150;
        else if(poziom == 3) czas = 100;
        else czas = 50;
        return czas;
    }

    private single_poziom() {
    }
}