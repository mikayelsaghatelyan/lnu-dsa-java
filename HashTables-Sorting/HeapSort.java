package com.source;

class HeapSort {
    //recursive method that creates max heap from the array
    private void heapify(int[] arr, int n, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[max])
            max = left;
        if (right < n && arr[right] > arr[max])
            max = right;
        if (max != i) {
            int t = arr[i];
            arr[i] = arr[max];
            arr[max] = t;
            heapify(arr, n, max);
        }
    }

    //sorting works by the principle "swap->remove->heapify"
    void sort(int[] arr, int low, int high) {
        for (int i = high / 2 - 1; i >= low; i--)
            heapify(arr, high, i);
        for (int i = high - 1; i >= low; i--) { //"remove" (the last element is not included at every next iteration)
            //swap
            int t = arr[low];
            arr[low] = arr[i];
            arr[i] = t;
            //removal is done, the next iteration will be without the last element
            //heapify
            heapify(arr, i, low);
        }
    }

    void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }
}
