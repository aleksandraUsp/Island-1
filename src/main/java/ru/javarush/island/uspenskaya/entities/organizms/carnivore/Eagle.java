package ru.javarush.island.uspenskaya.entities.organizms.carnivore;

import ru.javarush.island.uspenskaya.entities.organizms.Organism;
import ru.javarush.island.uspenskaya.entities.organizms.herbivore.*;
import ru.javarush.island.uspenskaya.util.Setting;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Setting(mass=6, speed= 3,maxQuality=20,saturation=1, icon="U+1F98A")
public class Eagle extends Carnivore {
    private final String name;
    private final Map<Class<?>, Integer> ration = new HashMap<>();

    public Eagle(double mass, int speed, int maxQuality, double saturation, String icon) {
        super(mass, speed, maxQuality, saturation, icon);
        AtomicInteger number = new AtomicInteger(1);
        this.name = "Eagle"+ number.getAndIncrement();
    }

    @Override
    public Map<Class<?>, Integer> getRation() {
        ration.put(Wolf.class, 0);
        ration.put(Snake.class ,0);
        ration.put(Fox.class, 10);
        ration.put(Eagle.class, 0);
        ration.put(Horse.class, 0);
        ration.put(Deer.class, 0); // олень
        ration.put(Rabbit.class, 90);
        ration.put(Mouse.class, 90);
        ration.put(Goat.class, 0);
        ration.put(Sheep.class, 0);
        ration.put(Boar.class, 0); //кабан
        ration.put(Buffalo.class, 0); //буйвол
        ration.put(Duck.class, 80);
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
        return new Eagle(this.getMass(), this.getSpeed(), this.getMaxQuality(), this.getSaturation(), this.getIcon());
    }

    @Override
    public String toString() {
        return name + getIcon();
    }
}
