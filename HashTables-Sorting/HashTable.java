package com.source;

import java.util.Arrays;

import static java.lang.Math.abs;

public class HashTable {
    String[] table;
    int size;
    public static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n <= 1 || n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }
    public static int closestPrime(int n) {
        if (isPrime(n)) return n;
        int i=0;
        while(true) {
            if(isPrime(n+i)) return n+i;
            i++;
        }
    }
    public HashTable(int size) {
        this.size = size;
        this.table = new String[this.size];
    }

    //method to insert a new key into the hash table.
    //this method also returns number of collisions that occurred during the insertion;

    /**
     * method for inserting new key into the hash table
     * @param key String which is being inserted
     * @returns an array containing collisions, offset and displacement.
     * collisions - number of times when the program tries to place the key in an occupied position.
     * offset - "scalar" displacement - length that the key "travels" before it is placed at its final position.
     * since the probing is quadratic, the offset is equal to a square of a natural number.
     * displacement = abs(hashVal-t) - "vector" displacement - absolute distance between where key must have been
     * placed and where it is actually placed.
     */
    public int[] insert(String key) {
        int hashVal = abs(key.hashCode()) % size;
        int coll = 0; //collisions
        int offset = 0;
        int t = hashVal;
        if (table[hashVal] == null) {
            table[hashVal] = key;
        } else {
            coll++;
            for (int j = 0; j < size; j++) {
                //quadratic probing
                t = (hashVal + j * j) % size;
                offset = j * j;
                if (table[t] == null) {
                    table[t] = key;
                    break;
                }
                coll++;
            }
        }
        int displacement = abs(hashVal-t);
        return new int[]{coll, offset, displacement};
    }

    public boolean find(String key) {
        int hashVal = abs(key.hashCode()) % size;
        for (int j = 0; j < size; j++) {
            //quadratic probing
            int t = (hashVal + j * j) % size;
            if (table[t].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int len() {
        int l = 0;
        for (int i = 0; i < size; ++i) {
            if (table[i] != null)
                l++;
        }
        return l;
    }

    //prints the table of strings
    public void print() {
        System.out.println(Arrays.toString(table));
    }
}
