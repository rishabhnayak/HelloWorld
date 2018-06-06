package com.MsoftTexas.WeatherOnMyTripRoute.Models;

public class Main {

    public Float temp;
    public Float pressure;
    public Long humidity;
    public Float tempMin;
    public Float tempMax;
    public Float seaLevel;
    public Float grndLevel;

    /**
     * No args constructor for use in serialization
     *
     */
    public Main() {
    }

    /**
     *
     * @param seaLevel
     * @param humidity
     * @param pressure
     * @param grndLevel
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(Float temp, Float pressure, Long humidity, Float tempMin, Float tempMax, Float seaLevel, Float grndLevel) {
        super();
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.seaLevel = seaLevel;
        this.grndLevel = grndLevel;
    }

}