package com.source.unweighted;

import java.util.ArrayList;

public class DFS { // depth-first search
    int s; // starting vertex on which DFS will be done
    ArrayList<Boolean> marked; // for keeping vertices that will be marked as visited
    ArrayList<Integer> edgeTo; // for keeping vertices that are connected to the starting vertex

    public DFS(IGraph G, int s) {
        this.s = s;
        this.marked = new ArrayList<>();
        this.edgeTo = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            marked.add(false);
        for (int i = 0; i < G.V(); ++i)
            edgeTo.add(0);

        DFS(G, s);
    }

    void DFS(IGraph G, int v) {
        this.marked.set(v, true);           // mark v
        for (Integer w : G.adjacent(v))     // for all vertices w adjacent to v
            if (!this.marked.get(w)) {      // if w is marked
                this.DFS(G, w);             // depth-first-search on w
                this.edgeTo.set(w, v);      // after search is done, w connects v
            }
    }

    public boolean hasPathTo(int v) {
        return marked.get(v);
    }

    public ArrayList<Integer> pathTo(int v) {
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
