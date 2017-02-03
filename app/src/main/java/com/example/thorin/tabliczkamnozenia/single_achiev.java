package com.example.thorin.tabliczkamnozenia;

/**
 * Created by Thorin on 2017-02-03.
 */
public class single_achiev {
    private static single_achiev ourInstance = new single_achiev();

    public static single_achiev getInstance() {
        return ourInstance;
    }

    private single_achiev() {
    }
}
