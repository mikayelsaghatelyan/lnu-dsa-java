package com.source;

class CountingSort {
    void sort(int[] arr) {
        int size = arr.length;
        int max = arr[0];
        for (int i = 1; i < size; ++i)
            if (arr[i] > max)
                max = arr[i];
        int[] count = new int[max + 1];
        int[] out = new int[size + 1];
        for (int val : arr)
            count[val]++;
        for (int i = 1; i < max + 1; ++i)
            count[i] += count[i - 1];

        for (int i = size - 1; i >= 0; --i) {
            out[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        System.arraycopy(out, 0, arr, 0, size);
    }
}
