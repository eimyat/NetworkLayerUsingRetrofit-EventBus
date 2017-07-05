package com.emmm.padc.restaurantapp.events;

import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;

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

    public static class FailedRestaurantLoadedEvent {

        private String message;

        public FailedRestaurantLoadedEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
