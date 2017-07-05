package com.emmm.padc.networklayerusingretrofiteventbus.utils;

import com.google.gson.Gson;

/**
 * Created by EI on 6/21/2017.
 */
public class CommonInstances {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}
