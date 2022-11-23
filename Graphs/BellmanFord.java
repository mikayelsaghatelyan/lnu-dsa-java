package com.source;

import java.util.ArrayList;

public class BellmanFord {
    Double inf = Double.POSITIVE_INFINITY;
    ArrayList<Edge> edgeTo;
    ArrayList<Double> distTo;
    ArrayList<Boolean> onQueue;
    ArrayList<Integer> queue;

    BellmanFord(WeightedDigraph G, int s) {
        // checking for negative cycles
        SPDAG nc = new SPDAG(G, 0);
        if (nc.hasNegativeCycle(G))
            throw new RuntimeException("can't perform Bellman-Ford algorithm: negative cycle(s) detected.");

        edgeTo = new ArrayList<>();
        distTo = new ArrayList<>();
        onQueue = new ArrayList<>();
        queue = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i) {
            this.edgeTo.add(null);
            this.distTo.add(inf);
            this.onQueue.add(false);
        }
        this.distTo.set(s, 0.0);
        this.onQueue.set(s, true);
        this.queue.add(s);

        while (!queue.isEmpty()) {
            System.out.println("relaxing");
            int v = this.queue.remove(this.queue.size() - 1);
            this.onQueue.set(v, false);
            this.relax(G, v);
        }
    }

    void relax(WeightedDigraph G, int v) {
        for (Edge e : G.adjacent(v)) {
            int w = e.dst();
            if (this.distTo.get(w) > this.distTo.get(v) + e.weight) {
                this.distTo.set(w, this.distTo.get(v) + e.weight);
                this.edgeTo.set(w, e);
                if (!this.onQueue.get(w)) {
                    this.queue.add(0, w);
                    this.onQueue.set(w, true);
                }
            }
        }
    }

    boolean hasPathTo(int v) {
        return this.distTo.get(v) < this.inf;
    }

    ArrayList<Edge> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        ArrayList<Edge> path = new ArrayList<>();
        Edge x = this.edgeTo.get(v);
        while (x != null) {
            path.add(0, x);
            x = edgeTo.get(x.src());
        }
        return path;
    }
}
