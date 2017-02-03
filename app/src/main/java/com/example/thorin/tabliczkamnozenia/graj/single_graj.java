package com.example.thorin.tabliczkamnozenia.graj;

/**
 * Created by Thorin on 2017-02-03.
 */
public class single_graj {
    private static single_graj ourInstance = new single_graj();

    public static single_graj getInstance() {
        return ourInstance;
    }

    private single_graj() {
    }
}
