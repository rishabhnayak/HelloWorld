
package com.MsoftTexas.WeatherOnMyTripRoute.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Wlist implements Serializable
{

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("precipIntensity")
    @Expose
    private String precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private String precipProbability;
    @SerializedName("precipType")
    @Expose
    private String precipType;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    @SerializedName("apparentTemperature")
    @Expose
    private String apparentTemperature;
    @SerializedName("dewPoint")
    @Expose
    private String dewPoint;
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("windSpeed")
    @Expose
    private String windSpeed;
    @SerializedName("windBearing")
    @Expose
    private String windBearing;
    @SerializedName("cloudCover")
    @Expose
    private String cloudCover;
    private final static long serialVersionUID = -6936455797618950384L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Wlist() {
    }

    /**
     *
     * @param summary
     * @param icon
     * @param pressure
     * @param cloudCover
     * @param apparentTemperature
     * @param precipType
     * @param precipIntensity
     * @param temperature
     * @param dewPoint
     * @param time
     * @param windSpeed
     * @param humidity
     * @param windBearing
     * @param precipProbability
     */
    public Wlist(String time, String summary, String icon, String precipIntensity, String precipProbability, String precipType, String temperature, String apparentTemperature, String dewPoint, String humidity, String pressure, String windSpeed, String windBearing, String cloudCover) {
        super();
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(String precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(String precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(String apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(String windBearing) {
        this.windBearing = windBearing;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

}
