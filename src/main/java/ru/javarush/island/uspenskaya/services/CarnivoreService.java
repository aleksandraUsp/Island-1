package ru.javarush.island.uspenskaya.services;

import ru.javarush.island.uspenskaya.entities.field.Cell;
import ru.javarush.island.uspenskaya.entities.organizms.Animal;
import ru.javarush.island.uspenskaya.repository.FactoryLife;
import ru.javarush.island.uspenskaya.repository.TypesOfOrganisms;
import ru.javarush.island.uspenskaya.util.Randomizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;


public class CarnivoreService extends AnimalService {
    Cell[][] field;

     public CarnivoreService(Cell[][] field) {
        this.field = field;
    }

    @Override
    public void initialize() {
        Cell cell;
        HashSet<Class<?>> carnivoresTypes = TypesOfOrganisms.getTypesOfCarnivores();

        for (Cell[] cells : field) {
            for (int j = 0; j < cells.length; j++) {
                cell = cells[j];
                HashMap<Class<?>, HashSet<Animal>> carnivores = new HashMap<>();
                for (Class<?> typeOfCarnivores : carnivoresTypes) {
                    HashSet<Animal> listOfCarnivoresThisType = FactoryLife.factoryOrganism(typeOfCarnivores);
                    carnivores.put(typeOfCarnivores, listOfCarnivoresThisType);
                }
                cell.setCarnivores(carnivores);
            }
        }
    }


    @Override
    public void reproduct(Cell[][] field) {
        Cell cell;
        int qualityOfAnimal, qualityOfAddAnimal, bandOfAnimal;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                cell = field[i][j];
                synchronized (cell.getMonitor(i, j)) {
                    Map<Class<?>, HashSet<Animal>> carnivores = cell.getCarnivores();
                    for (Map.Entry<Class<?>, HashSet<Animal>> entry : carnivores.entrySet()) {
                        Class<?> typeOfCarnivores = entry.getKey();
                        HashSet<Animal> listOfCarnivoresThisType = entry.getValue();
                        qualityOfAnimal = listOfCarnivoresThisType.size();
                        TypesOfOrganisms type = TypesOfOrganisms.valueOf(typeOfCarnivores
                                .getSimpleName()
                                .toUpperCase(Locale.ROOT));
                        int maxQualityOfAnimal = type.getMaxQuality();
                        bandOfAnimal = maxQualityOfAnimal - qualityOfAnimal;
                        qualityOfAddAnimal = Randomizer.getRnd(0, bandOfAnimal);
                        HashSet<Animal> listOfAddCarnivoresThisType = FactoryLife.factoryOrganism(qualityOfAddAnimal, typeOfCarnivores);
                        listOfCarnivoresThisType.addAll(listOfAddCarnivoresThisType);
                        entry.setValue(listOfCarnivoresThisType);

                    }
                }
            }
        }
    }


    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }
}


/*    @Override
    public void run() {
        eat(cell.herbivores);
        int timeout= Randomizer.getRnd(Configger.getMinTimeout(), Configger.getMaxTimeout());
        Sleeper.sleep(timeout);
        multiply(Integer quality);
        Sleeper.sleep(timeout);
        move();
        Sleeper.sleep(timeout);
    }
    @Override
    public Map<Organism, Integer> eat(Map<Organism, Integer> organisms) {
        return null;
    }

    @Override
    public void move() {

    }

    @Override
    public Integer multiply(Integer quality) {
        return null;
    }
}*/
