package com.emmm.padc.restaurantapp.data.models;

import com.emmm.padc.restaurantapp.data.agents.retrofit.RetrofitDataAgent;
import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;
import com.emmm.padc.restaurantapp.events.DataEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by EI on 7/3/2017.
 */

public class RestaurantModel extends BaseModel {

    private static RestaurantModel objInstance;

    private List<RestaurantVO> mRestaurantList;

    private RestaurantModel() {
        super();

//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
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

    public void notifyRestaurantsLoaded(List<RestaurantVO> restaurantList) {
        mRestaurantList = restaurantList;

        RestaurantVO.saveRestaurants(mRestaurantList);
    }

    public void setStoredData(List<RestaurantVO> restaurantList) {
        mRestaurantList = restaurantList;
    }

//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void receiveAttactionList(DataEvent.RestaurantLoadedEvent event) {
//        event.getRestaurantList();
//    }
}
