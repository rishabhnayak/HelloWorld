package com.MsoftTexas.WeatherOnMyTripRoute.Models;

public class Wind {

    public Float speed;
    public Float deg;

    /**
     * No args constructor for use in serialization
     *
     */
    public Wind() {
    }

    /**
     *
     * @param speed
     * @param deg
     */
    public Wind(Float speed, Float deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

}