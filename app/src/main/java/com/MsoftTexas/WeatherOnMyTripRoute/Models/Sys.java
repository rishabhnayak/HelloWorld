package com.MsoftTexas.WeatherOnMyTripRoute.Models;


public class Sys {

    public Float message;
    public String country;
    public Long sunrise;
    public Long sunset;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sys() {
    }

    /**
     *
     * @param message
     * @param sunset
     * @param sunrise
     * @param country
     */
    public Sys(Float message, String country, Long sunrise, Long sunset) {
        super();
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

}