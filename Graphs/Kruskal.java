package com.source;

import java.util.ArrayList;

public class Kruskal {
    ArrayList<Edge> mst; // minimum spanning tree
    EdgeHeap pq;
    WQUF uf; // union find

    Kruskal(WeightedGraph G) {
        this.mst = new ArrayList<>();
        this.pq = new EdgeHeap();
        this.uf = new WQUF(G.V());
        for (Edge e : G.edges())
            pq.heapPush(e);

        while (!this.pq.isEmpty() && this.mst.size() < G.V() - 1) {
            Edge e = pq.heapPop();          // take the edge with the least weight
            assert e != null;               // take its vertices
            int v = e.src();
            int u = e.dst(v);
            if (!uf.connected(v, u)) {      // if it's NOT connected to the previous vertices (otherwise we will create a cycle)
                uf.union(v, u);             // connect them with union
                mst.add(e);                 // add the edge to minimum-spanning-tree
            }
        }
    }

    ArrayList<Edge> edges() {
        return this.mst;
    }

    int weight() {
        int sum = 0;
        for (Edge e : mst) {
            sum += e.weight;
        }
        return sum;
    }
}
