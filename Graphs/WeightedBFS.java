package com.source;

import java.util.ArrayList;

public class WeightedBFS {
    int s;
    ArrayList<Boolean> marked;
    ArrayList<Integer> edgeTo;

    WeightedBFS(IWGraph G, int s) {
        this.s = s;
        this.marked = new ArrayList<>();
        this.edgeTo = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            marked.add(false);
        for (int i = 0; i < G.V(); ++i)
            edgeTo.add(0);
        BFS(G, s);
    }

    void BFS(IWGraph G, int v) {
        ArrayList<Integer> q = new ArrayList<>();
        q.add(0, v);
        this.marked.set(v, true);
        while (!q.isEmpty()) {
            int w = q.remove(q.size() - 1);
            for (Edge e : G.adjacent(w))
                if (!this.marked.get(e.dst(w))) {
                    q.add(0, e.dst(w));
                    this.marked.set(e.dst(w), true);
                    this.edgeTo.set(e.dst(w), w);
                }
        }
    }

    boolean hasPathTo(int v) {
        return marked.get(v);
    }

    ArrayList<Integer> pathTo(int v) {
        ArrayList<Integer> path = new ArrayList<>();
        if (!hasPathTo(v))
            return path;
        int x = v;
        while (x != this.s) {
            path.add(0, x);
            x = edgeTo.get(x);
        }
        path.add(0, this.s);
        return path;
    }
}
