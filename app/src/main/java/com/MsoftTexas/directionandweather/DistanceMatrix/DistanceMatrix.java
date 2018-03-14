package com.example.kamlesh.directionandweather.DistanceMatrix;

import java.util.List;


public class DistanceMatrix {

    private List<String> destination_addresses = null;
    private List<String> origin_addresses = null;
    private List<Row> rows = null;
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public DistanceMatrix() {
    }

    /**
     *
     * @param destination_addresses
     * @param status
     * @param origin_addresses
     * @param rows
     */
    public DistanceMatrix(List<String> destination_addresses, List<String> origin_addresses, List<Row> rows, String status) {
        super();
        this.destination_addresses = destination_addresses;
        this.origin_addresses = origin_addresses;
        this.rows = rows;
        this.status = status;
    }

    public List<String> getDestinationAddresses() {
        return destination_addresses;
    }

    public void setDestinationAddresses(List<String> destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public List<String> getOriginAddresses() {
        return origin_addresses;
    }

    public void setOriginAddresses(List<String> origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}




