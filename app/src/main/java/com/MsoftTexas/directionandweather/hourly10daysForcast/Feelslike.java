
package com.example.kamlesh.directionandweather.hourly10daysForcast;


public class Feelslike {

    private String english;
    private String metric;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Feelslike() {
    }

    /**
     * 
     * @param metric
     * @param english
     */
    public Feelslike(String english, String metric) {
        super();
        this.english = english;
        this.metric = metric;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

}
