package ru.javarush.island.uspenskaya.services;

import ru.javarush.island.uspenskaya.entities.field.Cell;
import ru.javarush.island.uspenskaya.entities.organizms.Plant;
import ru.javarush.island.uspenskaya.repository.FactoryLife;
import ru.javarush.island.uspenskaya.repository.TypesOfOrganisms;
import ru.javarush.island.uspenskaya.util.Randomizer;

import java.util.HashSet;


public class PlantService extends OrganismService {
    Cell[][] field;

    public PlantService(Cell[][] field) {
        this.field = field;
    }

    @Override
    public void initialize() {
        Cell cell;
        int qualityOfPlant;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                cell = field[i][j];
                qualityOfPlant = Randomizer.getRnd(0, TypesOfOrganisms.PLANT.getMaxQuality());
                HashSet<Plant> listOfCellPlant = FactoryLife.factoryPlant(qualityOfPlant);
                cell.setListOfPlant(listOfCellPlant);
            }
        }
    }

    @Override
    public void reproduct(Cell[][] field) {
        Cell cell;
        int qualityOfPlant, qualityOfAddPlant, bandOfPlant;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                cell = field[i][j];
                synchronized (cell.getMonitor(i, j)) {
                    HashSet<Plant> listOfPlant = cell.getListOfPlant();
                    qualityOfPlant = cell.getQualityOfPlant();
                    bandOfPlant = TypesOfOrganisms.PLANT.getMaxQuality() - qualityOfPlant;
                    qualityOfAddPlant = Randomizer.getRnd(0, bandOfPlant);
                    HashSet<Plant> listOfCellAddPlant = FactoryLife.factoryPlant(qualityOfAddPlant);
                    listOfPlant.addAll(listOfCellAddPlant);
                    cell.setListOfPlant(listOfPlant);
                }
            }
        }
    }
}




