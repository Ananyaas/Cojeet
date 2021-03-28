
package com.example.cojeet.Covidstats.covidstatsapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionDatum {

    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("totalInfected")
    @Expose
    private Integer totalInfected;
    @SerializedName("newInfected")
    @Expose
    private Integer newInfected;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("newRecovered")
    @Expose
    private Integer newRecovered;
    @SerializedName("deceased")
    @Expose
    private Integer deceased;
    @SerializedName("newDeceased")
    @Expose
    private Integer newDeceased;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getTotalInfected() {
        return totalInfected;
    }

    public void setTotalInfected(Integer totalInfected) {
        this.totalInfected = totalInfected;
    }

    public Integer getNewInfected() {
        return newInfected;
    }

    public void setNewInfected(Integer newInfected) {
        this.newInfected = newInfected;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(Integer newRecovered) {
        this.newRecovered = newRecovered;
    }

    public Integer getDeceased() {
        return deceased;
    }

    public void setDeceased(Integer deceased) {
        this.deceased = deceased;
    }

    public Integer getNewDeceased() {
        return newDeceased;
    }

    public void setNewDeceased(Integer newDeceased) {
        this.newDeceased = newDeceased;
    }

}
