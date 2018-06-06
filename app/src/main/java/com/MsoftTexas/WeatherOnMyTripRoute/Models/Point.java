
package com.MsoftTexas.WeatherOnMyTripRoute.Models;


public class Point {

    private double latitude;
    private double longitude;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Point() {
    }

    /**
     * 
     * @param longitude
     * @param latitude
     */
    public Point(double latitude, double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
