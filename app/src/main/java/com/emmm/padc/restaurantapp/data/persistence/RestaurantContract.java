package com.emmm.padc.restaurantapp.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.emmm.padc.restaurantapp.RestaurantApp;

/**
 * Created by EI on 7/3/2017.
 */

public class RestaurantContract {
    public static final String CONTENT_AUTHORITY = RestaurantApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RESTAURANTS = "restaurants";
    public static final String PATH_RESTAURANTS_TAGS = "restaurant_tags";

    public static final class RestaurantEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String TABLE_NAME = "restaurants";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ADD_SHORT = "short_addr";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TOTAL_RATING_COUNT = "total_rating_count";
        public static final String COLUMN_AVERAGE_RATING_VALUE = "average_rating_value";
        public static final String COLUMN_IS_AD = "is_add";
        public static final String COLUMN_IS_NEW = "is_new";
        public static final String COLUMN_LEAD_TIME_IN_MIN = "lead_time_in_min";

        public static Uri buildRestaurantUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantUriWithTitle(String title) {
            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_TITLE, title).build();
        }

        public static  String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

    public static final class RestaurantTagEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS_TAGS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS_TAGS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS_TAGS;

        public static final String TABLE_NAME = "restaurant_tags";

        public static final String COLUMN_RESTAURANT_TITLE = "restaurant_title";
        public static final String COLUMN_TAG = "tag";

        public static Uri buildRestaurantTagUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantTagUriWithTitle(String restaurantTitle) {
            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_RESTAURANT_TITLE, restaurantTitle).build();
        }

        public static String getRestaurantTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RESTAURANT_TITLE);
        }
    }
}
