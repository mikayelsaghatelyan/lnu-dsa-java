package com.source.unweighted;

import java.util.ArrayList;

public interface IGraph { // unweighted graph interface
    int V();
    int E();
    void addEdge(int a, int b);
    void removeEdge(int a, int b);
    int degree(int a);
    int maxDegree();
    int meanDegree();
    ArrayList<Integer> adjacent(int a);
    boolean hasEdge(int a, int b);
}
