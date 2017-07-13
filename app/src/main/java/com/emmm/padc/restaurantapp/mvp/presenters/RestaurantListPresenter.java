package com.emmm.padc.restaurantapp.mvp.presenters;

import android.content.Context;

import com.emmm.padc.restaurantapp.data.models.RestaurantModel;
import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;
import com.emmm.padc.restaurantapp.mvp.views.RestaurantListView;

import java.util.List;

/**
 * Created by EI on 7/11/2017.
 */

public class RestaurantListPresenter extends BasePresenter {

    RestaurantListView restaurantListView;

    public RestaurantListPresenter(RestaurantListView restaurantListView) {
        this.restaurantListView = restaurantListView;
    }

    public void loadRestaurantList() {
        RestaurantModel.getInstance().loadRestaurants();
    }

    public void displayRestaurantList(List<RestaurantVO> restaurantList) {
        restaurantListView.displayRestaurantList(restaurantList);
    }
}
