package com.MsoftTexas.WeatherOnMyTripRoute;

import java.util.ArrayList;

import com.MsoftTexas.WeatherOnMyTripRoute.Models.MPlace;

/**
 * Created by sahu on 6/8/2017.
 */

public class PlaceSaverObject {
    ArrayList<MPlace> list;
    public PlaceSaverObject(ArrayList<MPlace> list) {
       this.list=list;
    }

    public ArrayList<MPlace> getList() {
        return list;
    }
}
