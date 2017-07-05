package com.emmm.padc.networklayerusingretrofiteventbus.data.agents.retrofit;

import android.util.Log;

import com.emmm.padc.networklayerusingretrofiteventbus.data.agents.RestaurantDataAgent;
import com.emmm.padc.networklayerusingretrofiteventbus.data.responses.RestaurantListResponse;
import com.emmm.padc.networklayerusingretrofiteventbus.events.DataEvent;
import com.emmm.padc.networklayerusingretrofiteventbus.utils.CommonInstances;
import com.emmm.padc.networklayerusingretrofiteventbus.utils.RestaurantsConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by EI on 6/21/2017.
 */

public class RetrofitDataAgent implements RestaurantDataAgent {

    private static RetrofitDataAgent objInstance;

    private final RestaurantApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantsConstants.RESTAURANT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(RestaurantApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if(objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadRestaurants() {
        Call<RestaurantListResponse> loadRestaurantList = theApi.loadRestaurants();
        loadRestaurantList.enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {
                RestaurantListResponse restaurantListResponse = response.body();
                if(restaurantListResponse == null) {
                    Log.i(RetrofitDataAgent.class.getName(), response.body().getMessage());
                } else {
                    EventBus.getDefault().post(new DataEvent.RestaurantLoadedEvent(restaurantListResponse.getRestaurantList()));
                }
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {
                Log.i(RetrofitDataAgent.class.getName(), "onFailure!");
            }
        });
    }
}