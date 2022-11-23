package com.source.unweighted;

import java.util.ArrayList;

public class CC {
    ArrayList<Boolean> marked;
    ArrayList<Integer> ids;
    int count;

    public CC(Graph G) {
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

    void DFS(Graph G, int v) {
        this.marked.set(v, true);
        this.ids.set(v, this.count);
        for (Integer w : G.adjacent(v))
            if (!this.marked.get(w))
                this.DFS(G, w);
    }

    public int getCount() {
        return this.count;
    }

    int compID(int v) {
        return this.ids.get(v);
    }

    public ArrayList<Integer> getComp(int id) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int v = 0; v < ids.size(); ++v) {
            if (ids.get(v) == id) list.add(v);
        }
        return list;
    }
}
