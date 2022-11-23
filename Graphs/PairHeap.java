package com.source;

import java.util.ArrayList;

// binary min-heap for Pair class (pairs of form [int, double]) needed for Dijkstra shortest path algorithm
// inspired by Python heapq class: https://github.com/python/cpython/blob/main/Lib/heapq.py
public class PairHeap {
    ArrayList<Pair> heap;

    PairHeap() {
        this.heap = new ArrayList<>();
    }

    public void heapPush(Pair item) {
        // push item into this.heap, maintaining the this.heap invariant.
        this.heap.add(item);
        siftDown(0, this.heap.size() - 1);
    }

    Pair heapPop() {
        Pair lastElt = this.heap.remove(this.heap.size() - 1);
        if (!this.heap.isEmpty()) {
            Pair returnItem = this.heap.get(0);
            this.heap.set(0, lastElt);
            siftUp(0);
            return returnItem;
        }
        return lastElt;
    }

    Pair heapReplace(Pair item) {
        Pair returnItem = this.heap.get(0);
        this.heap.set(0, item);
        siftUp(0);
        return returnItem;
    }

    Pair heapPushPop(Pair item) {
        if (!this.heap.isEmpty() && this.heap.get(0).dist < item.dist) {
            Pair temp = item;
            item = this.heap.get(0);
            this.heap.set(0, temp);
            siftUp(0);
        }
        return item;
    }

    void heapify() {
        int n = this.heap.size();
        for (int i = n / 2; i >= 0; --i)
            siftUp(i);
    }

    void siftUp(int i) {
        int end = this.heap.size();
        int startPos = i;
        Pair newItem = this.heap.get(i);
        int child = 2 * i + 1;
        while (child < end) {
            int right = child + 1;
            if ((right < end) && !(this.heap.get(child).dist < this.heap.get(right).dist))
                child = right;
            this.heap.set(i, this.heap.get(child));
            i = child;
            child = 2 * i + 1;
        }
        this.heap.set(i, newItem);
        siftDown(startPos, i);
    }

    void siftDown(int startPos, int i) {
        Pair newItem = this.heap.get(i);
        while (i > startPos) {
            int parentPos = (i - 1) >> 1;
            Pair parent = this.heap.get(parentPos);
            if (newItem.dist < parent.dist) {
                this.heap.set(i, parent);
                i = parentPos;
                continue;
            }
            break;
        }
        this.heap.set(i, newItem);
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }
}
