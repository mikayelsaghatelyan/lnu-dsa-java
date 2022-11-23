package com.source;

import java.util.ArrayList;

public class WeightedCF { // cycle find in weighted graph
    int weightSum;
    ArrayList<Integer> color;   // 0 - white, 1 - gray, 2 - black

    WeightedCF(IWGraph G) {
        color = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            color.add(0);
    }

    boolean hasCycle(IWGraph G) {
        for (int v = 0; v < G.V(); ++v)
            if (this.color.get(v) == 0) {
                if (DFS(G, v))
                    return true;
            }
        return false;
    }

    public boolean DFS(IWGraph G, int v) {
        this.color.set(v, 1); // v marked gray
        for (Edge e : G.adjacent(v)) {
            weightSum += e.weight;
            if (this.color.get(e.dst(v)) == 1)
                return true;
            if (this.color.get(e.dst(v)) == 0 && this.DFS(G, e.dst(v)))
                return true;
        }
        this.color.set(v, 2); // v marked black
        return false;
    }


}
