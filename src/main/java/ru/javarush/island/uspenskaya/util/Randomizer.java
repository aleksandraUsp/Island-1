package ru.javarush.island.uspenskaya.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private Randomizer() {
    }

    public static int getRnd (int from, int to){
        return ThreadLocalRandom.current().nextInt(from,to);
    }
    public static double getRndDouble (double from, double to){
        return ThreadLocalRandom.current().nextDouble(from,to);
    }
}
