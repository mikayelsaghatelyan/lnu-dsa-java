package com.source;

class Chronograph {
    private final double start_time;

    Chronograph() {
        start_time = System.nanoTime();
    }

    // start the stopwatch
    double elapsed(String unit) {
        double end_time = System.nanoTime();
        double passed_time = end_time - start_time;
        switch (unit) {
            case "sec" -> passed_time/=1000000000;
            case "msec" -> passed_time/=1000000;
            case "mcsec" -> passed_time/=1000;
        }
        return passed_time;
    }
    double elapsed() {
        double end_time = System.nanoTime();
        return (end_time - start_time);
    }

}

