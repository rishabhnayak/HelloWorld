package com.example.kamlesh.directionandweather.DistanceMatrix;

public class Duration {

    private String text;
    private Long value;

    /**
     * No args constructor for use in serialization
     *
     */
    public Duration() {
    }

    /**
     *
     * @param text
     * @param value
     */
    public Duration(String text, Long value) {
        super();
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
