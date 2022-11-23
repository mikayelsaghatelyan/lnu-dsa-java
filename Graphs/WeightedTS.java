package com.source;

import java.util.ArrayList;
import java.util.Collections;

public class WeightedTS { // topological sorting in weighted graph
    ArrayList<Boolean> marked;
    ArrayList<Integer> postorder;

    WeightedTS(IWGraph G) {
        this.marked = new ArrayList<>();
        this.postorder = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            this.marked.add(false);
        for (int v = 0; v < G.V(); ++v) {
            if (!this.marked.get(v)) {
                this.DFO(G, v);
            }
        }
    }

    void DFO(IWGraph G, int v) {
        this.marked.set(v, true);
        for (Edge e : G.adjacent(v))
            if (!this.marked.get(e.dst(v)))
                this.DFO(G, e.dst(v));
        this.postorder.add(v);
    }

    ArrayList<Integer> reversePost() {
        ArrayList<Integer> rev = this.postorder;
        Collections.reverse(rev);
        return rev;
    }
}

