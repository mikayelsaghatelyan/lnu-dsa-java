package com.source;

import java.util.ArrayList;

public interface IWGraph { // weighted graph interface
        int V();

        int E();

        void addEdge(int v, int u, double weight);

        void addEdge(int v, int u);

        void addEdge(Edge e);

        ArrayList<Edge> edges();

        int degree(int a);

        ArrayList<Edge> adjacent(int v);

        boolean hasEdge(int a, int b);
}
