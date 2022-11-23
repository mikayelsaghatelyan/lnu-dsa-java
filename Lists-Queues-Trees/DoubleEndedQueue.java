package com.source;

//deque, which is basically a double-linked list
 class DoubleEndedQueue {
    static class DEQNode {
        int val;
        DoubleEndedQueue.DEQNode next;
        DoubleEndedQueue.DEQNode prev;

        DEQNode(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    //iterator for doubleEndedQueue which can go both ways via next() and prev()
     static class DEQueueIterator {
        DEQNode current;

        DEQueueIterator(DEQNode startingNode) {
            this.current = startingNode;
        }

         boolean hasNext() {
            return this.current != null;
        }

         DEQNode next() {
            return this.current.next;
        }

         boolean hasPrev() {
            return this.current != null;
        }

         DEQNode prev() {
            return this.current.prev;
        }
    }

    int size = 0;
    DEQNode head = null;
    DEQNode tail = null;

     int getSize() {
        return this.size;
    }

    //adds an element at the front of the deque
     void addFirst(int val) {
        DEQNode elem = new DEQNode(val);
        elem.next = head;
        if (size != 0) {
            head.prev = elem;
        } else {
            tail = elem;
        }
        head = elem;
        size++;
    }

    //removes the last element at the front of the deque. Returns the element so it can be used afterwards
     DEQNode removeFirst() {
        DEQNode firstElem = head;
        if (size != 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
        } else throw new RuntimeException("cannot remove from empty list (rmvFst)");
        return firstElem;
    }

    //adds an element at the back of the queue
     void addLast(int val) {
        DEQNode elem = new DEQNode(val);
        elem.prev = tail;
        if (size != 0) {
            tail.next = elem;
        } else {
            head = elem;
        }
        tail = elem;
        size++;
    }

    //removes the last element at the back of the queue. Returns the element so it can be used afterwards
     DEQNode removeLast() {
        DEQNode lastElem = tail;
        if (size != 0) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            size--;
        } else throw new RuntimeException("cannot remove from empty list (rmvLst)");
        return lastElem;
    }

     boolean isEmpty() {
        return size == 0;
    }

    //traverses deque, printing the elements front-to-back
     void printFromLeft() {
        DEQNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.print("\n");
    }

    //traverses deque, printing the elements back-to-front
     void printFromRight() {
        DEQNode curr = tail;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.prev;
        }
        System.out.print("\n");
    }

    //traverses deque front-to-back and returns an array with deque's elements
    int[] toArrayFromLeft() {
        int[] arr = new int[this.size];
        DEQNode curr = head;
        for (int i = 0; i < size; ++i) {
            arr[i] = curr.val;
            curr = curr.next;
        }
        return arr;
    }

    //traverses deque back-to-front and returns an array with deque's elements
    int[] toArrayFromRight() {
        int[] arr = new int[this.size];
        DEQNode curr = tail;
        for (int i = 0; i < size; ++i) {
            arr[i] = curr.val;
            curr = curr.prev;
        }
        return arr;
    }

    DEQueueIterator iteratorFromLeft() {
        return new DEQueueIterator(head);
    }

    DEQueueIterator iteratorFromRight() {
        return new DEQueueIterator(tail);
    }

}
