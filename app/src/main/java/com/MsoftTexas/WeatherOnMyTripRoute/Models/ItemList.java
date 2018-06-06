
package com.MsoftTexas.WeatherOnMyTripRoute.Models;

import java.util.List;

public class ItemList {

    private Point point;
    private List<Wlist> wlist = null;
    private String arrtime;
    private String distance;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemList() {
    }

    /**
     * 
     * @param point
     * @param distance
     * @param arrtime
     * @param wlist
     */
    public ItemList(Point point, List<Wlist> wlist, String arrtime, String distance) {
        super();
        this.point = point;
        this.wlist = wlist;
        this.arrtime = arrtime;
        this.distance = distance;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public List<Wlist> getWlist() {
        return wlist;
    }

    public void setWlist(List<Wlist> wlist) {
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

}
