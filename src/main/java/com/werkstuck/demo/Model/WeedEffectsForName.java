package com.werkstuck.demo.Model;

public class WeedEffectsForName {
    private String[] positive;
    private String[] negative;
    private String[] medical;

    public WeedEffectsForName(){}

    public String[] getPositive() {
        return positive;
    }

    public void setPositive(String[] positive) {
        this.positive = positive;
    }

    public String[] getNegative() {
        return negative;
    }

    public void setNegative(String[] negative) {
        this.negative = negative;
    }

    public String[] getMedical() {
        return medical;
    }

    public void setMedical(String[] medical) {
        this.medical = medical;
    }
}
