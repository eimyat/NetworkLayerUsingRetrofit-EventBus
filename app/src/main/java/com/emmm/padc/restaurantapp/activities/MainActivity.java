package com.emmm.padc.restaurantapp.activities;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import com.emmm.padc.restaurantapp.R;
import com.emmm.padc.restaurantapp.adapters.RestaurantAdapter;
import com.emmm.padc.restaurantapp.data.agents.retrofit.RetrofitDataAgent;
import com.emmm.padc.restaurantapp.data.models.RestaurantModel;
import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;
import com.emmm.padc.restaurantapp.events.DataEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    @BindView(R.id.tvNoOfRestaurants)
    TextView textViewNoOfRestaurants;

    @BindView(R.id.rv_restaurants)
    RecyclerView rvRestaurants;

    private RestaurantAdapter mRestaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RetrofitDataAgent.getInstance().loadRestaurants();

        //List<RestaurantVO> restaurantList = RestaurantModel.getInstance().getRestaurantList();
        mRestaurantAdapter = new RestaurantAdapter(getApplicationContext());
        //mRestaurantAdapter.setNewData(restaurantList);
        rvRestaurants.setAdapter(mRestaurantAdapter);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadedRestaurant(DataEvent.RestaurantLoadedEvent event) {
        mRestaurantAdapter.setNewData(event.getRestaurantList());
        textViewNoOfRestaurants.setText(mRestaurantAdapter.getItemCount() + " restaurants deliver to you");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void failedLoadedRestaurant(DataEvent.FailedRestaurantLoadedEvent event) {
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
