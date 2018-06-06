package com.MsoftTexas.WeatherOnMyTripRoute.Models;

import java.util.List;

public class Weather {

    public Coord coord;
    public List<Weather_> weather = null;
    public String base;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public Long dt;
    public Sys sys;
    public Long id;
    public String name;
    public Long cod;

    /**
     * No args constructor for use in serialization
     *
     */
    public Weather() {
    }

    /**
     *
     * @param id
     * @param dt
     * @param clouds
     * @param coord
     * @param wind
     * @param cod
     * @param sys
     * @param name
     * @param base
     * @param weather
     * @param main
     */
    public Weather(Coord coord, List<Weather_> weather, String base, Main main, Wind wind, Clouds clouds, Long dt, Sys sys, Long id, String name, Long cod) {
        super();
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

}