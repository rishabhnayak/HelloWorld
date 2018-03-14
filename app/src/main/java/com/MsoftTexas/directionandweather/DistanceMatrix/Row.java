package com.example.kamlesh.directionandweather.DistanceMatrix;

import java.util.List;

public class Row {

    private List<Element> elements = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Row() {
    }

    /**
     *
     * @param elements
     */
    public Row(List<Element> elements) {
        super();
        this.elements = elements;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
