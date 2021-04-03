
package com.example.cojeet.hospitals.Apidata;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Feature {

    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

}
