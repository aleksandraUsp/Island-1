package ru.javarush.island.uspenskaya.entities.organizms;

import ru.javarush.island.uspenskaya.entities.organizms.herbivore.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class Animal extends Organism {


    private final int speed;
    private final double saturation; // насыщение
    private final Map<Class<?>, Integer> ration = new HashMap<>();
    private final Map<Integer,Class<?>> probabilityOfRation=new TreeMap<>();



    public Animal(double mass, int speed, int maxQuality, double saturation, String icon) {
        super(mass, maxQuality, icon);
        this.speed = speed;
        this.saturation = saturation;
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
    public String getIcon() {
        return super.getIcon();
    }

    public int getSpeed() {
        return speed;
    }

    public double getSaturation() {
        return saturation;
    }

    public Map<Class<?>, Integer> getRation() {
        ration.put(Horse.class, 0);
        ration.put(Deer.class, 0); // олень
        ration.put(Rabbit.class, 0);
        ration.put(Mouse.class, 0);
        ration.put(Goat.class, 0);
        ration.put(Sheep.class, 0);
        ration.put(Boar.class, 0); //кабан
        ration.put(Buffalo.class, 0); //буйвол
        ration.put(Duck.class, 0);
        ration.put(Caterpillar.class, 0); //гусеница
        ration.put(Plant.class, 100);
        return ration;
    }

    public Map<Integer,Class<?>> getProbabilityOfRation() {
        probabilityOfRation.put( 0, Horse.class);
        probabilityOfRation.put(100,Plant.class);
        return probabilityOfRation;
    }

    @Override
    public Organism clone() {
        return super.clone();
    }
}


