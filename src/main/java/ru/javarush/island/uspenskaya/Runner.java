package ru.javarush.island.uspenskaya;

import ru.javarush.island.uspenskaya.entities.field.Day;
import ru.javarush.island.uspenskaya.entities.field.GameField;
import ru.javarush.island.uspenskaya.services.GameWorker;
import ru.javarush.island.uspenskaya.util.Configger;


public class Runner {
    public static void main(String[] args) {
        System.out.println("Да будет остров!");
        Day day = new Day();
        GameField gameField = new GameField(Configger.getRow(), Configger.getColumn());
        GameWorker gameWorker = new GameWorker(gameField, day);
        gameWorker.start();

    }
}
