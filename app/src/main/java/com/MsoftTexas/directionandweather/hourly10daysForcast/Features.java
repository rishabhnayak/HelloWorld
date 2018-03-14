
package com.example.kamlesh.directionandweather.hourly10daysForcast;


public class Features {

    private Long hourly10day;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Features() {
    }

    /**
     * 
     * @param hourly10day
     */
    public Features(Long hourly10day) {
        super();
        this.hourly10day = hourly10day;
    }

    public Long getHourly10day() {
        return hourly10day;
    }

    public void setHourly10day(Long hourly10day) {
        this.hourly10day = hourly10day;
    }

}
