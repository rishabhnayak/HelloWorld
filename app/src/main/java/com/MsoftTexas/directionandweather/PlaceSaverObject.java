package com.MsoftTexas.directionandweather;

import java.util.ArrayList;
import com.google.android.gms.location.places.Place;
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
