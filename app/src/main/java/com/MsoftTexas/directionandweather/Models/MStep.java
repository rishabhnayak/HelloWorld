package com.MsoftTexas.directionandweather.Models;

import java.util.List;

public class MStep {

    List<Wlist> wlist;
    String arrtime;
    String steplength;
    Step step;
    long aft_distance;


    public MStep() {
        super();
    }

    public MStep(Step step , List<Wlist> wlist, String arrtime, long aft_distance, String steplength) {
        this.step=step;
        this.wlist=wlist;
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

    public List<Wlist> getWlist() {
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

