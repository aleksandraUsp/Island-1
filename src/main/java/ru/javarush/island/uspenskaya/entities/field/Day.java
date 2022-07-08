package ru.javarush.island.uspenskaya.entities.field;

import java.util.concurrent.atomic.AtomicInteger;

public class Day {
    private final AtomicInteger number=new AtomicInteger(1);
    private final String name;

    public Day() {
        this.name = "Day"+number;
    }

    public String getName() {
        return "Day"+number.getAndIncrement();
    }

    @Override
    public String toString() {
        return name;
    }
}
