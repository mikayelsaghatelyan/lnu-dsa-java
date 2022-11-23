package com.source;

import java.util.Arrays;

class QUF {
    int[] union_array;

    QUF(int size) {
        this.union_array = new int[size];
        for (int i = 0; i < union_array.length; i++)
            this.union_array[i] = i;
    }

    int root(int a) {
        while (a != this.union_array[a])
            a = this.union_array[a];
        return a;
    }

    void union(int a, int b) {
        int root_a = this.root(a);
        int root_b = this.root(b);
        this.union_array[root_a] = root_b;
    }

    public boolean connected(int a, int b) {
        return this.root(a) == this.root(b);
    }


    public int getSize() {
        return union_array.length;
    }

    public void print() {
        System.out.println(Arrays.toString(union_array));
    }
}
