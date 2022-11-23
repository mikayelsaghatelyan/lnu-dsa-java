package com.source.unweighted;

import java.util.ArrayList;
import java.util.Collections;

public class TopoSort { // topological sorting
    ArrayList<Boolean> marked;
    ArrayList<Integer> postorder;

    public TopoSort(IGraph G) {
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

    void DFO(IGraph G, int v) {
        this.marked.set(v, true);
        for (Integer w : G.adjacent(v))
            if (!this.marked.get(w))
                this.DFO(G, w);
        this.postorder.add(v);
    }

    public ArrayList<Integer> reversePost() {
        ArrayList<Integer> rev = this.postorder;
        Collections.reverse(rev);
        return rev;
    }
}
