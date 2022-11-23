package com.source;

import java.util.*;

import static java.lang.Math.log;

public class DriverClass {
    public static boolean isSorted(int[] arr, int low, int high) {
        for (int i = low; i < high - 1; ++i) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    public static boolean isSorted(int[] arr) {
        return isSorted(arr, 0, arr.length);
    }

    public static int[] randomArray(int arrSize, int org, int bnd) {
        int[] random_array = new int[arrSize];
        Random r = new Random();
        for (int i = 0; i < arrSize; i++) {
            random_array[i] = r.nextInt(org, bnd); // random elements
        }
        return random_array;
    }

    //method for generating random strings consisting of latin alphabet (upper and lowercase) and numbers
    public static String randomString(int size) {
        StringBuilder s = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < size; ++i) {
            int k = r.nextInt(0, 3);
            if (k == 0) s.append((char) r.nextInt(65, 91));
            if (k == 1) s.append((char) r.nextInt(97, 123));
            if (k == 2) s.append((char) r.nextInt(48, 58));
        }
        return s.toString();
    }

    //method for testing the hashTable on random strings
    public static void hashTableTest(int n, int m) {
        System.out.println("RANDOM STRING HASH TABLE TEST\n");
        Primes p = new Primes();
        Random r = new Random();
        HashTable t = new HashTable(p.primes[n]);
        if (m > p.primes[n] * 95 / 100) throw new RuntimeException("too many insertions");
        for (int i = 0; i < m; ++i) {
            t.insert(randomString(r.nextInt(5, 16)));
        }
        t.print();
    }

    /**
     * method for testing the hashTable data structure on random license plate numbers of swedish format
     *
     * @param n       - parameter for setting the maximum size of the table (the size will be equal to primes[n])
     * @param start_n - parameter for setting the starting size of the table (the starting size will be equal to primes[start_n])
     * @param step_n  - intervals in the size of the table
     * @param start_m - starting number of keys inserted (maximum is primes[n]*0.95 (integer))
     * @param step_m  - intervals in the numbers of keys inserted
     */
    public static void plateHashTest(int n, int start_n, int step_n, int start_m, int step_m, boolean csv) {
        System.out.println("PLATE NUMBER HASH TABLE TEST\n");
        Primes p = new Primes();
        for (int i = start_n; i < n; i += step_n) {
            int prime_size = p.primes[i];
            for (int j = start_m; j < prime_size * 95 / 100; j += step_m) {
                PlateNumbers pt = new PlateNumbers();
                System.out.print(prime_size + "," + j + ",");
                if (!csv) System.out.println();
                pt.process(prime_size, j, csv);
            }
        }
    }

    /**
     * method for comparing execution times of sorting algorithms
     *
     * @param size  - size of the random array
     * @param range - range of elements in the random array [0; range]
     * @param tries - number of tries (different random arrays each time)
     * @param print - determines whether the times for each sorting algorithm are printed
     */
    public static void sortingComparison(int size, int range, int tries, boolean print) {
        Map<Double, String> times = new HashMap<>();
        InsertionSort is = new InsertionSort();
        HeapSort hs = new HeapSort();
        QuickSort qs = new QuickSort();
        MergeSort ms = new MergeSort();
        ShellSort ss = new ShellSort();
        RadixSort rs = new RadixSort();
        CountingSort cs = new CountingSort();
        for (int i = 0; i < tries; ++i) {
            int[] array_is = randomArray(size, 0, range + 1);
            int[] array_hs = Arrays.copyOf(array_is, size);
            int[] array_qs = Arrays.copyOf(array_is, size);
            int[] array_msi = Arrays.copyOf(array_is, size);
            int[] array_msr = Arrays.copyOf(array_is, size);
            int[] array_ssb = Arrays.copyOf(array_is, size);
            int[] array_ssk = Arrays.copyOf(array_is, size);
            int[] array_ssh = Arrays.copyOf(array_is, size);
            int[] array_rs = Arrays.copyOf(array_is, size);
            int[] array_cs = Arrays.copyOf(array_is, size);
            if (print)
                System.out.println("array size: " + array_is.length + "\narray: " + Arrays.toString(array_is) + "\n");

            // insertion sort
            Chronograph is_time = new Chronograph();
            is.sort(array_is);
            double ist = is_time.elapsed("msec");
            if (!isSorted(array_is)) throw new RuntimeException("not sorted (is)");
            times.put(ist, "insertion sort");
            if (print) System.out.println("insertion sort:\t\t\t" + ist + " msec;");

            // heap sort
            Chronograph hs_time = new Chronograph();
            hs.sort(array_hs);
            double hst = hs_time.elapsed("msec");
            if (!isSorted(array_hs)) throw new RuntimeException("not sorted (hs)");
            times.put(hst, "heap sort");
            if (print) System.out.println("heap sort:\t\t\t\t" + hst + " msec;");

            // quick sort
            Chronograph qs_time = new Chronograph();
            qs.sort(array_qs);
            double qst = qs_time.elapsed("msec");
            if (!isSorted(array_qs)) throw new RuntimeException("not sorted (qs)");
            times.put(qst, "quick sort");
            if (print) System.out.println("quick sort:\t\t\t\t" + qst + " msec;");

            // merge sort (iterative)
            Chronograph msi_time = new Chronograph();
            ms.sortIterative(array_msi);
            double msit = msi_time.elapsed("msec");
            if (!isSorted(array_msi)) throw new RuntimeException("not sorted (msi)");
            times.put(msit, "iterative merge sort");
            if (print) System.out.println("merge sort (iterative):\t" + msit + " msec;");

            // merge sort (recursive)
            Chronograph msr_time = new Chronograph();
            ms.sortIterative(array_msr);
            double msrt = msr_time.elapsed("msec");
            if (!isSorted(array_msr)) throw new RuntimeException("not sorted (msr)");
            times.put(msrt, "recursive merge sort");
            if (print) System.out.println("merge sort (recursive):\t" + msrt + " msec;");

            // Shell sort (basic)
            Chronograph ssb_time = new Chronograph();
            ss.sortBasic(array_ssb);
            double ssbt = ssb_time.elapsed("msec");
            if (!isSorted(array_ssb)) throw new RuntimeException("not sorted (ssb)");
            times.put(ssbt, "shell sort (basic gap sequence)");
            if (print) System.out.println("shell sort (default):\t" + ssbt + " msec;");

            // Shell sort (Knuth's increment)
            Chronograph ssk_time = new Chronograph();
            ss.sortKnuth(array_ssk);
            double sskt = ssk_time.elapsed("msec");
            if (!isSorted(array_ssk)) throw new RuntimeException("not sorted (ssk)");
            times.put(sskt, "shell sort (Knuth's gap sequence)");
            if (print) System.out.println("shell sort (Knuth):\t\t" + sskt + " msec;");

            // Shell sort (Hibbard's increment)
            Chronograph ssh_time = new Chronograph();
            ss.sortHibbard(array_ssh);
            double ssht = ssh_time.elapsed("msec");
            if (!isSorted(array_ssh)) throw new RuntimeException("not sorted (ssh)");
            times.put(ssht, "shell sort (Hibbard's gap sequence)");
            if (print) System.out.println("shell sort (Hibbard):\t" + ssht + " msec;");

            // counting sort
            Chronograph cs_time = new Chronograph();
            cs.sort(array_cs);
            double cst = cs_time.elapsed("msec");
            if (!isSorted(array_cs)) throw new RuntimeException("not sorted (cs)");
            times.put(cst, "counting sort");
            if (print) System.out.println("counting sort:\t\t\t" + cst + " msec;");

            // radix sort
            Chronograph rs_time = new Chronograph();
            ss.sortHibbard(array_rs);
            double rst = rs_time.elapsed("msec");
            if (!isSorted(array_rs)) throw new RuntimeException("not sorted (rs)");
            times.put(rst, "radix sort");
            if (print) System.out.println("radix sort:\t\t\t\t" + rst + " msec;\n");

            // finding the best time
            Double best_time = Collections.min(times.keySet());
            String best_sort = times.get(best_time);
            System.out.println("best time: " + best_sort + ", " + best_time + " msec.");
            System.out.println("\n\n");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int i = 0;
        int N, M, K, A, B;
        while (i != -1) {
            System.out.println("Please, select the test program to execute:\n");
            System.out.println("""
                    0 - hash table test on random strings
                    1 - hash table test on random license plates of Swedish format
                    2 - comparison of the following sorting algorithms performance:
                        - insertion sort
                        - heap sort
                        - quick sort
                        - merge sort (iterative)
                        - merge sort (recursive)
                        - shell sort (default gap sequence)
                        - shell sort (Knuth's increment gap sequence)
                        - shell sort (Hibbard's increment gap sequence)
                        - counting sort
                        - radix sort
                        """);
            i = input.nextInt();
            if (i == -1)
                break;
            switch (i) {
                case 0 -> {
                    System.out.println("enter N (hash table size will be N-th prime number, N < 1000) and M (number of insertions):");
                    N = input.nextInt();
                    M = input.nextInt();
                    hashTableTest(N, M);
                }
                case 1 -> {
                    System.out.println("""
                            enter the following parameters:
                                n       - parameter for setting the maximum size of the table (the size will be equal to primes[n])
                                start_n - parameter for setting the starting size of the table (the starting size will be equal to primes[start_n])
                                step_n  - intervals in the size of the table
                                start_m - starting number of keys inserted (maximum is primes[n] * 0.95 (integer))
                                step_m  - intervals in the numbers of keys inserted
                            """);
                    N = input.nextInt();
                    M = input.nextInt();
                    K = input.nextInt();
                    A = input.nextInt();
                    B = input.nextInt();
                    plateHashTest(N, M, K, A, B, false);
                }
                case 2 -> {
                    System.out.println("enter N (array size), M (array element range) and K (tries):");
                    N = input.nextInt();
                    M = input.nextInt();
                    K = input.nextInt();
                    sortingComparison(N, M, K, true);
                }
            }
        }
    }
}