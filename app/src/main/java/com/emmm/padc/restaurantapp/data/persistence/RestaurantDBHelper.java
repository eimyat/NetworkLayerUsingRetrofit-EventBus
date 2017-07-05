package com.emmm.padc.restaurantapp.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by EI on 7/3/2017.
 */

public class RestaurantDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "restaurants.db";

    private static final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantContract.RestaurantEntry.TABLE_NAME + " (" +
            RestaurantContract.RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantContract.RestaurantEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RestaurantContract.RestaurantEntry.COLUMN_ADD_SHORT + " TEXT, " +
            RestaurantContract.RestaurantEntry.COLUMN_IMAGE + " TEXT, " +
            RestaurantContract.RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE + " TEXT NOT NULL, " +
            RestaurantContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT + " TEXT NOT NULL, " +
            RestaurantContract.RestaurantEntry.COLUMN_IS_AD + " TEXT NOT NULL, " +
            RestaurantContract.RestaurantEntry.COLUMN_IS_NEW + " TEXT NOT NULL, " +
            RestaurantContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN + " TEXT NOT NULL, " +

            " UNIQUE (" + RestaurantContract.RestaurantEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_RESTAURANT_TAG_TABLE = "CREATE TABLE " + RestaurantContract.RestaurantTagEntry.TABLE_NAME + " (" +
            RestaurantContract.RestaurantTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " TEXT NOT NULL, " +
            RestaurantContract.RestaurantTagEntry.COLUMN_TAG + "TEXT NOT NULL, " +

            " UNIQUE (" + RestaurantContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + ", " +
            RestaurantContract.RestaurantTagEntry.COLUMN_TAG + ") ON CONFLICT IGNORE" +
            ");";

    public RestaurantDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RESTAURANT_TABLE);
        db.execSQL(SQL_CREATE_RESTAURANT_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantContract.RestaurantEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantContract.RestaurantTagEntry.TABLE_NAME);

        onCreate(db);
    }
}
