
package com.example.kamlesh.directionandweather.hourly10daysForcast;


public class Response {

    private String version;
    private String termsofService;
    private Features features;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param features
     * @param termsofService
     * @param version
     */
    public Response(String version, String termsofService, Features features) {
        super();
        this.version = version;
        this.termsofService = termsofService;
        this.features = features;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermsofService() {
        return termsofService;
    }

    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

}
