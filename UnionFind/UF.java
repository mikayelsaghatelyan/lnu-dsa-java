package com.source;

import java.util.Arrays;
class UF {
    private int[] union_array;

    UF(int size) {
        this.union_array = new int[size];
        for (int i = 0; i < union_array.length; i++)
            union_array[i] = i;
    }

    void union(int a, int b) {
        int a_index = this.union_array[a];
        int b_index = this.union_array[b];
        for (int i = 0; i < union_array.length; ++i)
            if (union_array[i] == a_index)
                this.union_array[i] = b_index;
    }

    boolean connected(int a, int b) {
        return this.union_array[a] == this.union_array[b];
    }

    int getSize() {
        return union_array.length;
    }

    void print() {
        System.out.println(Arrays.toString(union_array));
    }

}
