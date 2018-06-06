package com.MsoftTexas.WeatherOnMyTripRoute;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by kamlesh on 28-04-2018.
 */

public class bitmapfromstring {

    BitmapDescriptor icon=BitmapDescriptorFactory.fromResource(R.drawable.clear_day);
    public bitmapfromstring(String name) {
        switch (name){
            case "clear-day":icon = BitmapDescriptorFactory.fromResource(R.drawable.clear_day);
                break;
            case "cloudy":icon = BitmapDescriptorFactory.fromResource(R.drawable.cloudy);
                break;
            case "clear-night":icon = BitmapDescriptorFactory.fromResource(R.drawable.clear_night);
                break;
            case "fog":icon = BitmapDescriptorFactory.fromResource(R.drawable.fog);
                break;
            case "hail":icon = BitmapDescriptorFactory.fromResource(R.drawable.hail);
                break;
            case "partly-cloudy-day":icon = BitmapDescriptorFactory.fromResource(R.drawable.partly_cloudy_day);
                break;
            case "partly-cloudy-night":icon = BitmapDescriptorFactory.fromResource(R.drawable.partly_cloudy_night);
                break;
            case "rain":icon = BitmapDescriptorFactory.fromResource(R.drawable.rain);
                break;
            case "sleet":icon = BitmapDescriptorFactory.fromResource(R.drawable.sleet);
                break;
            case "snow":icon = BitmapDescriptorFactory.fromResource(R.drawable.snow);
                break;
            case "thunderstorm":icon = BitmapDescriptorFactory.fromResource(R.drawable.thunderstorm);
                break;
            case "tornado":icon = BitmapDescriptorFactory.fromResource(R.drawable.tornado);
                break;
            case "wind":icon = BitmapDescriptorFactory.fromResource(R.drawable.wind);
                break;
        }

    }

    public BitmapDescriptor getIcon() {
        return icon;
    }
}
