package com.emmm.padc.restaurantapp.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.emmm.padc.restaurantapp.R;
import com.emmm.padc.restaurantapp.RestaurantApp;
import com.emmm.padc.restaurantapp.adapters.RestaurantAdapter;
import com.emmm.padc.restaurantapp.data.persistence.RestaurantContract;
import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;
import com.emmm.padc.restaurantapp.events.DataEvent;
import com.emmm.padc.restaurantapp.mvp.presenters.RestaurantListPresenter;
import com.emmm.padc.restaurantapp.mvp.views.RestaurantListView;
import com.emmm.padc.restaurantapp.utils.RestaurantsConstants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by EI on 7/12/2017.
 */

public class MVPMainActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>, RestaurantListView {

    @BindView(R.id.tvNoOfRestaurants)
    TextView textViewNoOfRestaurants;

    @BindView(R.id.rv_restaurants)
    RecyclerView rvRestaurants;

    private RestaurantListPresenter restaurantListPresenter;
    private RestaurantAdapter mRestaurantAdapter;
    private List<RestaurantVO> mRestaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        restaurantListPresenter = new RestaurantListPresenter(this);

        mRestaurantAdapter = new RestaurantAdapter(getApplicationContext());
        rvRestaurants.setAdapter(mRestaurantAdapter);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        this.getSupportLoaderManager().initLoader(RestaurantsConstants.RESTAURANT_LIST_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                RestaurantContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                RestaurantContract.RestaurantEntry.COLUMN_TITLE);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor data) {
        List<RestaurantVO> restaurantList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                RestaurantVO restaurant = RestaurantVO.parseFromCursor(data);
                restaurant.setTags(RestaurantVO.loadRestaurantTagsByTitle(restaurant.getTitle()));
                restaurantList.add(restaurant);
            } while (data.moveToNext());
        }

        if (mRestaurantList.size() != restaurantList.size()) {
            mRestaurantList = restaurantList;
            restaurantListPresenter.displayRestaurantList(restaurantList);
        }

        if (restaurantList.size() == 0) {
            restaurantListPresenter.loadRestaurantList();
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public void displayRestaurantList(List<RestaurantVO> restaurantList) {
        mRestaurantAdapter.setNewData(restaurantList);
        textViewNoOfRestaurants.setText(mRestaurantAdapter.getItemCount() + " restaurants deliver to you");
    }
}