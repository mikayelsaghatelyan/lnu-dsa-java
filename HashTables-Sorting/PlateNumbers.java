package com.source;

import java.util.Random;

import static com.source.HashTable.closestPrime;

public class PlateNumbers {

    //method for generating random swedish license plate numbers (of form ABC123 or ABC12A)
    public String randomPlateNumber() {
        Random rand = new Random();
        StringBuilder plate = new StringBuilder();
        for (int i = 0; i < 3; ++i)
            plate.append((char) rand.nextInt(65, 91));
        for (int i = 0; i < 2; ++i)
            plate.append(rand.nextInt(0, 10));
        int t = rand.nextInt(0, 2);
        if (t == 1)
            plate.append((char) rand.nextInt(65, 91));
        else
            plate.append(rand.nextInt(0, 10));
        return plate.toString();
    }

    /**
     * method for processing the plate number hashing test
     * @param num   number based on which the size of the hash table (prime number closest to n) will be determined
     * @param k_num number of keys that will be inserted into the hash table.
     **/
    public void process(int num, int k_num, boolean csv) {
        HashTable ht = new HashTable(closestPrime(num));
        int totalCols = 0; //total collisions
        double colSum = 0, colSize = 0; //collision stack variables
        double offSum = 0, offSize = 0; //scalar offset stack variables
        double dspSum = 0, dspSize = 0; //vector offset stack variables
        ObjectStack<Integer> col = new ObjectStack<>();
        ObjectStack<Integer> off = new ObjectStack<>();
        ObjectStack<Integer> dsp = new ObjectStack<>();
        for (int i = 0; i < k_num; ++i) {
            int[] res = ht.insert(randomPlateNumber());
            if (res[0] != 0) {
                totalCols += res[0];
                col.push(res[0]);
                colSize++;
            }
            if (res[1] != 0) {
                off.push(res[1]);
                offSize++;
            }
            if (res[2] != 0) {
                dsp.push(res[2]);
                dspSize++;
            }
        }

        while (!col.isEmpty()) {
            colSum += col.pop();
        }
        while (!off.isEmpty()) {
            offSum += off.pop();
        }
        while (!dsp.isEmpty()) {
            dspSum += dsp.pop();
        }
        if (csv) {
            System.out.println(totalCols+","+colSum / colSize + "," + offSum / offSize + "," + dspSum / dspSize + "," +
                    colSum / k_num + "," + offSum / k_num + "," + dspSum / k_num);
        } else {
            System.out.println("total collisions:\t"+totalCols);
            System.out.println("AVERAGES AMONG NON-ZERO VALUES");
            System.out.println("collisions:\t\t" + colSum / colSize);
            System.out.println("offset:\t\t\t" + offSum / offSize);
            System.out.println("displacement:\t" + dspSum / dspSize);
            System.out.println("OVERALL AVERAGES");
            System.out.println("collisions:\t\t" + colSum / k_num);
            System.out.println("offset:\t\t\t" + offSum / k_num);
            System.out.println("displacement:\t" + dspSum / k_num);
        }
    }
}
