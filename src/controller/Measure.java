package controller;

import model.cubes.Color;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.solving.genetic.model.SolvedThreeCube;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Measure {

    public static void main(String[] args) throws InterruptedException {
        ThreeCube cube = new ThreeCube(
                new Color[][]{
                        {Color.RED, Color.YELLOW, Color.ORANGE},
                        {Color.ORANGE, Color.WHITE, Color.GREEN},
                        {Color.BLUE, Color.YELLOW, Color.YELLOW}
                },
                new Color[][]{
                        {Color.BLUE, Color.ORANGE, Color.ORANGE},
                        {Color.GREEN, Color.YELLOW, Color.BLUE},
                        {Color.WHITE, Color.ORANGE, Color.BLUE}
                },
                new Color[][]{
                        {Color.RED, Color.RED, Color.BLUE},
                        {Color.BLUE, Color.GREEN, Color.YELLOW},
                        {Color.YELLOW, Color.WHITE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.RED, Color.ORANGE},
                        {Color.RED, Color.BLUE, Color.YELLOW},
                        {Color.ORANGE, Color.GREEN, Color.GREEN}
                },
                new Color[][]{
                        {Color.GREEN, Color.WHITE, Color.WHITE},
                        {Color.RED, Color.RED, Color.WHITE},
                        {Color.WHITE, Color.ORANGE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.BLUE, Color.RED},
                        {Color.BLUE, Color.ORANGE, Color.WHITE},
                        {Color.RED, Color.GREEN, Color.WHITE}
                }
        );

        int numberOfThreads = 1000; // Anzahl der Threads, die du verwenden möchtest
        long durationInSeconds = 1; // Testdauer in Sekunden

        // Executor Service erstellen, um mehrere Threads zu verwalten
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // Um die Anzahl der Methodenausführungen in allen Threads zu zählen
        final int[] executionCounts = new int[numberOfThreads];

        // Zeitmessung
        long startTime = System.nanoTime();
        long endTime = startTime + durationInSeconds * 1_000_000_000L;

        // Threads starten
        for (int i = 0; i < numberOfThreads; i++) {
            final int threadIndex = i; // Für die Verwendung im Lambda-Ausdruck
            executorService.submit(() -> {
                long threadStartTime = System.nanoTime();
                while (System.nanoTime() - threadStartTime < durationInSeconds * 1_000_000_000L) {
                    cube.equals(SolvedThreeCube.SOLVED);
                    executionCounts[threadIndex]++;
                }
            });
        }

        // Executor-Service wartet, bis alle Threads fertig sind
        executorService.shutdown();
        executorService.awaitTermination(durationInSeconds + 1, TimeUnit.SECONDS);

        // Gesamtanzahl der Ausführungen über alle Threads hinweg
        int totalExecutions = 0;
        for (int count : executionCounts) {
            totalExecutions += count;
        }

        System.out.println("Die Methode wurde insgesamt " + totalExecutions + " Mal in " + durationInSeconds + " Sekunde(n) auf " + numberOfThreads + " Threads ausgeführt.");
    }

}

class MaxThreadsTest {
    public static void main(String[] args) {
        int threadCount = 0;
        try {
            while (true) {
                Thread t = new Thread(() -> {
                    try {
                        // Einfacher Thread, der nichts tut
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        // Der Thread wird nie unterbrochen
                    }
                });
                t.start();
                System.out.println(++threadCount);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Maximale Anzahl an Threads erreicht: " + threadCount);
        }
    }
}
