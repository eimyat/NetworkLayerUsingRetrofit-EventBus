package com.emmm.padc.networklayerusingretrofiteventbus.events;

import com.emmm.padc.networklayerusingretrofiteventbus.data.vos.RestaurantVO;

import java.util.List;

/**
 * Created by EI on 6/23/2017.
 */

public class DataEvent {

    public static class RestaurantLoadedEvent {

        private List<RestaurantVO> restaurantList;

        public RestaurantLoadedEvent(List<RestaurantVO> restaurantList) {
            this.restaurantList = restaurantList;
        }

        public List<RestaurantVO> getRestaurantList() {
            return restaurantList;
        }
    }
}
