package com.emmm.padc.restaurantapp.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created EI on 7/3/2017.
 */

public class RestaurantProvider extends ContentProvider {

    public static final int RESTAURANT = 100;
    public static final int RESTAURANT_TAG = 200;

    private static final String sRestaurantTitleSelection = RestaurantContract.RestaurantEntry.COLUMN_TITLE + " = ?";
    private static final String sRestaurantTagSelectionwithTitle = RestaurantContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " = ?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private RestaurantDBHelper mRestaurantDBHelper;

    @Override
    public boolean onCreate() {
        mRestaurantDBHelper = new RestaurantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mRestaurantDBHelper.getReadableDatabase();
        Cursor queryCursor;
        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANT:
                String restaurantTitle = RestaurantContract.RestaurantEntry.getTitleFromParam(uri);
                if(TextUtils.isEmpty(restaurantTitle)) {
                    selection = sRestaurantTitleSelection;
                    selectionArgs = new String[]{restaurantTitle};
                }
                queryCursor = db.query(RestaurantContract.RestaurantEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case RESTAURANT_TAG:
                String title = RestaurantContract.RestaurantTagEntry.getRestaurantTitleFromParam(uri);
                if(title != null) {
                    selection = sRestaurantTagSelectionwithTitle;
                    selectionArgs = new String[]{title};
                }
                queryCursor = db.query(RestaurantContract.RestaurantTagEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANT:
                return RestaurantContract.RestaurantEntry.DIR_TYPE;
            case RESTAURANT_TAG:
                return RestaurantContract.RestaurantTagEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;

        switch (matchUri) {
            case RESTAURANT: {
                long _id = db.insert(RestaurantContract.RestaurantEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    insertedUri = RestaurantContract.RestaurantEntry.buildRestaurantUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }

            case RESTAURANT_TAG: {
                long _id = db.insert(RestaurantContract.RestaurantTagEntry.TABLE_NAME, null, values);
                if(_id > 0) {
                    insertedUri = RestaurantContract.RestaurantTagEntry.buildRestaurantTagUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int rowDeleted;

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if(context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int rowUpdated;

        rowUpdated = db.update(tableName, values, selection, selectionArgs);
        Context context = getContext();
        if(context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(RestaurantContract.CONTENT_AUTHORITY, RestaurantContract.PATH_RESTAURANTS, RESTAURANT);
        uriMatcher.addURI(RestaurantContract.CONTENT_AUTHORITY, RestaurantContract.PATH_RESTAURANTS_TAGS, RESTAURANT_TAG);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case RESTAURANT:
                return RestaurantContract.RestaurantEntry.TABLE_NAME;
            case RESTAURANT_TAG:
                return RestaurantContract.RestaurantTagEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}
