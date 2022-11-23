package com.source;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim {
    ArrayList<Edge> mst; // minimum span tree
    PriorityQueue<Edge> pq; // priority queue
    ArrayList<Boolean> marked;

    Prim(WeightedGraph G) {
        this.mst = new ArrayList<>();
        this.pq = new PriorityQueue<>(new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                return Double.compare(a.weight, b.weight);
            }
        });
        this.marked = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            marked.add(false);

        this.visit(G, 0);            // visit the vertex, add all the edges of this vertex to the heap
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.poll();         // taking the edge with the least weight
            assert e != null;
            int v = e.src();
            int u = e.dst(v);

            if (this.marked.get(v) && this.marked.get(u)) // if the edge is fully marked, continue
                continue;
            this.mst.add(e);            // if not add it to the minimum spanning tree
            if (!marked.get(v))
                this.visit(G, v);       // visit the edges of vertex v if the vertex wasn't visited
            if (!marked.get(u))
                this.visit(G, u);       // visit the edges of vertex u if the vertex wasn't visited
        }
    }

    void visit(WeightedGraph G, int v) {
        this.marked.set(v, true);
        for (Edge e : G.adjacent(v))
            if (!this.marked.get(e.dst(v)))
                pq.add(e);
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
