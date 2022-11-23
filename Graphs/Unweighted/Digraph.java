package com.source.unweighted;

import java.util.ArrayList;

public class Digraph implements IGraph {
    ArrayList<ArrayList<Integer>> adjList;

    public Digraph(int v) {
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

    public void addEdge(int src, int dst) {
        if (src < adjList.size() && dst < adjList.size()) {
            this.adjList.get(src).add(dst);
        }
    }

    public void removeEdge(int src, int dst) {
        if (src < adjList.size() && dst < adjList.size()) {
            this.adjList.get(src).remove((Integer)dst);
        }
    }

    public ArrayList<Integer> adjacent(int v) {
        if (v < adjList.size())
            return adjList.get(v);
        else throw new RuntimeException ("vertex out of range");
    }

    public int outDegree(int v) {
        return adjList.get(v).size();
    }

    public int inDegree(int v) {
        int deg=0;
        for(int i=0;i<this.V();++i) {
            if(i==v)
                continue;
            for(int j=0;j<this.adjList.get(i).size();++i) {
                if(this.adjList.get(i).get(j)==v)
                    deg++;
            }
        }
        return deg;
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

    public boolean hasEdge(int src, int dst) {
        for (int i = 0; i < adjList.get(src).size(); ++i)
            if (adjList.get(src).get(i) == dst)
                return true;
        return false;
    }

    public int degree(int a) {
        throw new RuntimeException("unsupported");
    }
}
