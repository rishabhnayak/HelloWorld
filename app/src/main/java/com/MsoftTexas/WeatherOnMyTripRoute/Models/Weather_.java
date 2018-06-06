package com.MsoftTexas.WeatherOnMyTripRoute.Models;

public class Weather_ {

    public Long id;
    public String main;
    public String description;
    public String icon;

    /**
     * No args constructor for use in serialization
     *
     */
    public Weather_() {
    }

    /**
     *
     * @param id
     * @param icon
     * @param description
     * @param main
     */
    public Weather_(Long id, String main, String description, String icon) {
        super();
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

}