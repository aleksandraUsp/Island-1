package ru.javarush.island.uspenskaya.entities.organizms.carnivore;

import ru.javarush.island.uspenskaya.entities.organizms.Organism;
import ru.javarush.island.uspenskaya.entities.organizms.herbivore.*;
import ru.javarush.island.uspenskaya.util.Setting;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Setting(mass=500, speed= 2,maxQuality=5,saturation=80, icon="\uD83D\uDC3B")
public class Bear extends Carnivore {
    private final AtomicInteger number=new AtomicInteger(1);
    private final Map<Class<?>, Integer> ration = new HashMap<>();
    private final String name;

    public Bear(double mass, int speed, int maxQuality, double saturation, String icon) {
        super(mass, speed, maxQuality, saturation, icon);
        this.name = "Bear"+number.getAndIncrement();
    }

    @Override
    public Map<Class<?>, Integer> getRation() {
        ration.put(Wolf.class, 0);
        ration.put(Snake.class ,80);
        ration.put(Fox.class, 0);
        ration.put(Eagle.class, 0);
        ration.put(Horse.class, 40);
        ration.put(Deer.class, 80); // олень
        ration.put(Rabbit.class, 80);
        ration.put(Mouse.class, 90);
        ration.put(Goat.class, 70);
        ration.put(Sheep.class, 70);
        ration.put(Boar.class, 50); //кабан
        ration.put(Buffalo.class, 20); //буйвол
        ration.put(Duck.class, 10);
        ration.put(Caterpillar.class, 0); //гусеница
        return ration;
    }

    public String getName() {
        return name;
    }

    public AtomicInteger getNumber() {
        return number;
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
       return new Bear(this.getMass(), this.getSpeed(), this.getMaxQuality(), this.getSaturation(), this.getIcon());
    }

    public String toString() {
        return name + getIcon();
    }
}
