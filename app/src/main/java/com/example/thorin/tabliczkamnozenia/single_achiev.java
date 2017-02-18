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
        return procent >= 50;
    }

    //achievement 75%
    static boolean achiev2() {
        return procent >= 0.75;
    }

    //achievement 100%
    static boolean achiev3() {
        return procent == 1;
    }

    //achievement 100% przez 3 dni
    static boolean achiev4() {
        return procent == 1;
    }

    //achievement 100% przez 7 dni
    static boolean achiev5() {
        return procent == 1;
    }

    //achievement 100% przez 14 dni w dwoch grach dziennie
    static boolean achiev6() {
        return procent == 1;
    }

    //achievement 100% przez 30 dni w czterech grach dziennie
    static boolean achiev7() {
        return procent == 1;
    }

    //achievement 44%
    static boolean achiev8() {
        return procent == 0.44;
    }

    //pobiera % wygranych
    static void setwin(double a) {
        procent = a;
    }

    private single_achiev() {
        //tsd
    }
}
