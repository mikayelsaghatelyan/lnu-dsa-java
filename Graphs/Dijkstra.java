package com.source;

import java.util.ArrayList;

public class Dijkstra {
    Double inf = Double.POSITIVE_INFINITY;
    ArrayList<Edge> edgeTo;
    ArrayList<Double> distTo;
    PairHeap pq;

    public Dijkstra(WeightedDigraph G, int s) {
        edgeTo = new ArrayList<>();
        distTo = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i) {
            edgeTo.add(null);
            distTo.add(inf);
        }
        pq = new PairHeap();
        distTo.set(s, 0.0);
        pq.heapPush(new Pair(s, 0.0));
        while (!pq.isEmpty()) {
            int v = pq.heapPop().v;
            for (Edge e : G.adjacent(v))
                relax(e);
        }
    }

    void relax(Edge e) {
        if (this.distTo.get(e.dst()) > this.distTo.get(e.src()) + e.weight) {
            this.distTo.set(e.dst(), this.distTo.get(e.src()) + e.weight);
            this.edgeTo.set(e.dst(), e);
            for (int i = 0; i < pq.heap.size(); ++i) {
                Pair p = pq.heap.get(i);
                if (p.v == e.dst()) {
                    this.pq.heap.set(i, new Pair(e.dst(), this.distTo.get(e.dst())));
                    pq.heapify();
                    break;
                }
            }
            this.pq.heapPush(new Pair(e.dst(), this.distTo.get(e.dst())));
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
