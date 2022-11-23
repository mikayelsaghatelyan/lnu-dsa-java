package com.source;

import java.util.ArrayList;
import java.util.Objects;

public class WeightedSCC { //Kosaraju-Sharir algorithm for weighted graphs
    ArrayList<Boolean> marked;
    ArrayList<Integer> ids;
    int count;

    public WeightedDigraph reverse(WeightedDigraph G) {
        WeightedDigraph RG = new WeightedDigraph(G.V());
        for(int v = 0; v < G.V(); ++v)
            for(Edge e : G.adjacent(v))
                RG.addEdge(e.dst(v),v);
        return RG;
    }

    WeightedSCC(WeightedDigraph G) {
        this.marked = new ArrayList<>();
        this.ids = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            this.marked.add(false);
        for (int i = 0; i < G.V(); ++i)
            this.ids.add(0);

        WeightedTS t = new WeightedTS(reverse(G)); // get topological order of G-reverse graph

        for (Integer v : t.reversePost()) {    // DFS on vertices from G in topological order of G-reverse
            if (!this.marked.get(v)) {           // gives strongly connected components
                this.DFS(G, v);
                this.count++;
            }
        }
    }
    
    void DFS(WeightedDigraph G, int v) {
        this.marked.set(v, true);
        this.ids.set(v, this.count);
        for (Edge e: G.adjacent(v))
            if (!this.marked.get(e.dst(v)))
                this.DFS(G, e.dst(v));
    }

    boolean connected(int v, int u) {
        return Objects.equals(this.ids.get(v), this.ids.get(u));
    }

    ArrayList<Integer> getComp(int id) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int v = 0; v < ids.size(); ++v) {
            if (ids.get(v) == id) list.add(v);
        }
        return list;
    }
}
