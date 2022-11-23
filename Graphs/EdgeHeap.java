package com.source;

import java.util.ArrayList;

//binary min-heap for Edge class (graph edges, compared by weight property) needed for Kruskal MST algorithm
//based on Python heapq class: https://github.com/python/cpython/blob/main/Lib/heapq.py

public class EdgeHeap {
    ArrayList<Edge> heap;

    EdgeHeap() {
        this.heap = new ArrayList<>();
    }

    public void heapPush(Edge item) {
        //Push item onto this.heap, maintaining the this.heap invariant.
        this.heap.add(item);
        siftDown(0, this.heap.size() - 1);
    }

    Edge heapPop() {
        Edge lastElt = this.heap.remove(this.heap.size() - 1);
        if (!this.heap.isEmpty()) {
            Edge returnItem = this.heap.get(0);
            this.heap.set(0, lastElt);
            siftUp(0);
            return returnItem;
        }
        return lastElt;
    }

    Edge heapReplace(Edge item) {
        Edge returnItem = this.heap.get(0);
        this.heap.set(0, item);
        siftUp(0);
        return returnItem;
    }

    Edge heapPushPop(Edge item) {
        if (!this.heap.isEmpty() && this.heap.get(0).weight < item.weight) {
            Edge temp = item;
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
        Edge newItem = this.heap.get(i);
        int child = 2 * i + 1;
        while (child < end) {
            int right = child + 1;
            if ((right < end) && !(this.heap.get(child).weight < this.heap.get(right).weight))
                child = right;
            this.heap.set(i, this.heap.get(child));
            i = child;
            child = 2 * i + 1;
        }
        this.heap.set(i, newItem);
        siftDown(startPos, i);
    }

    void siftDown(int startPos, int i) {
        Edge newItem = this.heap.get(i);
        while (i > startPos) {
            int parentPos = (i - 1) >> 1;
            Edge parent = this.heap.get(parentPos);
            if (newItem.weight < parent.weight) {
                this.heap.set(i, parent);
                i = parentPos;
                continue;
            }
            break;
        }
        this.heap.set(i, newItem);
    }

    public boolean isEmpty() {
        return heap.size()==0;
    }
}
