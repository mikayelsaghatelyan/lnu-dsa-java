package com.source;

 class InsertionSort {
     void sort(int[] arr, int low, int high) {
        {
            for (int i = low + 1; i <= high; i++) {
                for (int j = i - 1; j >= low; j--) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    } else
                        break;
                }
            }
        }
    }

     void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
