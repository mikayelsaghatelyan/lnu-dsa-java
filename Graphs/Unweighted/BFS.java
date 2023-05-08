package com.source.unweighted;

import java.util.ArrayList;

public class BFS { // breadth-first search
    int s;
    ArrayList<Boolean> marked;
    ArrayList<Integer> edgeTo;

    public BFS(IGraph G, int s) {
        this.s = s;
        this.marked = new ArrayList<>();
        this.edgeTo = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            marked.add(false);
        for (int i = 0; i < G.V(); ++i)
            edgeTo.add(0);
        BFS(G, s);
    }

    void BFS(IGraph G, int v) {
        ArrayList<Integer> q = new ArrayList<>();
        q.add(0, v);
        this.marked.set(v, true);
        while (!q.isEmpty()) {
            int w = q.remove(q.size() - 1);
            for (Integer u : G.adjacent(w))
                if (!this.marked.get(u)) {
                    q.add(0, u);
                    this.marked.set(u, true);
                    this.edgeTo.set(u, w);
                }
        }
    }

    public boolean hasPathTo(int v) {
        return marked.get(v);
    }

    public ArrayList<Integer> pathTo(int v) {
        ArrayList<Integer> path = new ArrayList<>();
        if (!hasPathTo(v))
            return path;
        while (v != this.s) {
            path.add(0, v);
            v = edgeTo.get(v);
        }
        path.add(0, this.s);
        return path;
    }
}
