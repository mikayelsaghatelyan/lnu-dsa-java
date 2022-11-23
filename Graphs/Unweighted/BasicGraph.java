package com.source.unweighted;

import java.util.Vector;

public class BasicGraph { // simplest graph implementation
    int vertices;
    Vector<Integer[]> edgeList;

    int V() {
        return this.vertices;
    }

    int E() {
        return this.edgeList.size();
    }

    void addEdge(int v, int w) {
        if (v < this.vertices && w < this.vertices) {
            edgeList.add(new Integer[]{v, w});
        }
    }

    Vector<Integer> adjacent(int v) {
        Vector<Integer> list = new Vector<>();
        for (Integer[] edge : edgeList) {
            if (v == edge[0])
                list.add(edge[0]);
            else if (v == edge[1])
                list.add(edge[1]);
        }
        return list;
    }

    int degree(int v) {
        int d = 0;
        for (Integer[] edge : edgeList) {
            if (v == edge[0])
                d++;
            else if (v == edge[1])
                d++;
        }
        return d;
    }

    int maxDegree() {
        int max = 0;
        for (int i = 0; i < vertices; ++i) {
            if (degree(i) > max)
                max = degree(i);
        }
        return max;
    }

    int meanDegree() {
        return 2 * this.E() / this.V();
    }

    boolean hasEdge(int v, int u) {
        for (Integer[] edge : edgeList) {
            if (v == edge[0] && u == edge[1])
                return true;
            else if (v == edge[1] && u == edge[0])
                return true;
        }
        return false;
    }
}
