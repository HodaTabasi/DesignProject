package com.smm.sapp.sproject;

public class PieEntry {
    float value;
    String label;

    public PieEntry(float value, String label) {
        this.value = value;
        this.label = label;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
