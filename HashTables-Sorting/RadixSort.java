package com.source;

import java.util.Arrays;

class RadixSort {
    void CountingSort(int[] arr, int radix) {
        int size = arr.length;
        int[] count = new int[10];
        int[] out = new int[size + 1];

        for (int i = 0; i < size; ++i)
            count[(arr[i] / radix) % 10]++;

        for (int i = 1; i < 10; ++i)
            count[i] += count[i - 1];

        for (int i = size - 1; i >= 0; --i) {
            out[count[(arr[i] / radix) % 10] - 1] = arr[i];
            count[(arr[i] / radix) % 10]--;
        }
        System.arraycopy(out, 0, arr, 0, size);
    }

    public void sort(int[] arr) {
        int size = arr.length;
        if(size==0)
            return;
//        int max = arr[0];
//        for (int i = 1; i < size; ++i)
//            if (arr[i] > max)
//                max = arr[i];
        int max = 1000;
        for (int radix = 1; max / radix > 0; radix *= 10) {
            CountingSort(arr, radix);
            //System.out.println(Arrays.toString(arr));
        }

    }
}
