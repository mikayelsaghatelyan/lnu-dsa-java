package com.source.unweighted;

import java.util.ArrayList;

public class Graph implements IGraph {
    ArrayList<ArrayList<Integer>> adjList;

    public Graph(int v) {
        this.adjList = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            adjList.add(new ArrayList<>());
        }
    }

    public int V() {
        return this.adjList.size();
    }

    public int E() {
        int sum = 0;
        for (ArrayList<Integer> vl : adjList) {
            sum += vl.size();
        }
        return sum / 2;
    }

    public void addEdge(int v, int u) {
        if (v < adjList.size() && u < adjList.size()) {
            this.adjList.get(v).add(u);
            this.adjList.get(u).add(v);
        }
    }

    public void removeEdge(int v, int u) {
        if (v < adjList.size() && u < adjList.size()) {
            this.adjList.get(v).remove((Integer)u);
            this.adjList.get(u).remove((Integer)u);
        }
    }

    public ArrayList<Integer> adjacent(int v) {
        if (v < adjList.size())
            return adjList.get(v);
        else throw new RuntimeException ("vertex out of range");
    }

    public int degree(int v) {
        return adjList.get(v).size();
    }

    public int maxDegree() {
        int max=0;
        for (ArrayList<Integer> vl : adjList)
            if (vl.size() > max)
                max = vl.size();
        return max;
    }

    public int meanDegree() {
        return 2 * this.E() / this.V();
    }

    public boolean hasEdge(int v, int u) {
        for (int i = 0; i < adjList.get(v).size(); ++i)
            if (adjList.get(v).get(i) == u)
                return true;
        return false;
    }
}






