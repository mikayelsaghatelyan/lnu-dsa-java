package com.source;
import java.util.Random;

class RandomizedQueue {
    private static int size;
    private static int[] queue;

    RandomizedQueue() {
        queue = new int[1];
        size = 0;
    }

    //method for resizing the randQueue array.
    private void resize(int capacity) {
        assert capacity >= size;
        int[] copy_queue = new int[capacity];
        if (size >= 0) System.arraycopy(queue, 0, copy_queue, 0, size);
        queue = copy_queue;
        copy_queue = null;
    }

    static class RandQueueIterator {
        private int[] copy_queue = new int[queue.length];
        private int copy_size = size;

        //constructor for the random randQueue iterator.
        //creates a copy of the random randQueue. This copy will then be shrunk during the random iteration with use of next() method
        RandQueueIterator() {
            System.arraycopy(queue, 0, copy_queue, 0, queue.length);
        }

        boolean hasNext() {
            return copy_size > 0;
        }

        //method for finding the next element in the random iteration of the randomized randQueue.
        //returns a random element from the copy of the random randQueue, deleting it afterwards
        int next() {
            if (!hasNext()) {
                throw new RuntimeException("no next");
            }
            Random rand = new Random();
            int random_num = rand.nextInt(0, copy_size);
            int item = copy_queue[random_num];
            if (random_num != copy_size - 1)
                //placing the last element of the randQueue in place of the random element which must then be deleted
                copy_queue[random_num] = copy_queue[copy_size - 1];
            //deleting that random element
            copy_queue[copy_size - 1] = 0;
            copy_size--;
            return item;
        }
    }

    //method for printing the randQueue array (as it is, not random)
    void print() {
        for (int i = 0; i < size; ++i) {
            System.out.print("[" + queue[i] + "]");
        }
        System.out.println();
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    //peeks at a random element from the randQueue
    int randPeek() {
        if (isEmpty())
            throw new RuntimeException("can't peek: queue is empty.");
        Random r = new Random();
        int random_num = r.nextInt(0, size);
        return queue[random_num];
    }

    //adds an element to the randQueue. Resizes the randQueue if needed
    void enqueue(int item) {
        if (size == queue.length)
            resize(queue.length * 2);
        queue[size] = item;
        size++;
    }

    //dequeues random element from the randQueue, putting the last element in its place
    int dequeue() {
        if (isEmpty())
            throw new RuntimeException("can't dequeue: queue is empty.");
        Random r = new Random();
        int random_num = r.nextInt(0, size);
        int random_elem = queue[random_num];
        if (random_num != size - 1) {
            //putting the last element in the place of the dequeued random element
            queue[random_num] = queue[size - 1];
        }
        //deleting the last element
        queue[size - 1] = 0;
        size--;
        if (size > 0 && size == queue.length / 4)
            resize(queue.length / 2);
        return random_elem;
    }
}