package ru.javarush.island.uspenskaya.thread;

import ru.javarush.island.uspenskaya.entities.field.GameField;
import ru.javarush.island.uspenskaya.exeption.IslandException;
import ru.javarush.island.uspenskaya.services.PlantService;
import ru.javarush.island.uspenskaya.util.Configger;
import ru.javarush.island.uspenskaya.util.Sleeper;

import java.util.concurrent.Callable;


public class PlantServiceTask implements Callable<Integer> {
    GameField gameField;
    PlantService plantService;


    public PlantServiceTask(GameField gameField) {
        this.gameField=gameField;
        this.plantService = gameField.getPlantService();
    }

    @Override
    public Integer call() {
        int isGood = 1;
        try {plantService.reproduct(gameField.getField());
            Sleeper.sleep(Configger.getMinTimeout()*2);
        } catch (Exception e) {
            throw new IslandException("PlantService isn't good" + e);
        }
        return isGood;
    }
}
