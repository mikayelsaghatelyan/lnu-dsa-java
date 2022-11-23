package com.source;

import java.util.Arrays;
import java.util.Scanner;

public class DriverClass {
    static boolean debug = false;

    // Method for measuring the execution time of making unions in UF, QUF and WQUF data structures.
    public static double[] UnionFindTest(int mode, int size, int tries) {

        Chronograph creation_time = new Chronograph();

        ThreeSum solution = null;
        UF sampleUF = null;
        QUF sampleQUF = null;
        WQUF sampleWQUF = null;
        if (mode == 0) sampleUF = new UF(size);
        if (mode == 1) sampleQUF = new QUF(size);
        if (mode == 2) sampleWQUF = new WQUF(size);
        double[] execution_times = new double[tries];

        if (debug) System.out.println("object creation time: " + creation_time.elapsed("msec") + " msec");

        Chronograph overall_time = new Chronograph();
        for (int q = 0; q < tries; ++q) {
            Chronograph execution_time = new Chronograph();
            switch (mode) {
                case 0 -> {
                    for (int i = 0; i < size - 1; i += 1)
                        sampleUF.union(i, i + 1);
                }
                case 1 -> {
                    for (int i = 0; i < size - 1; i += 1)
                        sampleQUF.union(i, i + 1);
                }
                case 2 -> {
                    for (int i = 0; i < size - 1; i += 1)
                        sampleWQUF.union(i, i + 1);
                }
            }
            if (debug) System.out.println(execution_time.elapsed("msec") + " msec");
            execution_times[q] = execution_time.elapsed("msec");
        }
        double sum = 0.0;
        double max = execution_times[0];
        double min = execution_times[0];
        for (int i = 0; i < tries; ++i) {
            if (execution_times[i] > max) max = execution_times[i];
            if (execution_times[i] < min) min = execution_times[i];
            sum += execution_times[i];
        }
        sum /= tries;
        System.out.print("overall time: ");
        System.out.println(overall_time.elapsed("msec"));
        return new double[]{min, max, sum};
    }

    // method for measuring the execution time of Percolation Model which works through the Monte Carlo Simulation
    public static double PercolationTest(int size, int tries) {
        Chronograph timer = new Chronograph();
        PercolationSimulation simulation = new PercolationSimulation(size, tries);
        System.out.print("elements: ");
        System.out.print(size);
        System.out.print("\nratio (p*): ");
        System.out.print(simulation.getRatio());
        System.out.print("\nexecution time: ");
        return timer.elapsed("msec");
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner userInput = new Scanner(System.in);
        int mode = 0;
        while (mode != -1) {
            System.out.println("""
                    Please, choose one of the following options:
                                    
                    0 - unions of consecutive elements in Union Find data structure,
                    1 - unions of consecutive elements in Quick Union Find data structure,
                    2 - unions of consecutive elements in Weighted Quick Union Find data structure,
                    3 - percolation model tests by Monte Carlo simulation.
                                        
                    To exit, type "-1".
                    """);
            mode = userInput.nextInt();
            if (mode == -1)
                break;
            System.out.println("Please, enter the size and the number of tries: ");
            int size = userInput.nextInt();
            int tries = userInput.nextInt();
            if (mode >= 0 && mode <= 2) {
                System.out.print("UNION FIND DATA STRUCTURE TEST: ");
                if(mode==0) System.out.println("GENERIC UNION FIND");
                if(mode==1) System.out.println("QUICK UNION FIND");
                if(mode==2) System.out.println("WEIGHTED QUICK UNION FIND");
                System.out.println("\n");
                System.out.println("min/max/avg time for consecutive unions: " + Arrays.toString(UnionFindTest(mode, size, tries)) + "\n\n\n");
            }
            if (mode == 3) {
                System.out.println("PERCOLATION MODEL TEST\n");
                System.out.println(PercolationTest(size, tries) + "\n\n\n");
            }
        }
    }
}
