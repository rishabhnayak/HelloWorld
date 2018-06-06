package com.MsoftTexas.WeatherOnMyTripRoute.Models;

public class MStep {


    String arrtime;
    String steplength;
    Step step;
    long aft_distance;
    private Wlist wlist;

    public MStep() {
        super();
    }

    public MStep(Step step ,Wlist wlist, String arrtime,long aft_distance, String steplength) {
        this.step=step;

        this.arrtime=arrtime;
        this.steplength=steplength;
        this.aft_distance=aft_distance;
    }




    public String getArrtime() {
        return arrtime;
    }

    public String getSteplength() {
        return steplength;
    }

    public void setSteplength(String steplength) {
        this.steplength = steplength;
    }

    public Wlist getWlist() {
        return wlist;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public long getAft_distance() {
        return aft_distance;
    }

    public void setAft_distance(long aft_distance) {
        this.aft_distance = aft_distance;
    }
}

