package com.example.thorin.tabliczkamnozenia;

/**
 * Created by Thorin on 2017-02-03.
 */
public class single_poziom {
    private static single_poziom ourInstance = new single_poziom();

    public static single_poziom getInstance() {
        return ourInstance;
    }

    private static int poziom = 1;

    static int getpytan() {
        int pytan;
        if(poziom == 1) pytan = 10;
        else if(poziom == 2) pytan = 20;
        else if(poziom == 3) pytan = 30;
        else if(poziom == 4) pytan = 40;
        else pytan = 50;
        return pytan;
    }

    static int getczas() {
        int czas;
        if(poziom == 1)czas = 300;
        else if(poziom == 2) czas = 200;
        else if(poziom == 3) czas = 150;
        else if(poziom == 4) czas = 100;
        else czas = 50;
        return czas;
    }

    static int getpoziom() {
        return poziom;
    }

    static void setpoziom(int a) {
        poziom = a;
    }

    private single_poziom() {
    }
}
