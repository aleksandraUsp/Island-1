package ru.javarush.island.uspenskaya.repository;

import ru.javarush.island.uspenskaya.entities.organizms.Animal;
import ru.javarush.island.uspenskaya.entities.organizms.Organism;
import ru.javarush.island.uspenskaya.exeption.IslandException;
import ru.javarush.island.uspenskaya.util.Randomizer;
import ru.javarush.island.uspenskaya.util.Setting;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FactoryEtalonOrganisms {
    public FactoryEtalonOrganisms() {
    }

    public static Map<Class<?>, Organism> createPrototypesMap(HashSet<Class<?>> setOfOrganisms) { //HashSet<Class<?>> TypesOfOrganisms.getTypesOfOrganisms()
        HashMap<Class<?>, Organism> etalonOrganisms = new HashMap<>();//Organism[] organisms = new Organism[setOfOrganisms.size()];
        for (Class<?> type : setOfOrganisms) {
            double mass = 0;
            int speed = 0;
            int maxQuality = 0;
            double saturation = 0D;
            String icon = null;
            if (type.isAnnotationPresent(Setting.class)) {
                Setting setting = type.getAnnotation(Setting.class);
                //(double mass, int speed, int maxQuality, double saturation, String icon)
                //if((type.equals(TypesOfOrganisms.PLANT.getType())||(type.equals(TypesOfOrganisms.CATERPILLAR.getType()))
                mass = Randomizer.getRndDouble(setting.mass() / 2, setting.mass());
                speed = setting.speed();
                maxQuality = setting.maxQuality();
                if (setting.saturation()!=0){
                saturation = Randomizer.getRndDouble(0, setting.saturation());}
                else {saturation=0;}
                icon = setting.icon();
            }
            Organism organism = generatePrototypeOrganism(type, mass, speed, maxQuality, saturation, icon);
            etalonOrganisms.put(type, organism);
        }
        return etalonOrganisms;
    }

    @SuppressWarnings("unused")
    public static Map<Class<?>, Animal> createPrototypesMapAnimal(HashSet<Class<?>> setOfOrganisms) { //HashSet<Class<?>> TypesOfOrganisms.getTypesOfOrganisms()
        HashMap<Class<?>, Animal> etalonOrganisms = new HashMap<>();//Organism[] organisms = new Organism[setOfOrganisms.size()];
        for (Class<?> type : setOfOrganisms) {
            double mass = 0;
            int speed = 0;
            int maxQuality = 0;
            double saturation = 0D;
            String icon = null;
            if (type.isAnnotationPresent(Setting.class)) {
                Setting setting = type.getAnnotation(Setting.class);
                //(double mass, int speed, int maxQuality, double saturation, String icon)
                //if((type.equals(TypesOfOrganisms.PLANT.getType())||(type.equals(TypesOfOrganisms.CATERPILLAR.getType()))
                mass = Randomizer.getRndDouble(setting.mass() / 2, setting.mass());
                speed = setting.speed();
                maxQuality = setting.maxQuality();
                if (setting.saturation()!=0){
                    saturation = Randomizer.getRndDouble(0, setting.saturation());}
                else {saturation=0;}
                icon = setting.icon();
            }
            Animal organism = generatePrototypeAnimal(type, mass, speed, maxQuality, saturation, icon);
            etalonOrganisms.put(type, organism);
        }
        return etalonOrganisms;
    }


    private static Organism generatePrototypeOrganism(Class<?> type, double mass, int speed, int maxQuality, double saturation, String icon) {
        Organism organism;
        try {
            if (type.equals(TypesOfOrganisms.PLANT.getType())) {
                Constructor<?> constructor = type.getConstructor(double.class, int.class, String.class);
                organism = (Organism) constructor.newInstance(mass, maxQuality, icon);
            } else {
                Constructor<?> constructor = type.getConstructor(double.class, int.class, int.class, double.class, String.class);
                organism = (Organism) constructor.newInstance(mass, speed, maxQuality, saturation, icon);
            }
            return organism;
        } catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IslandException("not found entity constructor", e);
        }
    }

    private static Animal generatePrototypeAnimal(Class<?> type, double mass, int speed, int maxQuality, double saturation, String icon) {
        Animal organism;
        try {
                Constructor<?> constructor = type.getConstructor(double.class, int.class, int.class, double.class, String.class);
                organism = (Animal) constructor.newInstance(mass, speed, maxQuality, saturation, icon);

            return organism;
        } catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IslandException("not found entity constructor", e);
        }
    }
}

