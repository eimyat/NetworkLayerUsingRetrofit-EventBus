package com.emmm.padc.networklayerusingretrofiteventbus.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.emmm.padc.networklayerusingretrofiteventbus.R;
import com.emmm.padc.networklayerusingretrofiteventbus.data.vos.RestaurantVO;
import com.emmm.padc.networklayerusingretrofiteventbus.views.holders.RestaurantViewHolder;

/**
 * Created by EI on 6/22/2017.
 */

public class RestaurantAdapter extends BaseRecyclerAdapter<RestaurantViewHolder, RestaurantVO> {

    public RestaurantAdapter(Context context) {
        super(context);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_restarurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
