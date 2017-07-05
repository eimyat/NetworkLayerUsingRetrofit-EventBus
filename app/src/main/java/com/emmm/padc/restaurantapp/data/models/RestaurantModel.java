package com.emmm.padc.restaurantapp.data.models;

import com.emmm.padc.restaurantapp.data.agents.retrofit.RetrofitDataAgent;
import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;

import java.util.List;

/**
 * Created by EI on 7/3/2017.
 */

public class RestaurantModel extends BaseModel {

    private static RestaurantModel objInstance;

    private List<RestaurantVO> mRestaurantList;

    private RestaurantModel() {
        super();
    }

    public static RestaurantModel getInstance() {
        if(objInstance == null) {
            objInstance = new RestaurantModel();
        }
        return objInstance;
    }

    public List<RestaurantVO> getRestaurantList() {
        return mRestaurantList;
    }

    public void loadRestaurants() {
        RetrofitDataAgent.getInstance().loadRestaurants();
        //dataAgent.loadRestaurants();
    }
}
