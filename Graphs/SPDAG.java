package com.source;

import java.util.ArrayList;

public class SPDAG { // Shortest Path in a Directed Acyclic Graph
    Double inf = Double.POSITIVE_INFINITY;
    ArrayList<Edge> edgeTo;
    ArrayList<Double> distTo;

    public SPDAG(WeightedDigraph G, int s) {
        edgeTo = new ArrayList<>();
        distTo = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            edgeTo.add(null);
        for (int i = 0; i < G.V(); ++i)
            distTo.add(inf);
        distTo.set(s, 0.0);
        WeightedTS dfo = new WeightedTS(G); // topologically sorting the vertices in graph
        for (Integer v : dfo.reversePost()) // for all vertices in reverse postorder of topological sort
            for (Edge e : G.adjacent(v))
                this.relax(e);              // relaxing edges of the vertex
    }

    void relax(Edge e) {
        if (this.distTo.get(e.dst()) > this.distTo.get(e.src()) + e.weight) {
            this.distTo.set(e.dst(), this.distTo.get(e.src()) + e.weight);
            this.edgeTo.set(e.dst(), e);
        }
    }

    boolean hasNegativeCycle(WeightedDigraph G) {
        for (Edge e : G.edges())
            if (distTo.get(e.dst()) > distTo.get(e.src()) + e.weight)
                return true;
        return false;
    }

    boolean hasPathTo(int v) {
        return this.distTo.get(v) < this.inf;
    }

    ArrayList<Edge> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        ArrayList<Edge> path = new ArrayList<>();
        Edge x = this.edgeTo.get(v);
        while (x != null) {
            path.add(0, x);
            x = edgeTo.get(x.src());
        }
        return path;
    }
}
