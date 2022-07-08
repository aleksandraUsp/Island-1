package ru.javarush.island.uspenskaya.thread;

import ru.javarush.island.uspenskaya.entities.field.GameField;
import ru.javarush.island.uspenskaya.exeption.IslandException;
import ru.javarush.island.uspenskaya.services.HerbivoreService;
import ru.javarush.island.uspenskaya.util.Configger;
import ru.javarush.island.uspenskaya.util.Sleeper;

import java.util.concurrent.Callable;

public class HerbivoreServiceTask implements Callable<Integer> {
    GameField gameField;
    HerbivoreService herbivoreService;


    public HerbivoreServiceTask(GameField gameField) {
        this.gameField=gameField;
        this.herbivoreService = gameField.getHerbivoreService();
    }

    @Override
    public Integer call() {
        int isGood = 1;
        try {
            herbivoreService.reproduct(gameField.getField());
            herbivoreService.eat();
            Sleeper.sleep(Configger.getMinTimeout()*2);
        } catch (Exception e) {
            throw new IslandException("HerbivoreService isn't good" + e);
        }
        return isGood;
    }

}

