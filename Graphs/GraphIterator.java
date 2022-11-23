package com.source;

import java.util.LinkedList;
import java.util.Queue;

public class GraphIterator {
    Queue<Edge> eq;
    Queue<Integer> sq;
    GraphIterator(IWGraph G) { // constructor for iterating through all edges of the graph
        eq = new LinkedList<>();
        eq.addAll(G.edges());
    }
    GraphIterator(IWGraph G, int v) { // constructor for iterating through all edges of a certain vertex in the graph
        eq = new LinkedList<>();
        eq.addAll(G.adjacent(v));
    }
    GraphIterator(IWGraph G, int v, int u, String mode) { // constructor for iterating through path between two vertices found by DFS/BFS
        sq = new LinkedList<>();
        if(mode.equals("DFS")) {
            WeightedDFS s = new WeightedDFS(G,v);
            sq.addAll(s.pathTo(u));
        }
        else if(mode.equals("BFS")) {
            WeightedBFS s = new WeightedBFS(G,v);
            sq.addAll(s.pathTo(u));
        }
        else throw new RuntimeException("invalid iterator mode. only BFS/DFS supported.");
    }

    // edge list and adjacency list iteration methods
    boolean hasNext() {
        return !eq.isEmpty();
    }
    Edge next() {
        return eq.poll();
    }
    // DFS/BFS vertex sequence iteration methods
    boolean s_hasNext() {
        return !sq.isEmpty();
    }
    Integer s_next() {
        return sq.poll();
    }

}
