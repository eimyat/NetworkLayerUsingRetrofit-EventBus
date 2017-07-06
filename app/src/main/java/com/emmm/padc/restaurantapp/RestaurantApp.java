package com.emmm.padc.restaurantapp;

import android.app.Application;
import android.content.Context;

import com.emmm.padc.restaurantapp.data.models.RestaurantModel;
import com.facebook.stetho.Stetho;

/**
 * Created by EI on 7/3/2017.
 */

public class RestaurantApp extends Application {

    public static final String TAG = "RestaurantApp";

    public static RestaurantApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        RestaurantModel.getInstance().loadRestaurants();

        Stetho.initializeWithDefaults(this);
    }

    public static Context getContext() {
        return INSTANCE.getApplicationContext();
    }
}
