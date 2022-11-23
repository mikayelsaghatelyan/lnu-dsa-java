package com.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class DriverClass {

    //method for testing the single-linked list data structure.
    //creates a linked list of random size (range: 1 to N) filled with random integers (range: -M to M).
    //prints the created list, reverses it and prints it again.
    public static void SLLTest(int N, int M) {
        if (N < 1) throw new RuntimeException("invalid list size");
        System.out.println("SINGLE-LINKED LIST TEST\n");
        SingleLinkedList ll = new SingleLinkedList();
        Random rand = new Random();
        int size = rand.nextInt(1, N + 1);
        System.out.println("list size:\t\t\t\t" + size + "\nelement range:\t\t\t" + (-M) + " to " + M + "\n");
        for (int i = 0; i < size; ++i) {
            ll.add(rand.nextInt(-M, M + 1));
        }
        System.out.print("initial linked list:\t");
        ll.print();
        ll.reverse();
        System.out.print("reversed linked list:\t");
        ll.print();
        System.out.println("\n");
    }

    //method for testing the double-ended queue data structure.
    //creates a deque of a random size through a random combination
    //of front and back inserts and deletes (addFirst(), addLast(),
    //removeFirst() and removeLast() methods respectively).
    //the total number of "add"s and "remove"s is random (range 5 to N).
    //the number of "add"s will be at least 2 times more than the number of "remove"s.
    //inserting methods add random integers (range: -M to M).
    public static void DEQTest(int N, int M) {
        System.out.println("DOUBLE-ENDED QUEUE TEST\n");
        DoubleEndedQueue dq = new DoubleEndedQueue();
        Random rand = new Random();
        int size = rand.nextInt(5, N + 1);
        int[] methods = new int[size];
        for (int i = 0; i < size; ++i) {
            methods[i] = rand.nextInt(0, 3);
        }
        int add_count = 0, remove_count = 0;
        int bad_remove_count = 0; //counts the times method tries to remove from empty list;
        for (int i = 0; i < size; ++i) {
            switch (methods[i]) {
                case 0:
                    dq.addFirst(rand.nextInt(-M, M + 1));
                    add_count++;
                    break;
                case 1:
                    dq.addLast(rand.nextInt(-M, M + 1));
                    add_count++;
                    break;
                case 2:
                    if (!dq.isEmpty() && remove_count <= (add_count / 2)) {
                        dq.removeFirst();
                        remove_count++;
                    } else bad_remove_count++;
                    break;
                case 4:
                    if (!dq.isEmpty()) {
                        dq.removeLast();
                        remove_count++;
                    } else bad_remove_count++;
                    break;
            }
        }
        System.out.println("element range:\t\t\t" + (-M) + " to " + (M));
        System.out.print("adds:\t\t\t\t\t" + add_count + "\nremoves:\t\t\t\t");
        System.out.print(remove_count + "\nunsuccessful removes:\t" + bad_remove_count + "\n");
        System.out.println("deque size:\t\t\t\t" + dq.getSize() + "\n");
        System.out.println("deque from front:\t\t" + Arrays.toString(dq.toArrayFromLeft()));
        System.out.println("deque from back:\t\t" + Arrays.toString(dq.toArrayFromRight()));

        //iteration from left (front)
        System.out.print("iteration from front:\t");
        DoubleEndedQueue.DEQueueIterator left_iterator = dq.iteratorFromLeft();
        while (left_iterator.hasNext()) {
            System.out.print("[" + left_iterator.current.val + "] ");
            left_iterator.current = left_iterator.next();
        }
        System.out.print("\n");

        //iteration from right (back)
        System.out.print("iteration from back:\t");
        DoubleEndedQueue.DEQueueIterator right_iterator = dq.iteratorFromRight();
        while (right_iterator.hasPrev()) {
            System.out.print("[" + right_iterator.current.val + "] ");
            right_iterator.current = right_iterator.prev();
        }
        System.out.print("\n");
        System.out.println();
    }

    //method for testing the randomized queue data structure.
    //creates a randomized queue of a random size (range: 1 to N) filled with random integers (range: -M to M).
    //performs K iterations over the created queue, using the random iterator class.
    //makes random dequeues, printing the queue after each time, until the queue is empty.
    public static void RQATest(int N, int M, int K) {
        System.out.println("RANDOMIZED QUEUE TEST\n");
        RandomizedQueue rq = new RandomizedQueue();
        Random rand = new Random();
        int size = rand.nextInt(1, N + 1);
        for (int i = 0; i < size; ++i)
            rq.enqueue(rand.nextInt(-M, M + 1));
        System.out.print("initial queue: \t");
        rq.print();
        System.out.println("queue size:\t\t" + rq.getSize() + "\n");
        RandomizedQueue.RandQueueIterator it;
        for (int i = 0; i < K; ++i) {
            it = new RandomizedQueue.RandQueueIterator();
            System.out.print((i + 1) + ". random iteration:\t\t");
            while (it.hasNext()) {
                System.out.print("[" + it.next() + "]");
            }
            System.out.println();
        }
        System.out.println();
        int i = 0;
        while (rq.getSize() > 1) {
            System.out.print((i + 1) + ". ~random dequeue~\n");
            i++;
            System.out.print("dequeued element:\t" + rq.dequeue() + "\nsize after dequeue:\t" + rq.getSize() + "\nresulting queue:\t");
            rq.print();
            System.out.println();
        }
    }

    //method for testing the leftmost-child-right-sibling tree data structure.
    //creates a tree with the root node being the starting directory (java File class).
    //runs the process() method, recursively creating the nodes for the subdirectories of the starting directory, getting a LCRS tree.
    //walks the tree, printing all the contents of the directory.
    public static void LMCTest(String filePath) {
        File f = new File(filePath);
        DirectoryTree lt = new DirectoryTree(f);
        lt.process(lt.root);
        System.out.println("Directory tree size: " + lt.getSize(lt.root));
        System.out.println("Directory tree root degree: " + lt.degree(lt.root));
        lt.walk(lt.root);
    }


    //method for testing the binary search tree data structure.
    //creates a binary search tree consisting of elements of range -M to M
    //that were randomly added. The total number of "add"s is random (range: 5 to N).
    //after that, the program starts removing random keys (if they are present in the tree),
    //the total number of tries of removal is 2 times less that the number of "add"s.
    //prints the tree through inorder, preorder and postorder iterations.
    //checks whether the tree contains a random number (range: -M to M).
    //removes K-th largest node from the tree (range of K: 1 to tree size).
    //prints the tree through inorder traversal after removing the K-th largest node.
    public static void BSTTest(int N, int M) {
        System.out.println("BINARY SEARCH TREE TEST\n");
        Random rand = new Random();
        BinarySearchTree tr = new BinarySearchTree(rand.nextInt(-M, M + 1)); //root node is created through the constructor
        System.out.println("root: " + tr.root.key);
        int add_size = rand.nextInt(5, N);
        for (int i = 0; i < add_size; ++i) {
            tr.add(rand.nextInt(-M, M));
        }
        for (int i = 0; i < add_size / 2; ++i) {
            tr.remove(rand.nextInt(-M, M));
        }
        System.out.println("tree size: " + tr.getSize() + "\n");
        BinarySearchTree.InorderIterator it = new BinarySearchTree.InorderIterator(tr.root);
        System.out.print("inorder iteration:\t\t");
        while (it.hasNext())
            System.out.print("[" + it.next().key + "] ");
        System.out.println();
        BinarySearchTree.PreorderIterator prt = new BinarySearchTree.PreorderIterator(tr.root);
        System.out.print("preorder iteration:\t\t");
        while (prt.hasNext())
            System.out.print("[" + prt.next().key + "] ");
        System.out.println();
        BinarySearchTree.PostorderIterator pst = new BinarySearchTree.PostorderIterator(tr.root);
        System.out.print("postorder iteration:\t");
        while (pst.hasNext())
            System.out.print("[" + pst.next().key + "] ");
        System.out.println();
        int p = rand.nextInt(-M, M + 1);
        System.out.println("\ncontains " + p + "? " + tr.contains(p));
        int K = rand.nextInt(1, tr.getSize() + 1);
        System.out.print(K + "th biggest node: ");
        tr.removeKth(tr.root, K);
        System.out.print("\ninorder iteration after removing it: ");
        BinarySearchTree.InorderIterator it2 = new BinarySearchTree.InorderIterator(tr.root);
        while (it2.hasNext())
            System.out.print("[" + it2.next().key + "] ");
        System.out.println();
    }

    //method for testing the Huffman Coding algorithm.
    //creates a tree for Huffman codes of a file, the
    //path of which is given as parameter.
    //prints the map of the Huffman codes for all the
    //characters present in the text, as well as the
    //encoded text and a Huffman code for a random character.
    public static void HFCTest(String path) throws IOException {
        System.out.println("HUFFMAN CODING TEST\n");
        String text = Files.readString(Path.of(path));
        HuffmanCoding ht = new HuffmanCoding();
        HuffmanCoding.HNode HuffmanTree = ht.createHuffmanTree(text);
        System.out.println("initial text: " + text);
        System.out.println("Huffman code: " + ht.HuffmanCode);
        System.out.println("encoded text: " + ht.encodedText);
        Random rand = new Random();
        Character[] chars = new Character[ht.HuffmanCode.size()];
        int i = 0;
        for (var entry : ht.HuffmanCode.entrySet()) {
            chars[i] = entry.getKey();
            i++;
        }
        Character ch = chars[rand.nextInt(0, ht.HuffmanCode.size())];
        System.out.println("code for character '" + ch + "' is: " + ht.getCharCode(ch));
    }

    //method for comparing binary search tree and avl tree data structures.
    //creates BS tree and AVL tree with the same elements. The elements are random,
    //(range: -M to M), the size of the tree is determined after random "add"s and "remove"s,
    //maximum amount of "remove"s will be twice less than the maximum amount of "add"s.
    //compares total times of the creation of the two trees, as well as
    //the average time for executing add() and remove() methods.
    //outputs the size of both trees.
    //outputs the heights and height calculation time for both trees.
    //outputs the search time in both trees for the average case trees.
    //outputs the search time in both trees for the worst case trees (sorted input, may produce stack overflow errors).
    public static void comparisonTest(int N, int M) {
        System.out.println("BINARY SEARCH TREE vs AVL BALANCED TREE COMPARISON TEST\n");
        Random r = new Random();
        int random_key = r.nextInt(-M, M + 1);
        BinarySearchTree bt = new BinarySearchTree(random_key);
        AVLTree at = new AVLTree(random_key);
        int add_size = N - 1;
        int rmv_size = add_size / 3;
        int[] add_array = new int[add_size];
        int[] rmv_array = new int[rmv_size];
        for (int i = 0; i < add_size; ++i)
            add_array[i] = r.nextInt(-M, M + 1);
        for (int i = 0; i < rmv_size; ++i)
            rmv_array[i] = r.nextInt(-M, M + 1);
        Chronograph bt_create_time = new Chronograph();
        double bt_add_avg = 0;
        for (int i = 0; i < add_size; ++i) {
            Chronograph bt_add_time = new Chronograph();
            bt.root = bt.add(bt.root, add_array[i]);
            bt_add_avg += bt_add_time.elapsed();
        }
        bt_add_avg /= add_size;
        System.out.println("BS tree add() method average time:\t\t" + bt_add_avg + " nsec");
        double bt_rmv_avg = 0;
        for (int i = 0; i < rmv_size; ++i) {
            Chronograph bt_rmv_time = new Chronograph();
            bt.root = bt.remove(bt.root, rmv_array[i]);
            bt_rmv_avg += bt_rmv_time.elapsed();
        }
        bt_rmv_avg /= rmv_size;
        System.out.println("BS tree remove() method average time:\t" + bt_rmv_avg + " nsec");
        System.out.println("BS tree creation time:\t\t\t\t\t" + bt_create_time.elapsed("msec") + " msec\n");

        Chronograph at_create_time = new Chronograph();
        double at_add_avg = 0;
        for (int i = 0; i < add_size; ++i) {
            Chronograph at_add_time = new Chronograph();
            at.root = at.add(at.root, add_array[i]);
            at_add_avg += at_add_time.elapsed();
        }
        at_add_avg /= add_size;
        System.out.println("AVL tree add() method average time:\t\t" + at_add_avg + " nsec");
        double at_rmv_avg = 0;
        for (int i = 0; i < rmv_size; ++i) {
            Chronograph at_rmv_time = new Chronograph();
            at.root = at.remove(at.root, rmv_array[i]);
            at_rmv_avg += at_rmv_time.elapsed();
        }
        at_rmv_avg /= rmv_size;
        System.out.println("AVL tree remove() method average time:\t" + at_rmv_avg + " nsec");
        System.out.println("AVL tree creation time:\t\t\t\t\t" + at_create_time.elapsed("msec") + " msec\n");

        System.out.println("trees size:\t\t\t\t\t" + bt.getSize() + "\n");
        Chronograph bt_height_time = new Chronograph();
        System.out.print("BS tree height:\t\t\t\t" + bt.height(bt.root) + "\nheight calculation time:\t");
        System.out.println(bt_height_time.elapsed("nsec") + " nsec\n");
        Chronograph at_height_time = new Chronograph();
        System.out.print("AVL tree height:\t\t\t" + at.getHeight(at.root) + "\nheight calculation time:\t");
        System.out.println(at_height_time.elapsed("nsec") + " nsec\n");

        Chronograph bt_search_time = new Chronograph();
        bt.search(add_array[r.nextInt(0, add_size - 1)]);
        System.out.println("BS tree search time:\t" + bt_search_time.elapsed("msec") + " msec");
        Chronograph at_search_time = new Chronograph();
        at.search(add_array[r.nextInt(0, add_size - 1)]);
        System.out.println("AVL tree search time:\t" + at_search_time.elapsed("msec") + " msec");
        System.out.println("\n");
        //worst input performance comparison
        BinarySearchTree worst_bst = new BinarySearchTree(-M);
        for (int i = -M + 1; i < M + 1; ++i)
            worst_bst.add(i);
        Chronograph bst_worst_search_time = new Chronograph();
        for (int i : add_array) worst_bst.search(i);
        System.out.println("BS tree worst case (sorted input) search time:\t" + bst_worst_search_time.elapsed() + ",");

        AVLTree worst_avl = new AVLTree(-M);
        for (int i = -M + 1; i < M + 1; ++i)
            worst_avl.add(i);
        Chronograph avl_worst_search_time = new Chronograph();
        for (int i : add_array) worst_avl.search(i);
        System.out.println("AVL tree worst case (sorted input) search time:\t" + avl_worst_search_time.elapsed());
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int C = 0;
        while (C != -1) {
        System.out.println("please, select the test program to execute:\n");
        System.out.println("0 - Single-linked list test\n1 - Double-ended queue test\n2 - Randomized queue test\n3 - LCRS tree directory test");
        System.out.println("4 - Binary search tree test\n5 - Huffman coding test\n6 - Binary search tree vs AVL tree comparison test\n");
        System.out.println("to exit, type \"-1\"");
            int N = 1, M = 1, K = 1;
            C = input.nextInt();
            if (C == -1)
                break;
            if (C == 0 || C == 1 || C == 2 || C == 4 || C == 6) {
                System.out.println("Input N (size) and M (absolute range of elements): ");
                N = input.nextInt();
                M = input.nextInt();
            }
            if (C == 2) {
                System.out.println("Input the number of random iterations: ");
                K = input.nextInt();
            }
            switch (C) {
                case 0 -> SLLTest(N, M);
                case 1 -> DEQTest(N, M);
                case 2 -> RQATest(N, M, K);
                case 3 -> LMCTest("D:\\test");
                case 4 -> BSTTest(N, M);
                case 5 -> HFCTest("D:\\code.txt");
                case 6 -> comparisonTest(N, M);
                default -> throw new RuntimeException("no such option");
            }
        }
    }
}


