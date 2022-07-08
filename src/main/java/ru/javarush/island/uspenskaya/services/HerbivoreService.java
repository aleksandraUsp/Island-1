package ru.javarush.island.uspenskaya.services;

import ru.javarush.island.uspenskaya.entities.field.Cell;
import ru.javarush.island.uspenskaya.entities.organizms.Animal;
import ru.javarush.island.uspenskaya.entities.organizms.Plant;
import ru.javarush.island.uspenskaya.repository.FactoryLife;
import ru.javarush.island.uspenskaya.repository.TypesOfOrganisms;
import ru.javarush.island.uspenskaya.util.Configger;
import ru.javarush.island.uspenskaya.util.Randomizer;

import java.util.*;


public class HerbivoreService extends AnimalService {
    HashSet<Class<?>> herbivoresTypes = TypesOfOrganisms.getTypesOfHerbivores();
    Cell[][] field;

    public HerbivoreService(Cell[][] field) {
        this.field = field;
    }

    @Override
    public void initialize() {
        Cell cell;
        for (int i = field.length-1; i >= 0; i--) {
            for (int j = field[i].length-1; j >= 0; j--) {
                cell = field[i][j];
                HashMap<Class<?>, HashSet<Animal>> herbivores = new HashMap<>();
                for (Class<?> typeOfHerbivores : herbivoresTypes) {
                    HashSet<Animal> listOfHerbivoresThisType = FactoryLife.factoryOrganism(typeOfHerbivores);
                    herbivores.put(typeOfHerbivores, listOfHerbivoresThisType);
                }
                cell.setHerbivores(herbivores);
            }
        }
    }


    @Override
    public void reproduct(Cell[][] field) {
        Cell cell;
        int qualityOfAnimal, qualityOfAddAnimal, bandOfAnimal;
        for (int i = field.length-1; i >= 0; i--) {
            for (int j = field[i].length-1; j >= 0; j--) {
                cell = field[i][j];
                synchronized (cell.getMonitor(i, j)) {
                    Map<Class<?>, HashSet<Animal>> herbivores = cell.getHerbivores();
                    for (Map.Entry<Class<?>, HashSet<Animal>> entry : herbivores.entrySet()) {
                        Class<?> typeOfHerbivores = entry.getKey();
                        HashSet<Animal> listOfHerbivoresThisType = entry.getValue();
                        qualityOfAnimal = listOfHerbivoresThisType.size();
                        TypesOfOrganisms type = TypesOfOrganisms.valueOf(typeOfHerbivores
                                .getSimpleName()
                                .toUpperCase(Locale.ROOT));
                        int maxQualityOfAnimal = type.getMaxQuality();
                        bandOfAnimal = maxQualityOfAnimal - qualityOfAnimal;
                        qualityOfAddAnimal = Randomizer.getRnd(0, bandOfAnimal);
                        HashSet<Animal> listOfAddHerbivoresThisType = FactoryLife.factoryOrganism(qualityOfAddAnimal, typeOfHerbivores);
                        listOfHerbivoresThisType.addAll(listOfAddHerbivoresThisType);
                        entry.setValue(listOfHerbivoresThisType);
                    }
                }
            }
        }
    }

    @Override
    public void eat() {
        Cell cell;
        for (int i = field.length-1; i >= 0; i--) {
            for (int j = field[i].length-1; j >= 0; j--) {
                cell = field[i][j];

                synchronized (cell.getMonitor(i, j)) {
                    HashMap<Class<?>, HashSet<Animal>> herbivores = cell.getHerbivores();
                    for (Map.Entry<Class<?>, HashSet<Animal>> entry : herbivores.entrySet()) {
                        Class<?> classOfHerbivores = entry.getKey();
                        HashSet<Animal> listOfHerbivoresThisType = entry.getValue();
                        TypesOfOrganisms type = TypesOfOrganisms.valueOf(classOfHerbivores
                                .getSimpleName()
                                .toUpperCase(Locale.ROOT));


                        for (Animal animal : listOfHerbivoresThisType) {
                            double massOfAnimal = animal.getMass();
                            Map<Integer, Class<?>> rationThisTypeAnimal = animal.getProbabilityOfRation();
                            for (Map.Entry<Integer, Class<?>> entryProbability : rationThisTypeAnimal.entrySet()) {

                                Class<?> clazzOfEat = entryProbability.getValue();


                                double tempSaturation = 0;


                                if (clazzOfEat != Plant.class) {
                                    HashSet<Animal> setOfAnimalToEat = herbivores.get(clazzOfEat);
                                    if (setOfAnimalToEat.size() > 0) {
                                        for (Animal animalForEating : setOfAnimalToEat) {
                                            double massOfAnimalForEating = animalForEating.getMass();
                                            if (massOfAnimalForEating < animal.getSaturation() &&
                                                    massOfAnimalForEating + massOfAnimal < type.getMaxMass()) {
                                                massOfAnimal = massOfAnimal + massOfAnimalForEating;
                                                tempSaturation = tempSaturation + massOfAnimalForEating;
                                                animalForEating.setMass(0.0);
                                            }
                                        }
                                    }
                                }
                                if (clazzOfEat == Plant.class) {
                                    HashSet<Plant> listOfPlant = cell.getListOfPlant();

                                    if (listOfPlant.size() > 0) {
                                        for (Plant plant : listOfPlant) {
                                            double massOfPlant = plant.getMass();
                                            if (massOfPlant < animal.getSaturation() &&
                                                    massOfPlant + massOfAnimal < type.getMaxMass()) {
                                                massOfAnimal = massOfAnimal + massOfPlant;
                                                tempSaturation = tempSaturation + massOfPlant;
                                                plant.setMass(0.0);
                                            }
                                        }
                                    }
                                }
                                if (tempSaturation == animal.getSaturation() || massOfAnimal == type.getMaxMass())
                                    break;
                            }
                            animal.setMass(massOfAnimal);
                        }
                    }
                    cell.setHerbivores(herbivores);
                }

            }
        }
        dieHerbivores();
        diePlant();
    }


    @Override
    public void move() {
        super.move();
    }

    @SuppressWarnings("Java8CollectionRemoveIf")
    public void dieHerbivores() {
        Cell cell;
        for (int i = field.length - 1; i >= 0; i--) {
            for (int j = field[i].length - 1; j >= 0; j--) {
                cell = field[i][j];

                synchronized (cell.getMonitor(i, j)) {
                    HashMap<Class<?>, HashSet<Animal>> herbivores = cell.getHerbivores();
                    for (Map.Entry<Class<?>, HashSet<Animal>> entryHerbivoresMayBeDie : herbivores.entrySet()) {
                        HashSet<Animal> setOfHerbivoresMayBeDie = entryHerbivoresMayBeDie.getValue();
                        Class<?> classOfHerbivores = entryHerbivoresMayBeDie.getKey();
                        TypesOfOrganisms type = TypesOfOrganisms.valueOf(classOfHerbivores
                                .getSimpleName()
                                .toUpperCase(Locale.ROOT));
                        Iterator<Animal> it = setOfHerbivoresMayBeDie.iterator();
                        while (it.hasNext()) {
                            Animal animalMayBeDie = it.next();
                            if (animalMayBeDie.getMass() <= type.getMaxMass() * Configger.getValueOfMassForDeath())
                                it.remove();
                        }
                    }
                    cell.setHerbivores(herbivores);
                }
            }
        }
    }


    public void diePlant() {
        Cell cell;
        for (int i = field.length-1; i >= 0; i--) {
            for (int j = field[i].length-1; j >= 0; j--) {
                cell = field[i][j];

                synchronized (cell.getMonitor(i, j)) {
                    HashSet<Plant> setOfPlant = cell.getListOfPlant();
                    setOfPlant.removeIf(plantMayBeDie -> plantMayBeDie.getMass() == 0.0);
                    cell.setListOfPlant(setOfPlant);
                }
            }
        }
    }
}

