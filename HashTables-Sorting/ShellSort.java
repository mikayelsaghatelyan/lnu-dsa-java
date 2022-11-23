package com.source;

import static java.lang.Math.log;
import static java.lang.Math.pow;

class ShellSort {
    //gap sequence is N/2, N/4, N/8, ..., 1
    void sortBasic(int[] arr) {
        int n = arr.length;
        for (int interval = n / 2; interval > 0; interval /= 2) {
            for (int i = interval; i < n; i ++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= interval && arr[j - interval] > temp; j -= interval) {
                    arr[j] = arr[j - interval];
                }
                arr[j] = temp;
            }
        }
    }

    //Knuth's gap sequence is (3^n-1)/2 [or (a_n+1)= (a_n)*3 + 1]: 1, 4, 13, 40, 121, ... (reversed)
    void sortKnuth(int[] arr) {
        int n = arr.length;
        int interval = 1;
        while (interval < n / 3) interval = 3 * interval + 1;
        while (interval >= 1) {
            for (int i = interval; i < n; i++) {
                for (int j = i; j >= interval && arr[j] < arr[j - interval]; j -= interval) {
                    int temp = arr[j];
                    arr[j] = arr[j - interval];
                    arr[j - interval] = temp;
                }
            }
            interval /= 3;
        }
    }

    //Hibbard's gap sequence is (2^n-1): 1, 3, 7, 15, 31, 63, 127, 255, ..., 511 (reversed)
    void sortHibbard(int[] arr) {
        int n = arr.length;
        int i, j;
        int temp, interval;
        for (interval = (int) (pow(2, (int)( log(n)/log(2) ) )-1); interval > 0; interval /= 2) {
            for (i = interval; i < n; i ++) {
                temp = arr[i];
                for (j = i; j >= interval && arr[j - interval] > temp; j -= interval) {
                    arr[j] = arr[j - interval];
                }
                arr[j] = temp;
            }
        }
    }
}
