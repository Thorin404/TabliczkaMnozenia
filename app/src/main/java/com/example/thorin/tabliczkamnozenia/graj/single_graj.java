package com.example.thorin.tabliczkamnozenia.graj;

/**
 * Created by Thorin on 2017-02-01.
 */
public class single_graj {

    private static single_graj instance = new single_graj();

    private int level = 0;

    private Singleton() {
    }

    public static single_graj get(){
        return instance;
    }

    public int getLevel() {
        return level;
    }

    void setLevel(int level) {
        this.level = level;
    }

    public static void main(String[] args) {

    }
}