package ru.javarush.island.uspenskaya.entities.organizms;

import ru.javarush.island.uspenskaya.exeption.IslandException;


public abstract class Organism implements Cloneable {
    private double mass;
    private final int maxQuality;
    private final String icon;

public Organism(double mass, int maxQuality, String icon) {
        this.mass = mass;
        this.maxQuality = maxQuality;
        this.icon = icon;
    }


    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getMaxQuality() {
        return maxQuality;
    }

    public String getIcon() {
        return icon;
    }

    public Organism clone()  {
        try {
            return (Organism)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IslandException("Not cloned"+ e);
        }
    }
}

