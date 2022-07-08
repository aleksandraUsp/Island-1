package ru.javarush.island.uspenskaya.entities.organizms.carnivore;

import ru.javarush.island.uspenskaya.entities.organizms.Organism;
import ru.javarush.island.uspenskaya.entities.organizms.herbivore.*;
import ru.javarush.island.uspenskaya.util.Setting;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Setting(mass=15, speed= 1,maxQuality=30,saturation=3, icon="\uD83D\uDC0D")
public class Snake extends Carnivore {
    private final String name;
    private final Map<Class<?>, Integer> ration = new HashMap<>();

    public Snake(double mass, int speed, int maxQuality, double saturation, String icon) {
        super(mass, speed, maxQuality, saturation, icon);
        AtomicInteger number = new AtomicInteger(1);
        this.name = "Wolf"+ number.getAndIncrement();
    }

    @Override
    public Map<Class<?>, Integer> getRation() {
        ration.put(Wolf.class, 0);
        ration.put(Snake.class ,15);
        ration.put(Fox.class, 0);
        ration.put(Eagle.class, 0);
        ration.put(Horse.class, 0);
        ration.put(Deer.class, 0); // олень
        ration.put(Rabbit.class, 20);
        ration.put(Mouse.class, 40);
        ration.put(Goat.class, 0);
        ration.put(Sheep.class, 0);
        ration.put(Boar.class, 0); //кабан
        ration.put(Buffalo.class, 0); //буйвол
        ration.put(Duck.class, 10);
        ration.put(Caterpillar.class, 0); //гусеница
        return ration;
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
        return new Snake(this.getMass(), this.getSpeed(), this.getMaxQuality(), this.getSaturation(), this.getIcon());
    }

    public String toString() {
        return name + getIcon();
    }
}

