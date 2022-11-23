package com.source;

 class QuickSort {
    //partition using "median of 3" pivot
    private int partitionMed3(int[] arr, int low, int high) {
        // choose the rightmost element as pivot
        int temp;
        int mid = low + ((high - low) / 2);
        //determining the median of 3
        int median;
        int x = arr[low] - arr[mid];
        int y = arr[mid] - arr[high];
        int z = arr[low] - arr[high];
        if (x * y > 0) median = mid;
        else if (x * z > 0) median = high;
        else median = low;
        //swapping median
        temp = arr[low];
        arr[low] = arr[median];
        arr[median] = temp;
        int pivot = arr[high];
        int i = (low - 1);
        // all elems are compared with pivot value
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                // if there is an elem smaller than pivot then it is swapped with greater element at i
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // pivot element now must be swapped with the greater element at i
        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        // returning the initial position
        return (i + 1);
    }

    //generic quick sort partition (last element as pivot)
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return (i + 1);
    }

     void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partitionMed3(arr, low, high);
            sort(arr, low, pivot - 1);
            sort(arr, pivot + 1, high);
        }
    }

    void sort(int[]arr) {
        sort(arr, 0, arr.length - 1);
    }
}


