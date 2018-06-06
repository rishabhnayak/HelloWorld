
package com.MsoftTexas.WeatherOnMyTripRoute.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Start_location_ {

    @SerializedName("lat")
    @Expose
    private Float lat;
    @SerializedName("lng")
    @Expose
    private Float lng;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Start_location_() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Start_location_(Float lat, Float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

}
