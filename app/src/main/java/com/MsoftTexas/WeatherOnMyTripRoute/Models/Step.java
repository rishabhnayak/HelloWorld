
package com.MsoftTexas.WeatherOnMyTripRoute.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("distance")
    @Expose
    private Distance_ distance;
    @SerializedName("duration")
    @Expose
    private Duration_ duration;
    @SerializedName("end_location")
    @Expose
    private End_location_ end_location;
    @SerializedName("html_instructions")
    @Expose
    private String html_instructions;
    @SerializedName("polyline")
    @Expose
    private Polyline polyline;
    @SerializedName("start_location")
    @Expose
    private Start_location_ start_location;
    @SerializedName("travel_mode")
    @Expose
    private String travel_mode;
    @SerializedName("maneuver")
    @Expose
    private String maneuver;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Step() {
    }

    /**
     * 
     * @param html_instructions
     * @param duration
     * @param distance
     * @param end_location
     * @param polyline
     * @param start_location
     * @param maneuver
     * @param travel_mode
     */
    public Step(Distance_ distance, Duration_ duration, End_location_ end_location, String html_instructions, Polyline polyline, Start_location_ start_location, String travel_mode, String maneuver) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.end_location = end_location;
        this.html_instructions = html_instructions;
        this.polyline = polyline;
        this.start_location = start_location;
        this.travel_mode = travel_mode;
        this.maneuver = maneuver;
    }

    public Distance_ getDistance() {
        return distance;
    }

    public void setDistance(Distance_ distance) {
        this.distance = distance;
    }

    public Duration_ getDuration() {
        return duration;
    }

    public void setDuration(Duration_ duration) {
        this.duration = duration;
    }

    public End_location_ getEnd_location() {
        return end_location;
    }

    public void setEnd_location(End_location_ end_location) {
        this.end_location = end_location;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Start_location_ getStart_location() {
        return start_location;
    }

    public void setStart_location(Start_location_ start_location) {
        this.start_location = start_location;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

}
