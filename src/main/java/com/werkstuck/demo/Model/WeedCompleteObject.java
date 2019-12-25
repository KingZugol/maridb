package com.werkstuck.demo.Model;

public class WeedCompleteObject extends Weed {
    private String desc;
    private WeedEffectsForName effects;
    private String[] flavors;

    public WeedCompleteObject() {}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public WeedEffectsForName getEffects() {
        return effects;
    }

    public void setEffects(WeedEffectsForName effects) {
        this.effects = effects;
    }

    public String[] getFlavors() {
        return flavors;
    }

    public void setFlavors(String[] flavors) {
        this.flavors = flavors;
    }
}
