
package com.example.kamlesh.directionandweather.hourly10daysForcast;

import java.util.List;

public class _240hrsForcast {

    private Response response;
    private List<HourlyForecast> hourly_forecast = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public _240hrsForcast() {
    }


    public _240hrsForcast(Response response, List<HourlyForecast> hourly_forecast) {
        super();
        this.response = response;
        this.hourly_forecast = hourly_forecast;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<HourlyForecast> getHourlyForecast() {
        return hourly_forecast;
    }

    public void setHourlyForecast(List<HourlyForecast> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

}
