package ru.javarush.island.uspenskaya.entities.organizms.carnivore;

import ru.javarush.island.uspenskaya.entities.organizms.Animal;
import ru.javarush.island.uspenskaya.entities.organizms.Organism;

import java.util.Map;

public abstract class Carnivore extends Animal /*хищник*/ {



    public Carnivore(double mass, int speed, int maxQuality, double saturation, String icon) {
        super(mass, speed, maxQuality, saturation, icon);

    }

    @Override
    public Map<Class<?>, Integer> getRation() {
        return super.getRation();
    }

    @Override
    public Map<Integer, Class<?>> getProbabilityOfRation() {
        return super.getProbabilityOfRation();
    }

    @Override
    public double getMass() {
        return super.getMass();
    }

    @Override
    public void setMass(double mass) {
        super.setMass(mass);
    }

    @Override
    public int getMaxQuality() {
        return super.getMaxQuality();
    }

    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public double getSaturation() {
        return super.getSaturation();
    }

    @Override
    public String getIcon() {
        return super.getIcon();
    }

    @Override
    public Organism clone() {
        return super.clone();
    }
}






