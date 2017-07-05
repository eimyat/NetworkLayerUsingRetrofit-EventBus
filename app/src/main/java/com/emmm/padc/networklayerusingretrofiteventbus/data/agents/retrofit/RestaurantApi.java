package com.emmm.padc.networklayerusingretrofiteventbus.data.agents.retrofit;

import com.emmm.padc.networklayerusingretrofiteventbus.data.responses.RestaurantListResponse;
import com.emmm.padc.networklayerusingretrofiteventbus.utils.RestaurantsConstants;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by EI on 6/21/2017.
 */

public interface RestaurantApi {

    @GET(RestaurantsConstants.API_GET_RESTAURANT_LIST)
    Call<RestaurantListResponse> loadRestaurants();
}
