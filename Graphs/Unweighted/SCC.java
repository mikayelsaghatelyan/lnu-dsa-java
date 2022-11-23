package com.source.unweighted;

import java.util.ArrayList;
import java.util.Objects;

public class SCC { //Kosaraju-Sharir algorithm
    ArrayList<Boolean> marked;
    ArrayList<Integer> ids;
    int count;

    public Digraph reverse(Digraph G) {
        Digraph RG = new Digraph (G.V());
        for(int v = 0; v < G.V(); ++v)
            for(Integer w : G.adjacent(v))
                RG.addEdge(w,v);
        return RG;
    }

    SCC(Digraph G) {
        this.marked = new ArrayList<>();
        this.ids = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            this.marked.add(false);
        for (int i = 0; i < G.V(); ++i)
            this.ids.add(0);

        TopoSort t = new TopoSort(reverse(G)); // get topological order of G-reverse graph
        for (Integer v : t.reversePost()) {    // DFS on vertices from G in topological order of G-reverse
            if(!this.marked.get(v)){           // gives strongly connected components
                this.DFS(G,v);
                this.count++;
            }
        }
    }
    void DFS(Digraph G, int v) {
        this.marked.set(v, true);
        this.ids.set(v, this.count);
        for (Integer w : G.adjacent(v))
            if (!this.marked.get(w))
                this.DFS(G, w);
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
