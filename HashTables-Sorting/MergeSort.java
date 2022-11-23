package com.source;

class MergeSort {
    //method for merging the sorted parts of array
    private void merge(int[] arr, int left, int mid, int right) {

        int leftArrSize = mid - left + 1;
        int rightArrSize = right - mid;
        int[] leftArr = new int[leftArrSize];
        int[] rightArr = new int[rightArrSize];

        for (int i = 0; i < leftArrSize; ++i)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < rightArrSize; ++j)
            rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < leftArrSize && j < rightArrSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < leftArrSize) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < rightArrSize) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    //recursive sorting
    private void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    void sortRecursive(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    void sortIterative(int[] arr) {
        int size = arr.length;
        int[] temp = new int[size];
        int left, right, end;
        int i, j, k, m;
        for (k = 1; k < size; k *= 2) {
            //sorting partial arrays
            for (left = 0; left < size - k; left += k * 2) {
                right = left + k;
                end = right + k;
                if (end > size)
                    end = size;
                m = left;
                i = left;
                j = right;
                //merging the sorted partial arrays
                while (i < right && j < end) {
                    if (arr[i] < arr[j]) {
                        temp[m] = arr[i];
                        i++;
                    } else { //arr[i]>=arr[j]
                        temp[m] = arr[j];
                        j++;
                    }
                    m++;
                }
                while (i < right) {
                    temp[m] = arr[i];
                    i++;
                    m++;
                }
                while (j < end) {
                    temp[m] = arr[j];
                    j++;
                    m++;
                }
                for (m = left; m < end; m++) {
                    arr[m] = temp[m];
                }
            }
        }
    }
}
