package com.source;

import java.util.Arrays;

public class WQUF {
    private int[] union_array;
    private int[] sizes;

    public WQUF(int size) {
        this.union_array = new int[size];
        this.sizes = new int[size];
        for (int i = 0; i < size; ++i) {
            union_array[i] = i;
        }
        Arrays.fill(sizes, 1);
    }

    // root method with path compression
    public int root(int i) {
        while (i != this.union_array[i]) {
            this.union_array[i]=this.union_array[this.union_array[i]];
            i = this.union_array[i];
        }
        return i;
    }

    public boolean connected(int i, int j) {
        return this.root(i) == this.root(j);
    }

    public void union(int i, int j) {
        int root_i = this.root(i);
        int root_j = this.root(j);
        if (this.sizes[root_i] < this.sizes[root_j]) {
            this.union_array[root_i] = root_j;
            this.sizes[root_j] += root_i;
        } else {
            this.union_array[root_j] = root_i;
            this.sizes[root_i] += root_j;
        }
    }

    public void print() {
        System.out.println(Arrays.toString(union_array));
    }
}