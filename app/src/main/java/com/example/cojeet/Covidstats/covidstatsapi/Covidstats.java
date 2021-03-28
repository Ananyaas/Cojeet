
package com.example.cojeet.Covidstats.covidstatsapi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Covidstats {

    @SerializedName("activeCases")
    @Expose
    private Integer activeCases;
    @SerializedName("activeCasesNew")
    @Expose
    private Integer activeCasesNew;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("recoveredNew")
    @Expose
    private Integer recoveredNew;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("deathsNew")
    @Expose
    private Integer deathsNew;
    @SerializedName("previousDayTests")
    @Expose
    private Integer previousDayTests;
    @SerializedName("totalCases")
    @Expose
    private Integer totalCases;
    @SerializedName("sourceUrl")
    @Expose
    private String sourceUrl;
    @SerializedName("lastUpdatedAtApify")
    @Expose
    private String lastUpdatedAtApify;
    @SerializedName("readMe")
    @Expose
    private String readMe;
    @SerializedName("regionData")
    @Expose
    private List<RegionDatum> regionData = null;

    public Integer getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(Integer activeCases) {
        this.activeCases = activeCases;
    }

    public Integer getActiveCasesNew() {
        return activeCasesNew;
    }

    public void setActiveCasesNew(Integer activeCasesNew) {
        this.activeCasesNew = activeCasesNew;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getRecoveredNew() {
        return recoveredNew;
    }

    public void setRecoveredNew(Integer recoveredNew) {
        this.recoveredNew = recoveredNew;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getDeathsNew() {
        return deathsNew;
    }

    public void setDeathsNew(Integer deathsNew) {
        this.deathsNew = deathsNew;
    }

    public Integer getPreviousDayTests() {
        return previousDayTests;
    }

    public void setPreviousDayTests(Integer previousDayTests) {
        this.previousDayTests = previousDayTests;
    }

    public Integer getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getLastUpdatedAtApify() {
        return lastUpdatedAtApify;
    }

    public void setLastUpdatedAtApify(String lastUpdatedAtApify) {
        this.lastUpdatedAtApify = lastUpdatedAtApify;
    }

    public String getReadMe() {
        return readMe;
    }

    public void setReadMe(String readMe) {
        this.readMe = readMe;
    }

    public List<RegionDatum> getRegionData() {
        return regionData;
    }

    public void setRegionData(List<RegionDatum> regionData) {
        this.regionData = regionData;
    }

}
