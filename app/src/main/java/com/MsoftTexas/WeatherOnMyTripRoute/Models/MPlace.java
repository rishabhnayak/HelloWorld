package com.MsoftTexas.WeatherOnMyTripRoute.Models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by kamlesh on 13-03-2018.
 */

public class MPlace {
    String Name;
    String Adress;
    LatLng latLng;
    String id;

    public MPlace(String id,String Name, String Adress, LatLng latLng) {
        this.Name=Name;
        this.Adress=Adress;
        this.latLng=latLng;
        this.id=id;
    }

    public MPlace() {
        super();
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getAdress() {
        return Adress;
    }

    public String getName() {
        return Name;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
