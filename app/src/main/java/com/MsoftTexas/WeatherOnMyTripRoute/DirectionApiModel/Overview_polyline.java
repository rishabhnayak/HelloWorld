
package com.MsoftTexas.WeatherOnMyTripRoute.DirectionApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Overview_polyline {

    @SerializedName("points")
    @Expose
    private String points;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Overview_polyline() {
    }

    /**
     * 
     * @param points
     */
    public Overview_polyline(String points) {
        super();
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
