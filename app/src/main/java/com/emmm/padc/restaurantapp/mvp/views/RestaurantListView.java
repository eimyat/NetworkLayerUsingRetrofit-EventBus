package com.emmm.padc.restaurantapp.mvp.views;

import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;

import java.util.List;

/**
 * Created by EI on 7/11/2017.
 */

public interface RestaurantListView {

    void displayRestaurantList(List<RestaurantVO> restaurantList);
}
