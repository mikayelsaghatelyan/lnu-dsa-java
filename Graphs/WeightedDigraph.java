package com.source;

import java.util.ArrayList;

public class WeightedDigraph implements IWGraph{
    int v; //number of vertices
    ArrayList<ArrayList<Edge>> adjListOut;
    ArrayList<ArrayList<Edge>> adjListIn;

    public WeightedDigraph(int v) {
        this.v = v;
        this.adjListOut = new ArrayList<>();
        this.adjListIn = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            adjListOut.add(new ArrayList<>());
            adjListIn.add(new ArrayList<>());
        }
    }

    public int V() {
        return v;
    }

    public int E() {
        int sum = 0;
        for (ArrayList<Edge> adj : adjListOut)
            sum += adj.size();
        return sum;
    }

    public void addEdge(int src, int dst, double weight) {
        Edge e = new Edge(src,dst,weight);
        this.adjListOut.get(src).add(e);
        this.adjListIn.get(dst).add(e);
    }

    public void addEdge(int src, int dst) {
        Edge e = new Edge(src,dst,1.0);
        this.adjListOut.get(src).add(e);
        this.adjListIn.get(dst).add(e);
    }

    public void addEdge(Edge e) {
        this.adjListOut.get(e.src()).add(e);
        this.adjListIn.get(e.dst()).add(e);
    }

    public void removeEdge(Edge e) {
        int v = e.src();
        int u = e.dst(v);
        removeEdge(v,u);
    }

    public void removeEdge(int v, int u) {
        this.adjListOut.get(v).removeIf(k -> (k.v == v && k.u == u) || (k.v == u && k.u == v));
        this.adjListIn.get(u).removeIf(k -> (k.v == v && k.u == u) || (k.v == u && k.u == v));
    }

    public ArrayList<Edge> edges() {
        ArrayList<Edge> res = new ArrayList<>();
        for(ArrayList<Edge> l : adjListOut)
            res.addAll(l);
        return res;
    }

    public void printEdges(boolean t) { // true - print weights, false - don't print weights
        for(Edge e : this.edges())
            e.print(t);
        System.out.println();
    }

    public int outDegree(int v) {
        return this.adjacent(v).size();
    }

    public int inDegree(int v) {
        return this.adjacentIn(v).size();
    }

    public int degree(int v) {
        return this.inDegree(v) + this.outDegree(v);
    }

    public ArrayList<Edge> adjacent(int v) {
        return this.adjListOut.get(v);
    }

    public ArrayList<Edge> adjacentIn(int v) {
        return this.adjListIn.get(v);
    }

    public boolean hasEdge(int a, int b) {
        return false;
    }
}

