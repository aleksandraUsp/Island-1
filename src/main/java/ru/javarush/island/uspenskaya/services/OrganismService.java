package ru.javarush.island.uspenskaya.services;

import ru.javarush.island.uspenskaya.entities.field.Cell;
import ru.javarush.island.uspenskaya.interfaces.organismactions.OrganismActions;


public abstract class OrganismService implements OrganismActions {
    public OrganismService() {
    }

    @Override
    public void initialize() {
    }

    @Override
    public void reproduct(Cell[][] field) {

    }
}
