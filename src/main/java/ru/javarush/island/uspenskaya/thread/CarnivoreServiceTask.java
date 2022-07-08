package ru.javarush.island.uspenskaya.thread;

import ru.javarush.island.uspenskaya.entities.field.GameField;
import ru.javarush.island.uspenskaya.exeption.IslandException;
import ru.javarush.island.uspenskaya.services.CarnivoreService;
import ru.javarush.island.uspenskaya.util.Configger;
import ru.javarush.island.uspenskaya.util.Sleeper;

import java.util.concurrent.Callable;

public class CarnivoreServiceTask implements Callable<Integer> {
    GameField gameField;
    CarnivoreService carnivoreService;


    public CarnivoreServiceTask(GameField gameField) {
        this.gameField=gameField;
        this.carnivoreService = gameField.getCarnivoreService();
    }

    @Override
    public Integer call() {
        int isGood = 1;
        try {
            carnivoreService.reproduct(gameField.getField());
            Sleeper.sleep(Configger.getMinTimeout()*2);
        } catch (Exception e) {
            throw new IslandException("CarnivoreService isn't good" + e);
        }
        return isGood;
    }

}
