package com.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// leftmost-child-right-sibling tree with nodes that have File class keys, for implementing the problem
// of recursively creating the tree from the content (subdirectories - files, folders) of a directory.

import static java.lang.Math.max;

class DirectoryTree {
    static class LMCNode {
        File key;
        LMCNode left; //child
        LMCNode right; //sibling

        LMCNode(File key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    LMCNode root;

    DirectoryTree(File key) {
        root = new LMCNode(key);
    }
    void walk(LMCNode node) {
        if (node != null) {
            System.out.println(node.key);
            if (node.left != null)
                walk(node.left);
            if (node.right != null)
                walk(node.right);
        }
    }

    LMCNode addChild(LMCNode node, File key) {
        if (node.left == null) {
            node.left = new LMCNode(key);
            return node.left;
        }
        LMCNode curr = node;
        while (curr.right != null) {
            curr = curr.right;
        }
        curr.right = new LMCNode(key);
        return curr.right;
    }

    int degree(LMCNode node) {
        int deg = 0;
        LMCNode lmc = node.left;
        while (lmc != null) {
            lmc = lmc.right;
            deg++;
        }
        return deg;
    }

    boolean isLeaf(LMCNode node) {
        return node.left == null;
    }

    int getSize(LMCNode node) {
        int l = 0, r = 0;
        if (node.left != null)
            l = getSize(node.left);
        if (node.right != null)
            r = getSize(node.right);
        return l + r + 1;
    }

    int height(LMCNode node) {
        int h = 0;
        LMCNode curr = node.left;
        while (curr != null) {
            h = max(h, 1 + height(curr));
            curr = curr.right;
        }
        return h;
    }

    void process(LMCNode node) {
        Path curr = Paths.get(node.key.getPath());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(curr)) {
            for (Path path : stream) {
                File file = path.toFile();
                if (file.isDirectory()) {
                    node = addChild(node, file);
                    process(node);
                } else {
                    addChild(node, file);
                }
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    void preorderPathPrint(LMCNode root) {
        if (root == null) return;
        System.out.println(root.key.getPath());
        preorderPathPrint(root.left);
        preorderPathPrint(root.right);
    }

    LMCNode find(LMCNode root, File key) {
        LMCNode node = null;
        if (root != null) {
            node = find(root.left, key);
            if (key.getPath().equals(root.key.getPath())) {
                node = root;
                return node;
            }
            node = find(root.right, key);
        }
        return node;
    }
}



