package ru.javarush.island.uspenskaya.entities.organizms.herbivore;

import ru.javarush.island.uspenskaya.entities.organizms.Organism;
import ru.javarush.island.uspenskaya.entities.organizms.Plant;
import ru.javarush.island.uspenskaya.util.Setting;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Setting(mass=0.01, speed= 0,maxQuality=1000,saturation=0, icon="\uD83D\uDC1B")
public class Caterpillar extends Herbivore {
    private final String name;
    private final TreeMap<Integer,Class<?>> probabilityOfRation= new TreeMap();

    public Caterpillar(double mass, int speed, int maxQuality, double saturation, String icon) {
        super(mass, speed, maxQuality, saturation, icon);
        AtomicInteger number = new AtomicInteger(1);
        this.name = "Caterpillar"+ number.getAndIncrement();
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
        ration.put(Plant.class, 100);
        return ration;
    }

    @Override
    public Map<Integer,Class<?>> getProbabilityOfRation() {
            probabilityOfRation.put(100,Plant.class);
        return probabilityOfRation;
    }

        @Override
    public Organism clone() {
        return new Caterpillar(this.getMass(), this.getSpeed(), this.getMaxQuality(), this.getSaturation(), this.getIcon());
    }

    @Override
    public String toString() {
        return name + getIcon();
    }
}
