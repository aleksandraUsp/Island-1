package ru.javarush.island.uspenskaya.util;

import ru.javarush.island.uspenskaya.exeption.IslandException;

public class Sleeper {
    private Sleeper() {
    }

    public static void sleep (int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }

}
