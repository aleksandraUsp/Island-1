package ru.javarush.island.uspenskaya.entities.organizms;


import ru.javarush.island.uspenskaya.util.Setting;

@Setting(mass=1, speed= 0,maxQuality=200*4,saturation=0, icon="\uD83C\uDF3F")
public class Plant extends Organism implements Cloneable{

    public Plant(double mass, int maxQuality, String icon) {
        super(mass, maxQuality, icon);
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

    @Override
    public Plant clone() {
        return new Plant(this.getMass(), this.getMaxQuality(), this.getIcon());
        }
}

