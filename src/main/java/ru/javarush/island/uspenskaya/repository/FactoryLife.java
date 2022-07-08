package ru.javarush.island.uspenskaya.repository;


import ru.javarush.island.uspenskaya.entities.organizms.Animal;
import ru.javarush.island.uspenskaya.entities.organizms.Organism;
import ru.javarush.island.uspenskaya.entities.organizms.Plant;
import ru.javarush.island.uspenskaya.util.Randomizer;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class FactoryLife {

    public FactoryLife() {
    }
    public static HashSet<Animal> factoryOrganism(Class<?> clazz) {
        TypesOfOrganisms type= TypesOfOrganisms.valueOf(clazz.getSimpleName().toUpperCase(Locale.ROOT));
        double maxMass = type.getMaxMass();
        int maxQualityOfOrganism = type.getMaxQuality();
        Animal prototypeOfOrganism = getPrototypeThisTypeAnimal(clazz);
        int quality = Randomizer.getRnd(0,maxQualityOfOrganism);
        HashSet<Animal> listOfOrganisms = new HashSet<>(quality);
        for (int i = 0; i < quality; i++) {
            Animal organism = (Animal)prototypeOfOrganism.clone();
            organism.setMass(Randomizer.getRndDouble(0, maxMass));
            listOfOrganisms.add(organism);
        }
        return listOfOrganisms;
    }

    public static HashSet<Animal> factoryOrganism(int qualityOfOrganism, Class<?> clazz) {
        TypesOfOrganisms type= TypesOfOrganisms.valueOf(clazz.getSimpleName().toUpperCase(Locale.ROOT));
        double maxMass = type.getMaxMass();
        Animal prototypeOfOrganism = getPrototypeThisTypeAnimal(clazz);
        HashSet<Animal> listOfOrganism = new HashSet<>(qualityOfOrganism);
        for (int i = 0; i < qualityOfOrganism; i++) {
            Animal organism = (Animal)prototypeOfOrganism.clone();
            organism.setMass(Randomizer.getRndDouble(0, maxMass));
            listOfOrganism.add(organism);
        }
        return listOfOrganism;
    }

    public static HashSet<Plant> factoryPlant(int qualityOfPlant) {
        Plant prototypeOfPlant = (Plant) getPrototypeThisType(TypesOfOrganisms.PLANT.getType());
        HashSet<Plant> listOfPlant = new HashSet<>();
        for (int i = 0; i < qualityOfPlant; i++) {
            Plant plant = prototypeOfPlant.clone();
            plant.setMass(Randomizer.getRndDouble(0, TypesOfOrganisms.PLANT.getMaxMass()));
            listOfPlant.add(plant);
        }
        return listOfPlant;
    }

    static Organism getPrototypeThisType(Class<?> clazz){
        Map<Class<?>, Organism> mapOfEtalonOrganisms = FactoryEtalonOrganisms.createPrototypesMap
                (TypesOfOrganisms.getTypesOfOrganisms());
        return mapOfEtalonOrganisms.get(clazz);
    }

    static Animal getPrototypeThisTypeAnimal(Class<?> clazz){
        Map<Class<?>, Animal> mapOfEtalonOrganisms = FactoryEtalonAnimals.createPrototypesMapAnimal
                (TypesOfOrganisms.getTypesOfAnimals());
        return mapOfEtalonOrganisms.get(clazz);
    }
}

    /*for (Map.Entry<String, List<Herbivore>> entry : carnivores.entrySet()) {
        String carnivoreType = entry.getKey();
        int qualityCarnivoreThisType = entry.getValue();
        for (int i = 0; i < qualityCarnivoreThisType; i++) {
            herbivores = carnivore.eat(herbivores); // вернули оставшихся травоядных
        }*/

