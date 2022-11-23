package com.source;

import java.util.ArrayList;
import java.util.HashSet;

public class WeightedGraph implements IWGraph{
    int v; //number of vertices
    ArrayList<ArrayList<Edge>> adjList;

    public WeightedGraph(int v) {
        this.v = v;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < v; ++i)
            adjList.add(new ArrayList<>());
    }

    public int V() {
        return this.v;
    }

    public int E() {
        int sum = 0;
        for (ArrayList<Edge> adj : adjList)
            sum += adj.size();
        return sum / 2;
    }

    public void addEdge(int v, int u, double weight) {
        Edge e = new Edge(v,u,weight);
        this.adjList.get(v).add(e);
        this.adjList.get(u).add(e);
    }

    public void addEdge(int v, int u) {
        Edge e = new Edge(v,u,1.0);
        this.adjList.get(v).add(e);
        this.adjList.get(u).add(e);
    }

    public void addEdge(Edge e) {
        int v = e.src();
        int u = e.dst(v);
        this.adjList.get(v).add(e);
        this.adjList.get(u).add(e);
    }

    public void removeEdge(Edge e) {
        int v = e.src();
        int u = e.dst(v);
        this.adjList.get(v).removeIf(k -> (k.v == v && k.u == u) || (k.v == u && k.u == v));
        this.adjList.get(u).removeIf(k -> (k.v == v && k.u == u) || (k.v == u && k.u == v));
    }

    public void removeEdge(int v, int u) {
        this.adjList.get(v).removeIf(k -> (k.v == v && k.u == u) || (k.v == u && k.u == v));
        this.adjList.get(u).removeIf(k -> (k.v == v && k.u == u) || (k.v == u && k.u == v));
    }

    public ArrayList<Edge> edges() {
        HashSet<Edge> res = new HashSet<>();
        for(ArrayList<Edge> l : adjList)
            res.addAll(l);
        return new ArrayList<>(res);
    }

    public void printEdges(boolean t) { // true - print weights, false - don't print weights
        for(Edge e : this.edges())
            e.print(t);
        System.out.println();
    }

    public int degree(int a) {
        return this.adjacent(a).size();
    }

    public ArrayList<Edge> adjacent(int v) {
        return this.adjList.get(v);
    }

    public boolean hasEdge(int a, int b) {
        return false;
    }

    public void printAdjacent(int v){
        System.out.println("Adjacency list for vertex "+v+":");
        for(Edge e : this.adjList.get(v))
            System.out.print("["+e.v + ","+e.u+"]");
        System.out.println();
    }
}
