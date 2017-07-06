package com.emmm.padc.restaurantapp.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.emmm.padc.restaurantapp.RestaurantApp;
import com.emmm.padc.restaurantapp.data.persistence.RestaurantContract;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EI on 6/21/2017.
 */

public class RestaurantVO {

    @SerializedName("title")
    private String title;

    @SerializedName("addr-short")
    private String addrShort;

    @SerializedName("image")
    private String image;

    @SerializedName("total-rating-count")
    private String totalRatingCount;

    @SerializedName("average-rating-value")
    private String averageRatingValue;

    @SerializedName("is-ad")
    private String isAd;

    @SerializedName("is-new")
    private String isNew;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("lead-time-in-min")
    private String deliverTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public void setAddrShort(String addrShort) {
        this.addrShort = addrShort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(String totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public String getAverageRatingValue() {
        return averageRatingValue;
    }

    public void setAverageRatingValue(String averageRatingValue) {
        this.averageRatingValue = averageRatingValue;
    }

    public String getIsAd() {
        return isAd;
    }

    public void setIsAd(String isAd) {
        this.isAd = isAd;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public static void saveRestaurants(List<RestaurantVO> restaurantList) {
        ContentValues[] restaurantCVs = new ContentValues[restaurantList.size()];
        for (int index = 0; index < restaurantList.size(); index++) {
            RestaurantVO restaurant = restaurantList.get(index);
            restaurantCVs[index] = restaurant.parseToContentValues();

            saveRestaurantTags(restaurant.getTitle(), restaurant.getTags());
        }
        Context context = RestaurantApp.getContext();
        int insertedCount = context.getContentResolver().bulkInsert(RestaurantContract.RestaurantEntry.CONTENT_URI, restaurantCVs);
        //Log.d(RestaurantApp.TAG, "inserted count, restaurants : " + insertedCount);
    }

    private static void saveRestaurantTags(String title, String[] tags) {
        ContentValues[] restaurantTagCVs = new ContentValues[tags.length];
        for (int index = 0; index < tags.length; index++) {
            String tag = tags[index];

            ContentValues cv = new ContentValues();
            cv.put(RestaurantContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE, title);
            cv.put(RestaurantContract.RestaurantTagEntry.COLUMN_TAG, tag);

            restaurantTagCVs[index] = cv;
        }
        Context context = RestaurantApp.getContext();
        int insertedCount = context.getContentResolver().bulkInsert(RestaurantContract.RestaurantTagEntry.CONTENT_URI, restaurantTagCVs);
        //Log.d(RestaurantApp.TAG, "inserted count, restaurant tags : " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_TITLE, title);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_ADD_SHORT, addrShort);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IMAGE, image);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT, totalRatingCount);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE, averageRatingValue);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IS_AD, isAd);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IS_NEW, isNew);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN, deliverTime);
        return cv;
    }

    public static RestaurantVO parseFromCursor (Cursor data) {
        RestaurantVO restaurant = new RestaurantVO();
        restaurant.title = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_TITLE));
        restaurant.addrShort = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_ADD_SHORT));
        restaurant.image = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IMAGE));
        restaurant.averageRatingValue = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE));
        restaurant.totalRatingCount = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT));
        restaurant.isAd = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IS_AD));
        restaurant.isNew = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IS_NEW));
        restaurant.deliverTime = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN));
        return restaurant;
    }

    public static String[] loadRestaurantTagsByTitle(String title) {
        Context context = RestaurantApp.getContext();
        ArrayList<String> tags = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(
                RestaurantContract.RestaurantTagEntry.buildRestaurantTagUriWithTitle(title),
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                tags.add(cursor.getString(cursor.getColumnIndex(RestaurantContract.RestaurantTagEntry.COLUMN_TAG)));
            } while (cursor.moveToNext());
        }
        String[] tagArray = new String[tags.size()];
        tags.toArray(tagArray);
        return tagArray;
    }
}
