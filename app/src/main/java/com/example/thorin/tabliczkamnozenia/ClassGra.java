package com.example.thorin.tabliczkamnozenia;

import java.util.Date;
import java.util.Random;

/**
 * Created by Thorin on 2017-02-24.
 */

public class ClassGra {

    static String whichLevel(int a){
        String poziom;
        if(a == 0) poziom = "achiev_P0";
        else if(a == 1) poziom = "achiev_P1";
        else if(a == 2) poziom = "achiev_P2";
        else if(a == 3) poziom = "achiev_P3";
        else poziom = "achiev_P4";
        return poziom;
    }

    static int randomize(){
        Random rand = new Random();
        int a,b = rand.nextInt(100) + 1;
        if (b >= 0 && b <= 8) a = 1;
        else if (b >= 9 && b <= 19) a = 2;
        else if (b >= 20 && b <= 30)  a = 3;
        else if (b >= 31 && b <= 41)  a = 4;
        else if (b >= 42 && b <= 52)  a = 5;
        else if (b >= 53 && b <= 64)  a = 6;
        else if (b >= 65 && b <= 76)  a = 7;
        else if (b >= 77 && b <= 88)  a = 8;
        else a = 9;
        return a;
    }

    public static long difference(Date d1, Date d2) {
        return Math.round((d1.getTime() - d2.getTime()) / 86400000.0);
    }
}
