package ru.javarush.island.uspenskaya.interfaces.organismactions;

import ru.javarush.island.uspenskaya.entities.field.Cell;
import ru.javarush.island.uspenskaya.interfaces.simpleinterface.Eatable;
import ru.javarush.island.uspenskaya.interfaces.simpleinterface.Movable;

public interface AnimalActions extends OrganismActions, Eatable, Movable {
    @Override
    void initialize();


    @Override
    void reproduct(Cell[][] field);

    @Override
    void eat();

    @Override
    void move();
}
