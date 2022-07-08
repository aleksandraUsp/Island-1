package ru.javarush.island.uspenskaya.entities.field;

import ru.javarush.island.uspenskaya.entities.organizms.Animal;
import ru.javarush.island.uspenskaya.entities.organizms.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Cell  {
    int row, column;
    private HashMap<Class<?>, HashSet<Animal>> herbivores;
    private HashMap<Class<?>, HashSet<Animal>> carnivores;  // <carnivore,quality>
    private HashSet<Plant> listOfPlant;


    public Cell(int row, int column) {
        herbivores = new HashMap<>();
        carnivores = new HashMap<>();
        listOfPlant = new HashSet<>();
        this.row=row;
        this.column=column;
    }

    public Cell getMonitor(int row, int column){
        return this;
    }
    public HashMap<Class<?>, HashSet<Animal>> getHerbivores() {
        return herbivores;
    }

    public void setHerbivores(HashMap<Class<?>, HashSet<Animal>> herbivores) {
        this.herbivores = herbivores;
    }

    public HashMap<Class<?>, HashSet<Animal>> getCarnivores() {
        return carnivores;
    }

    public void setCarnivores(HashMap<Class<?>, HashSet<Animal>> carnivores) {
        this.carnivores = carnivores;
    }

    public HashSet<Plant> getListOfPlant() {
        return listOfPlant;
    }

    public void setListOfPlant(HashSet<Plant> listOfPlant) {
        this.listOfPlant = listOfPlant;
    }

    public int getQualityOfPlant() {
        return listOfPlant.size();
    }


    public String print(HashMap<Cell, HashMap<String, Integer>> mapForPrintOut) {
        HashMap<String, Integer> leaders=mapForPrintOut.get(this);
        ArrayList<String> out = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : leaders.entrySet()) {
            String icon = entry.getKey();
            Integer quality=entry.getValue();
            out.add(icon+"="+quality);
        }
        return "|"+out.get(0)+";"+out.get(1)+";"+out.get(2)+"|";
    }
}
