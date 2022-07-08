package ru.javarush.island.uspenskaya.entities.organizms.herbivore;

import ru.javarush.island.uspenskaya.entities.organizms.Organism;
import ru.javarush.island.uspenskaya.entities.organizms.Plant;
import ru.javarush.island.uspenskaya.util.Setting;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Setting(mass=400, speed= 2,maxQuality=50,saturation=50, icon="\uD83D\uDC17")
public class Boar extends Herbivore {
    private final String name;
    private final TreeMap<Integer,Class<?>> probabilityOfRation= new TreeMap();

    public Boar(double mass, int speed, int maxQuality, double saturation, String icon) {
        super(mass, speed, maxQuality, saturation, icon);
        AtomicInteger number = new AtomicInteger(1);
        this.name = "Boar"+ number.getAndIncrement();
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
        ration.put(Mouse.class, 50);
        ration.put(Goat.class, 0);
        ration.put(Sheep.class, 0);
        ration.put(Buffalo.class, 0); //буйвол
        ration.put(Duck.class, 0);
        ration.put(Caterpillar.class, 90); //гусеница
        ration.put(Plant.class, 100);
        return ration;
    }

    @Override
    public Map<Integer,Class<?>> getProbabilityOfRation() {
        probabilityOfRation.put(50,Mouse.class);
        probabilityOfRation.put(90,Caterpillar.class); //гусеница
        probabilityOfRation.put(100,Plant.class);
        return probabilityOfRation;
    }

    @Override
    public Organism clone() {
        return new Boar(this.getMass(), this.getSpeed(), this.getMaxQuality(), this.getSaturation(), this.getIcon());
    }

    @Override
    public String toString() {
        return name + getIcon();
    }
}
