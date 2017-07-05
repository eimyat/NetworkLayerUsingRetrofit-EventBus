package com.emmm.padc.networklayerusingretrofiteventbus.data.responses;

import com.emmm.padc.networklayerusingretrofiteventbus.data.vos.RestaurantVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by EI on 6/21/2017.
 */

public class RestaurantListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("timestamp")
    private String timeStamp;

    @SerializedName("restaurants")
    private ArrayList<RestaurantVO> restaurantList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public ArrayList<RestaurantVO> getRestaurantList() {
        return restaurantList;
    }
}
