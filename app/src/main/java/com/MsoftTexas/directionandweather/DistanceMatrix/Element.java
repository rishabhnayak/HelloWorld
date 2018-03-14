package com.example.kamlesh.directionandweather.DistanceMatrix;

public class Element {

    private Distance distance;
    private Duration duration;
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public Element() {
    }

    /**
     *
     * @param duration
     * @param distance
     * @param status
     */
    public Element(Distance distance, Duration duration, String status) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.status = status;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
