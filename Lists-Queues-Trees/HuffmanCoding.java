package com.source;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanCoding {
    static class HNode {
        Character chr;
        Integer frq;
        HNode left;
        HNode right;


        HNode(Character chr, Integer frq, HNode left, HNode right) {
            this.chr = chr;
            this.frq = frq;
            this.left = left;
            this.right = right;
        }

        HNode(Character chr, Integer frq) {
            this.chr = chr;
            this.frq = frq;
            this.left = null;
            this.right = null;
        }
    }

    Map<Character, String> HuffmanCode;
    StringBuilder encodedText;
    HNode createHuffmanTree(String text) {
        if (text.length() == 0)
            return null;
        Map<Character, Integer> character_frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            //traversing through the string represented as array of chars each time a character is found,
            //it is put in the map with former value for this character (initially 0) increased by one.
            //By this, we will have the counts (frequencies) of all characters in string at the end of the traversing.
            character_frequencies.put(c, character_frequencies.getOrDefault(c, 0) + 1);
        }

        //creating a priority queue to store the Huffman tree nodes. Lower frequency has higher priority,
        //Comparison of the node frequencies is done using Comparator interface.
        PriorityQueue<HNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frq));
        //traversing through the elements of the map
        for (var element : character_frequencies.entrySet()) {
            //creating Huffman tree nodes with characters and their respective frequencies
            //and adding them to the priority queue with the above-mentioned priority:
            //the character with the lowest frequency will be removed the first
            queue.add(new HNode(element.getKey(), element.getValue()));
        }
        //creating the Huffman tree using the Huffman algorithm:
        //1. removing the two nodes having the lowest frequency,
        //2. creating a new node with sum of the two nodes' frequencies.
        //3. making the new node the parent of those two nodes, adding it to the priority queue.
        while (queue.size() > 1) {
            HNode left = queue.poll();
            HNode right = queue.poll();
            assert right!=null && left!=null;
            queue.add(new HNode(null, left.frq + right.frq, left, right));
        }
        HNode root = queue.poll();
        HuffmanCode = new HashMap<>();
        charToHuffmanCode(root, "", HuffmanCode);

        StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray())
        {
            sb.append(HuffmanCode.get(c));
        }
        this.encodedText = sb;
        return root;
    }

    //encoding the characters based on the Huffman algorithm
    static void charToHuffmanCode(HNode root, String code, Map<Character, String> HuffmanCode) {
        if (root == null)
            return;
        //if the current node of the Huffman tree is a leave, the code, which was assembled
        //during the recursive calls, is assigned to the character of the current node.
        if (root.left == null && root.right == null) {
            if (code.length() > 0)
                HuffmanCode.put(root.chr, code);
            else
                HuffmanCode.put(root.chr, "1");
        }
        charToHuffmanCode(root.left, code + '0', HuffmanCode);
        charToHuffmanCode(root.right, code + '1', HuffmanCode);
    }

    String getCharCode(char c) {
        for (var element : HuffmanCode.entrySet())
            if (element.getKey() == c)
                return element.getValue();
        throw new RuntimeException("no such character in the text.");
    }
}
