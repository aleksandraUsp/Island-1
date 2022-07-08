package ru.javarush.island.uspenskaya.interfaces.organismactions;

import ru.javarush.island.uspenskaya.entities.field.Cell;
import ru.javarush.island.uspenskaya.interfaces.simpleinterface.Initializable;
import ru.javarush.island.uspenskaya.interfaces.simpleinterface.Reproducible;

public interface OrganismActions extends Initializable, Reproducible {
    @Override
    void initialize();

    @Override
    void reproduct(Cell[][] field);

}
