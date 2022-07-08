package ru.javarush.island.uspenskaya.services;

import ru.javarush.island.uspenskaya.entities.field.Day;
import ru.javarush.island.uspenskaya.entities.field.GameField;
import ru.javarush.island.uspenskaya.entities.field.Viewer;
import ru.javarush.island.uspenskaya.exeption.IslandException;
import ru.javarush.island.uspenskaya.thread.CarnivoreServiceTask;
import ru.javarush.island.uspenskaya.thread.HerbivoreServiceTask;
import ru.javarush.island.uspenskaya.thread.PlantServiceTask;
import ru.javarush.island.uspenskaya.util.Configger;

import java.util.concurrent.*;

public class GameWorker extends Thread {
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final Day day;
    private final GameField gameField;
    private final Viewer viewer;


    public GameWorker(GameField gameField, Day day) {
        this.day=day;
        this.gameField = gameField;
        this.viewer = new Viewer(day, gameField);

    }

    @Override
    public void run() {
        viewer.showStatistics();
        viewer.showMap();


        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(1);
        mainPool.scheduleAtFixedRate(() -> {

            try {
                boolean isGood = emulation();

                if (isGood) {
                    viewer.showStatistics();
                    viewer.showMap();
                }

            } catch (InterruptedException | ExecutionException e) {
                throw new IslandException(e);
            }
        }, Configger.getInitialDelay(), Configger.getPeriod(), TimeUnit.SECONDS);
    }

    public boolean emulation() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futurePlantService = new FutureTask<>(new PlantServiceTask(gameField));
        FutureTask<Integer> futureCarnivoreService = new FutureTask<>(new CarnivoreServiceTask(gameField));
        FutureTask<Integer> futureHerbivoreService = new FutureTask<>(new HerbivoreServiceTask(gameField));
        ExecutorService servicePool = Executors.newFixedThreadPool(3);
        servicePool.submit(futurePlantService);
        servicePool.submit(futureHerbivoreService);
        servicePool.submit(futureCarnivoreService);


        Integer resultPlantService = futurePlantService.get();
        Integer resultHerbivoreService = futurePlantService.get();
        Integer resultCarnivoreService = futurePlantService.get();

        servicePool.shutdown();

        if (resultPlantService == 1 && resultHerbivoreService == 1 && resultCarnivoreService == 1)
            return true;
        else
        {
            futurePlantService.cancel(true);
            futureCarnivoreService.cancel(true);
            futureHerbivoreService.cancel(true);
            return false;
        }

    }
}





















