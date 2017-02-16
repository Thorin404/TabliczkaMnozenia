package com.example.thorin.tabliczkamnozenia;

/**
 * Created by Thorin on 2017-02-03.
 */
public class single_achiev {
    private static single_achiev ourInstance = new single_achiev();

    public static single_achiev getInstance() {
        return ourInstance;
    }

    private static double procent;

    //achievement 50%
    static boolean achiev1() {
        if (procent >= 50) return true;
        else return false;
    }

    //achievement 75%
    static boolean achiev2() {
        if (procent >= 0.75) return true;
        else return false;
    }

    //achievement 100%
    static boolean achiev3() {
        if (procent == 1) return true;
        else return false;
    }

    //achievement 100% przez 3 dni
    static boolean achiev4() {
        if (procent == 1) return true;
        else return false;
    }

    //achievement 100% przez 7 dni
    static boolean achiev5() {
        if (procent == 1) return true;
        else return false;
    }

    //achievement 100% przez 14 dni w dwoch grach dziennie
    static boolean achiev6() {
        if (procent == 1) return true;
        else return false;
    }

    //achievement 100% przez 30 dni w czterech grach dziennie
    static boolean achiev7() {
        if (procent == 1) return true;
        else return false;
    }

    //achievement 44%
    static boolean achiev8() {
        if (procent == 0.44) return true;
        else return false;
    }

    //pobiera % wygranych
    static void setwin(double a) {
        procent = a;
    }

    private single_achiev() {
        //tsd
    }
}
