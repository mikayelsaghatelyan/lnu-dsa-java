package com.source;

import java.util.ArrayList;

public class WeightedCC {
    ArrayList<Boolean> marked;
    ArrayList<Integer> ids;
    int count;

    WeightedCC(WeightedGraph G) {
        this.marked = new ArrayList<>();
        this.ids = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            this.marked.add(false);
        for (int i = 0; i < G.V(); ++i)
            this.ids.add(0);

        this.count = 0;
        for (int i = 0; i < G.V(); ++i)
            if (!this.marked.get(i)) {
                this.DFS(G, i);
                this.count++;
            }
    }

    void DFS(WeightedGraph G, int v) {
        this.marked.set(v, true);
        this.ids.set(v, this.count);
        for (Edge e : G.adjacent(v))
            if (!this.marked.get(e.dst(v)))
                this.DFS(G, e.dst(v));
    }

    int getCount() {
        return this.count;
    }

    int compID(int v) {
        return this.ids.get(v);
    }

    ArrayList<Integer> getComp(int id) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int v = 0; v < ids.size(); ++v) {
            if (ids.get(v) == id) list.add(v);
        }
        return list;
    }
}
