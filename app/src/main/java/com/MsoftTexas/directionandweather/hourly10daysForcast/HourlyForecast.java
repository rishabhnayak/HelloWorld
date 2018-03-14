
package com.example.kamlesh.directionandweather.hourly10daysForcast;


public class HourlyForecast {

    private fCTTIME FCTTIME;
    private Temp temp;
    private Dewpoint dewpoint;
    private String condition;
    private String icon;
    private String icon_url;
    private String fctcode;
    private String sky;
    private Wspd wspd;
    private Wdir wdir;
    private String wx;
    private String uvi;
    private String humidity;
    private Windchill windchill;
    private Heatindex heatindex;
    private Feelslike feelslike;
    private Qpf qpf;
    private Snow snow;
    private String pop;
    private Mslp mslp;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HourlyForecast() {
    }

    /**
     * 
     * @param uvi
     * @param mslp
     * @param icon
     * @param heatindex
     * @param condition
     * @param icon_url
     * @param wspd
     * @param pop
     * @param fctcode
     * @param feelslike
     * @param wdir
     * @param wx
     * @param qpf
     * @param humidity
     * @param sky
     * @param snow
     * @param dewpoint
     * @param temp
     * @param FCTTIME
     * @param windchill
     */
    public HourlyForecast(fCTTIME FCTTIME, Temp temp, Dewpoint dewpoint, String condition, String icon, String icon_url, String fctcode, String sky, Wspd wspd, Wdir wdir, String wx, String uvi, String humidity, Windchill windchill, Heatindex heatindex, Feelslike feelslike, Qpf qpf, Snow snow, String pop, Mslp mslp) {
        super();
        this.FCTTIME = FCTTIME;
        this.temp = temp;
        this.dewpoint = dewpoint;
        this.condition = condition;
        this.icon = icon;
        this.icon_url = icon_url;
        this.fctcode = fctcode;
        this.sky = sky;
        this.wspd = wspd;
        this.wdir = wdir;
        this.wx = wx;
        this.uvi = uvi;
        this.humidity = humidity;
        this.windchill = windchill;
        this.heatindex = heatindex;
        this.feelslike = feelslike;
        this.qpf = qpf;
        this.snow = snow;
        this.pop = pop;
        this.mslp = mslp;
    }

    public fCTTIME getFCTTIME() {
        return FCTTIME;
    }

    public void setFCTTIME(fCTTIME FCTTIME) {
        this.FCTTIME = FCTTIME;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Dewpoint getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(Dewpoint dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String geticon_url() {
        return icon_url;
    }

    public void seticon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getFctcode() {
        return fctcode;
    }

    public void setFctcode(String fctcode) {
        this.fctcode = fctcode;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public Wspd getWspd() {
        return wspd;
    }

    public void setWspd(Wspd wspd) {
        this.wspd = wspd;
    }

    public Wdir getWdir() {
        return wdir;
    }

    public void setWdir(Wdir wdir) {
        this.wdir = wdir;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getUvi() {
        return uvi;
    }

    public void setUvi(String uvi) {
        this.uvi = uvi;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Windchill getWindchill() {
        return windchill;
    }

    public void setWindchill(Windchill windchill) {
        this.windchill = windchill;
    }

    public Heatindex getHeatindex() {
        return heatindex;
    }

    public void setHeatindex(Heatindex heatindex) {
        this.heatindex = heatindex;
    }

    public Feelslike getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(Feelslike feelslike) {
        this.feelslike = feelslike;
    }

    public Qpf getQpf() {
        return qpf;
    }

    public void setQpf(Qpf qpf) {
        this.qpf = qpf;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public Mslp getMslp() {
        return mslp;
    }

    public void setMslp(Mslp mslp) {
        this.mslp = mslp;
    }

}
