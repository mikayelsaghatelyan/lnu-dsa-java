package com.source;

import java.util.Arrays;

public class WQUF {
    private int[] union_array;
    private int[] union_sizes;

    WQUF(int size) {
        this.union_array = new int[size];
        this.union_sizes = new int[size];
        for (int i = 0; i < size; ++i)
            this.union_array[i] = i;
        Arrays.fill(this.union_sizes, 1);
    }

    // root method with path compression
    int root(int i) {
        while (i != this.union_array[i]) {
            this.union_array[i] = this.union_array[this.union_array[i]];
            i = this.union_array[i];
        }
        return i;
    }

    boolean connected(int a, int b) {
        return this.root(a) == this.root(b);
    }

    void union(int a, int b) {
        int root_a = this.root(a);
        int root_b = this.root(b);
        if (this.union_sizes[root_a] < this.union_sizes[root_b]) {
            this.union_array[root_a] = root_b;
            this.union_sizes[root_b] += root_a;
        } else {
            this.union_array[root_b] = root_a;
            this.union_sizes[root_a] += root_b;
        }
    }

    void print() {
        System.out.println(Arrays.toString(union_array));
    }
}