package com.emmm.padc.networklayerusingretrofiteventbus.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by EI on 6/21/2017.
 */

public class RestaurantVO {

    @SerializedName("title")
    private String title;

    @SerializedName("addr-short")
    private String addrShort;

    @SerializedName("image")
    private String image;

    @SerializedName("total-rating-count")
    private String totalRatingCount;

    @SerializedName("average-rating-value")
    private String averageRatingValue;

    @SerializedName("is-ad")
    private String isAd;

    @SerializedName("is-new")
    private String isNew;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("lead-time-in-min")
    private String deliverTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public void setAddrShort(String addrShort) {
        this.addrShort = addrShort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(String totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public String getAverageRatingValue() {
        return averageRatingValue;
    }

    public void setAverageRatingValue(String averageRatingValue) {
        this.averageRatingValue = averageRatingValue;
    }

    public String getIsAd() {
        return isAd;
    }

    public void setIsAd(String isAd) {
        this.isAd = isAd;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }
}
