
package com.MsoftTexas.directionandweather.DirectionApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class End_location {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    /**
     * No args constructor for use in serialization
     * 
     */
    public End_location() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public End_location(Double lat, Double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}