
package com.MsoftTexas.WeatherOnMyTripRoute.Models;

public class Item {

    private Point point;
    private Wlist wlist = null;
    private String arrtime;
    private String distance;
    private String lname;
    private Step step;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param point
     * @param distance
     * @param arrtime
     * @param wlist
     */
    public Item(Point point, Wlist wlist, String arrtime, String distance, String lname) {
        super();
        this.point = point;
        this.wlist = wlist;
        this.arrtime = arrtime;
        this.distance = distance;
        this.lname=lname;
    }

    public Item(Point point, Wlist wlist, String arrtime, String distance, Step step) {
        super();
        this.point = point;
        this.wlist = wlist;
        this.arrtime = arrtime;
        this.distance = distance;
        this.step=step;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Wlist getWlist() {
        return wlist;
    }

    public void setWlist(Wlist wlist) {
        this.wlist = wlist;
    }

    public String getArrtime() {
        return arrtime;
    }

    public void setArrtime(String arrtime) {
        this.arrtime = arrtime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
