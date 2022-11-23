package com.source;

public class Edge {
        int v, u;
        double weight;
        Edge(int v, int u, double weight) {
            this.v = v;
            this.u = u;
            this.weight = weight;
        }
        Edge(int v, int u) {
            this.v = v;
            this.u = u;
            this.weight = 1.0;
        }
        int src() {
            return this.v;
        } // either (Graph), source (Digraph)
        int dst(int v) { //other (Graph), destination from v (Digraph)
            return (v == this.v ? this.u : this.v);
        }
        int dst() { //destination
            return u;
        }

        void print() {
            System.out.print("[" + v + ", " + u + ", " + weight + "]");
        }
        void print(boolean t) {
            System.out.print("[" + v + ", " + u);
            if(t)
                System.out.print(", " + weight);
            System.out.print("]");
        }
    }

