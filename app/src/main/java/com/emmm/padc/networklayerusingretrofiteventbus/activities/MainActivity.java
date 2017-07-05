package com.emmm.padc.networklayerusingretrofiteventbus.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import com.emmm.padc.networklayerusingretrofiteventbus.R;
import com.emmm.padc.networklayerusingretrofiteventbus.adapters.RestaurantAdapter;
import com.emmm.padc.networklayerusingretrofiteventbus.data.agents.retrofit.RetrofitDataAgent;
import com.emmm.padc.networklayerusingretrofiteventbus.events.DataEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

        mRestaurantAdapter = new RestaurantAdapter(getApplicationContext());
        rvRestaurants.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvRestaurants.setAdapter(mRestaurantAdapter);

        RetrofitDataAgent.getInstance().loadRestaurants();
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
}
