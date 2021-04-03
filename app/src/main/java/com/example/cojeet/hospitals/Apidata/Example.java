
package com.example.cojeet.hospitals.Apidata;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Example {

    @SerializedName("displayFieldName")
    @Expose
    private String displayFieldName;
    @SerializedName("fieldAliases")
    @Expose
    private FieldAliases fieldAliases;
    @SerializedName("geometryType")
    @Expose
    private String geometryType;
    @SerializedName("spatialReference")
    @Expose
    private SpatialReference spatialReference;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;
    @SerializedName("features")
    @Expose
    private List<Feature> features = null;

    public String getDisplayFieldName() {
        return displayFieldName;
    }

    public void setDisplayFieldName(String displayFieldName) {
        this.displayFieldName = displayFieldName;
    }

    public FieldAliases getFieldAliases() {
        return fieldAliases;
    }

    public void setFieldAliases(FieldAliases fieldAliases) {
        this.fieldAliases = fieldAliases;
    }

    public String getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    public void setSpatialReference(SpatialReference spatialReference) {
        this.spatialReference = spatialReference;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}
