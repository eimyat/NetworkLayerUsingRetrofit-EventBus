package com.emmm.padc.restaurantapp.views.holders;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emmm.padc.restaurantapp.R;
import com.emmm.padc.restaurantapp.data.vos.RestaurantVO;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by EI on 6/22/2017.
 */

public class RestaurantViewHolder extends BaseViewHolder<RestaurantVO> {

    @BindView(R.id.textViewAD)
    TextView tvAD;

    @BindView(R.id.textViewNew)
    TextView tvNew;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.textViewRatingCount)
    TextView tvRatingCount;

    @BindView(R.id.textViewTitle)
    TextView tvTitle;

    @BindView(R.id.textViewTags)
    TextView tvTags;

    @BindView(R.id.textViewDeliverTime)
    TextView tvDeliverTime;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RestaurantVO data) {

        Glide.with(imageView.getContext())
                .load(R.mipmap.ic_launcher_round)
                .into(imageView);

        if(data.getIsAd().equalsIgnoreCase("true"))
            tvAD.setVisibility(View.VISIBLE);
        else if(data.getIsAd().equalsIgnoreCase("false"))
            tvAD.setVisibility(View.GONE);

        if(data.getIsNew().equalsIgnoreCase("true"))
            tvNew.setVisibility(View.VISIBLE);
        else if(data.getIsNew().equalsIgnoreCase("false"))
            tvNew.setVisibility(View.GONE);

        ratingBar.setRating(Float.parseFloat(data.getAverageRatingValue()));

        tvRatingCount.setText("(" + data.getTotalRatingCount() + ")");

        if(data.getAddrShort() == null) {
            tvTitle.setText(data.getTitle());
        } else {
            tvTitle.setText(data.getTitle() + " (" + data.getAddrShort() + ")");
        }

        tvDeliverTime.setText("delivers in " + data.getDeliverTime() + " min.");

        String tags = getTags(data.getTags());
        tvTags.setText(tags);
    }

    private String getTags(String[] tags) {
        String stringTags = Arrays.toString(tags);
        //replace starting "[" and ending "]" and ","
        stringTags = stringTags.substring(1, stringTags.length()-1);
        return stringTags;
    }
}
