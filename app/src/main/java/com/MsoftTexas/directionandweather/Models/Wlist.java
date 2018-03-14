
package com.MsoftTexas.directionandweather.Models;


public class Wlist {

    private String imgurl;
    private String wtime;
    private String wx;
    private String temp;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wlist() {
    }

    /**
     * 
     * @param wx
     * @param wtime
     * @param temp
     * @param imgurl
     */
    public Wlist(String imgurl, String wtime, String wx, String temp) {
        super();
        this.imgurl = imgurl;
        this.wtime = wtime;
        this.wx = wx;
        this.temp = temp;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getWtime() {
        return wtime;
    }

    public void setWtime(String wtime) {
        this.wtime = wtime;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

}
