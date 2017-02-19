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
    private static int done[] = {0,0,0,0,0,0,0,0};

    //achievement 50%
    static boolean achiev0() {
        if (procent >= 0.5) done[0] = 1;
        return done[0] == 1;
    }

    //achievement 75%
    static boolean achiev1() {
        if (procent >= 0.75) done[1] = 1;
        return done[1] == 1;
    }

    //achievement 100%
    static boolean achiev2() {
        if (procent == 1) done[2] = 1;
        return done[2] == 1;
    }

    //achievement 100% przez 3 dni
    static boolean achiev3() {
        if (procent == 1) done[3] = 1;
        return done[3] == 1;
    }

    //achievement 100% przez 7 dni
    static boolean achiev4() {
        if (procent == 1) done[4] = 1;
        return done[4] == 1;
    }

    //achievement 100% przez 14 dni w dwoch grach dziennie
    static boolean achiev5() {
        if (procent == 1) done[5] = 1;
        return done[5] == 1;
    }

    //achievement 100% przez 30 dni w czterech grach dziennie
    static boolean achiev6() {
        if (procent == 1) done[6] = 1;
        return done[6] == 1;
    }

    //achievement 44%
    static boolean achiev7() {
        if (procent == 0.44) done[7] = 1;
        return done[7] == 1;
    }

    //pobiera % wygranych
    static void setwin(double a) {
        procent = a;
    }

    //usuwanie osiagnięć
    static void clear() {
        for (int i = 0; i < 8; i++) done[i] = 0;
    }

    private single_achiev() {
        //tsd
    }
}
