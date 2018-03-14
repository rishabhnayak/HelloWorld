
package com.example.kamlesh.directionandweather.hourly10daysForcast;


public class fCTTIME {

    private String hour;
    private String hourPadded;
    private String min;
    private String minUnpadded;
    private String sec;
    private String year;
    private String mon;
    private String monPadded;
    private String monAbbrev;
    private String mday;
    private String mdayPadded;
    private String yday;
    private String isdst;
    private String epoch;
    private String pretty;
    private String civil;
    private String monthName;
    private String monthNameAbbrev;
    private String weekdayName;
    private String weekdayNameNight;
    private String weekdayNameAbbrev;
    private String weekdayNameUnlang;
    private String weekdayNameNightUnlang;
    private String ampm;
    private String tz;
    private String age;
    private String uTCDATE;

    /**
     * No args constructor for use in serialization
     * 
     */
    public fCTTIME() {
    }

    /**
     * 
     * @param weekdayNameNightUnlang
     * @param mdayPadded
     * @param weekdayNameAbbrev
     * @param pretty
     * @param minUnpadded
     * @param monthNameAbbrev
     * @param ampm
     * @param weekdayNameNight
     * @param age
     * @param civil
     * @param year
     * @param monPadded
     * @param min
     * @param monAbbrev
     * @param epoch
     * @param mday
     * @param mon
     * @param hourPadded
     * @param hour
     * @param weekdayName
     * @param isdst
     * @param tz
     * @param weekdayNameUnlang
     * @param yday
     * @param sec
     * @param uTCDATE
     * @param monthName
     */
    public fCTTIME(String hour, String hourPadded, String min, String minUnpadded, String sec, String year, String mon, String monPadded, String monAbbrev, String mday, String mdayPadded, String yday, String isdst, String epoch, String pretty, String civil, String monthName, String monthNameAbbrev, String weekdayName, String weekdayNameNight, String weekdayNameAbbrev, String weekdayNameUnlang, String weekdayNameNightUnlang, String ampm, String tz, String age, String uTCDATE) {
        super();
        this.hour = hour;
        this.hourPadded = hourPadded;
        this.min = min;
        this.minUnpadded = minUnpadded;
        this.sec = sec;
        this.year = year;
        this.mon = mon;
        this.monPadded = monPadded;
        this.monAbbrev = monAbbrev;
        this.mday = mday;
        this.mdayPadded = mdayPadded;
        this.yday = yday;
        this.isdst = isdst;
        this.epoch = epoch;
        this.pretty = pretty;
        this.civil = civil;
        this.monthName = monthName;
        this.monthNameAbbrev = monthNameAbbrev;
        this.weekdayName = weekdayName;
        this.weekdayNameNight = weekdayNameNight;
        this.weekdayNameAbbrev = weekdayNameAbbrev;
        this.weekdayNameUnlang = weekdayNameUnlang;
        this.weekdayNameNightUnlang = weekdayNameNightUnlang;
        this.ampm = ampm;
        this.tz = tz;
        this.age = age;
        this.uTCDATE = uTCDATE;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getHourPadded() {
        return hourPadded;
    }

    public void setHourPadded(String hourPadded) {
        this.hourPadded = hourPadded;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMinUnpadded() {
        return minUnpadded;
    }

    public void setMinUnpadded(String minUnpadded) {
        this.minUnpadded = minUnpadded;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getMonPadded() {
        return monPadded;
    }

    public void setMonPadded(String monPadded) {
        this.monPadded = monPadded;
    }

    public String getMonAbbrev() {
        return monAbbrev;
    }

    public void setMonAbbrev(String monAbbrev) {
        this.monAbbrev = monAbbrev;
    }

    public String getMday() {
        return mday;
    }

    public void setMday(String mday) {
        this.mday = mday;
    }

    public String getMdayPadded() {
        return mdayPadded;
    }

    public void setMdayPadded(String mdayPadded) {
        this.mdayPadded = mdayPadded;
    }

    public String getYday() {
        return yday;
    }

    public void setYday(String yday) {
        this.yday = yday;
    }

    public String getIsdst() {
        return isdst;
    }

    public void setIsdst(String isdst) {
        this.isdst = isdst;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getPretty() {
        return pretty;
    }

    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    public String getCivil() {
        return civil;
    }

    public void setCivil(String civil) {
        this.civil = civil;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getMonthNameAbbrev() {
        return monthNameAbbrev;
    }

    public void setMonthNameAbbrev(String monthNameAbbrev) {
        this.monthNameAbbrev = monthNameAbbrev;
    }

    public String getWeekdayName() {
        return weekdayName;
    }

    public void setWeekdayName(String weekdayName) {
        this.weekdayName = weekdayName;
    }

    public String getWeekdayNameNight() {
        return weekdayNameNight;
    }

    public void setWeekdayNameNight(String weekdayNameNight) {
        this.weekdayNameNight = weekdayNameNight;
    }

    public String getWeekdayNameAbbrev() {
        return weekdayNameAbbrev;
    }

    public void setWeekdayNameAbbrev(String weekdayNameAbbrev) {
        this.weekdayNameAbbrev = weekdayNameAbbrev;
    }

    public String getWeekdayNameUnlang() {
        return weekdayNameUnlang;
    }

    public void setWeekdayNameUnlang(String weekdayNameUnlang) {
        this.weekdayNameUnlang = weekdayNameUnlang;
    }

    public String getWeekdayNameNightUnlang() {
        return weekdayNameNightUnlang;
    }

    public void setWeekdayNameNightUnlang(String weekdayNameNightUnlang) {
        this.weekdayNameNightUnlang = weekdayNameNightUnlang;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUTCDATE() {
        return uTCDATE;
    }

    public void setUTCDATE(String uTCDATE) {
        this.uTCDATE = uTCDATE;
    }

}
