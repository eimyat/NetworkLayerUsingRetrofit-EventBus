package com.emmm.padc.networklayerusingretrofiteventbus.views.holders;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.emmm.padc.networklayerusingretrofiteventbus.R;
import com.emmm.padc.networklayerusingretrofiteventbus.data.vos.RestaurantVO;
import com.emmm.padc.networklayerusingretrofiteventbus.utils.RestaurantsConstants;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by EI on 6/22/2017.
 */

public class RestaurantViewHolder extends BaseViewHolder<RestaurantVO> {

    @BindView(R.id.textViewAD)
    TextView tvAD;

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

        Log.i("ViewHolder", "Tags : " + tags);
    }

    private String getTags(String[] tags) {
        String stringTags = Arrays.toString(tags);
        //replace starting "[" and ending "]" and ","
        stringTags = stringTags.substring(1, stringTags.length()-1);
        return stringTags;
    }
}
