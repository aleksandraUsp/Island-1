package ru.javarush.island.uspenskaya.entities.field;


import ru.javarush.island.uspenskaya.services.CarnivoreService;
import ru.javarush.island.uspenskaya.services.HerbivoreService;
import ru.javarush.island.uspenskaya.services.PlantService;

public class GameField {
    private final Cell[][]field;   //100x20
    CarnivoreService carnivoreService;
    HerbivoreService herbivoreService;
    PlantService plantService;

    public GameField(int row, int column) {
        this.field= new Cell[row][column];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
        this.plantService = new PlantService(field);
        plantService.initialize();
        this.herbivoreService=new HerbivoreService(field);
        herbivoreService.initialize();
        this.carnivoreService = new CarnivoreService(field);
        carnivoreService.initialize();
    }

    public Cell[][] getField() {
        return field;
    }

    public int getRows() {return field.length;}

    public int getColumns(){return field[0].length;}

    public CarnivoreService getCarnivoreService() {
        return carnivoreService;
    }

    public void setCarnivoreService(CarnivoreService carnivoreService) {
        this.carnivoreService = carnivoreService;
    }

    public HerbivoreService getHerbivoreService() {
        return herbivoreService;
    }

    public void setHerbivoreService(HerbivoreService herbivoreService) {
        this.herbivoreService = herbivoreService;
    }

    public PlantService getPlantService() {
        return plantService;
    }

    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    public void print(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();

        }

    }
}
