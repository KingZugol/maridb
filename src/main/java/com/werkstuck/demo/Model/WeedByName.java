package com.werkstuck.demo.Model;

public class WeedByName extends Weed {
    private String description;
    private WeedEffects effects;
    private String[] flavors;

    public WeedByName(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeedEffects getEffects() {
        return effects;
    }

    public void setEffects(WeedEffects effects) {
        this.effects = effects;
    }

    public String[] getFlavors() {
        return flavors;
    }

    public void setFlavors(String[] flavors) {
        this.flavors = flavors;
    }
}
