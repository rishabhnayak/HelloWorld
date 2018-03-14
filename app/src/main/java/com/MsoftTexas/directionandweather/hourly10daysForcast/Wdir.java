
package com.example.kamlesh.directionandweather.hourly10daysForcast;


public class Wdir {

    private String dir;
    private String degrees;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wdir() {
    }

    /**
     * 
     * @param dir
     * @param degrees
     */
    public Wdir(String dir, String degrees) {
        super();
        this.dir = dir;
        this.degrees = degrees;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

}
