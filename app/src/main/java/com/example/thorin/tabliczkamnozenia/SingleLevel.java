package com.example.thorin.tabliczkamnozenia;

/**
 * Created by Thorin on 2017-02-03.
 */
public class SingleLevel {

    static int getQuestion(int poziom) {
        int question;
        if(poziom == 0) question = 10;
        else if(poziom == 1) question = 20;
        else if(poziom == 2) question = 30;
        else if(poziom == 3) question = 40;
        else question = 50;
        return question;
    }

    static int getTime(int level) {
        int time;
        if(level == 0)time = 300;
        else if(level == 1) time = 200;
        else if(level == 2) time = 150;
        else if(level == 3) time = 100;
        else time = 50;
        return time;
    }

    private SingleLevel() {
    }
}