package com.emmm.padc.restaurantapp.data.models;

import com.emmm.padc.restaurantapp.data.agents.RestaurantDataAgent;
import com.emmm.padc.restaurantapp.data.agents.retrofit.RetrofitDataAgent;

/**
 * Created by EI on 7/3/2017.
 */

public class BaseModel {

    RestaurantDataAgent dataAgent;

    public BaseModel() {
        RetrofitDataAgent.getInstance();
    }
}
