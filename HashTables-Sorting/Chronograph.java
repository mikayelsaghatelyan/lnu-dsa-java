package com.source;

public class Chronograph {
    //timer that returns execution time in nanoseconds.
    private final double start_time;

    public Chronograph() {
        start_time = System.nanoTime();
    }

    // start the stopwatch
    public double elapsed(String unit) {
        double end_time = System.nanoTime();
        double passed_time = end_time - start_time;
        switch (unit) {
            case "sec" -> passed_time/=1000000000;
            case "msec" -> passed_time/=1000000;
            case "mcsec" -> passed_time/=1000;
        }
        return passed_time;
    }
    public double elapsed() {
        double endTime = System.nanoTime();
        return (endTime - start_time);
    }

}

