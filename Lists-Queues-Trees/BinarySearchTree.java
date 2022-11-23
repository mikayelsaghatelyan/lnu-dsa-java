package com.source;

import static java.lang.Math.max;

class BinarySearchTree {

    static class InorderIterator {
        private ObjectStack<BSNode> node_stack;

        InorderIterator(BSNode node) {
            node_stack = new ObjectStack<>();
            if (node != null)
                process(node);
        }

        private void process(BSNode node) {
            while (node != null) {
                node_stack.push(node);
                node = node.left;
            }
        }

        //returns whether there is next smallest number
        boolean hasNext() {
            return !node_stack.isEmpty();
        }

        //the next smallest number
        BSNode next() {
            if (hasNext()) {
                BSNode curr = node_stack.pop();
                process(curr.right);
                return curr;
            }
            return null;
        }
    }

    static class PreorderIterator {
        private ObjectStack<BSNode> nodeStack;

        PreorderIterator(BSNode node) {
            nodeStack = new ObjectStack<>();
            if (node != null)
                process(node);
        }

        private void process(BSNode node) {
            nodeStack.push(node);
        }

        //whether there is a next smallest number
        boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        //the next smallest number
        BSNode next() {
            BSNode node = nodeStack.pop();
            if (node.right != null)
                process(node.right);
            if (node.left != null)
                process(node.left);
            return node;
        }
    }

    static class PostorderIterator {

        private ObjectStack<BSNode> nodeStack;

        PostorderIterator(BSNode node) {
            nodeStack = new ObjectStack<>();
            process(node);
        }

        //finds the first leaf, stores the nodes with in-between values
        private void process(BSNode node) {
            while (node != null) {
                nodeStack.push(node);
                if (node.left != null)
                    node = node.left;
                else
                    node = node.right;
            }
        }

        //returns whether there is a next smallest number
        boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        //the next smallest number
        BSNode next() {
            BSNode node = nodeStack.pop();
            if (!nodeStack.isEmpty()) {
                if (node == nodeStack.peek().left) {
                    process(nodeStack.peek().right);  // find next leaf in right subtree
                }
            }
            return node;
        }
    }

    static class BSNode {
        int key;
        BSNode left;
        BSNode right;

        BSNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    BSNode root;

    BinarySearchTree(int key) {
        root = new BSNode(key);
    }

    int height(BSNode node) {
        if (node == null)
            return 0;
        int l = height(node.left);
        int r = height(node.right);
        return max(l, r) + 1;
    }

    int height() {
        return height(root);
    }

    int getSize(BSNode node) {
        if (node == null)
            return 0;
        return getSize(node.left) + getSize(node.right) + 1;
    }

    int getSize() {
        return getSize(root);
    }

    //method for preorder-iterating through the tree and printing the node keys.
    String preorderString(BSNode node) {
        if (node == null)
            return "";
        else {
            String s = "[" + node.key + "] ";
            s += preorderString(node.left);
            s += preorderString(node.right);
            return s;
        }
    }

    //method for inorder-iterating through the tree and printing the node keys.
    String inorderString(BSNode node) {
        if (node == null)
            return "";
        else {
            String s = inorderString(node.left);
            s += "[" + node.key + "] ";
            s += inorderString(node.right);
            return s;
        }
    }

    //method for postorder-iterating through the tree and printing the node keys.
    String postorderString(BSNode node) {
        if (node == null)
            return "";
        else {
            String s = postorderString(node.left);
            s += postorderString(node.right);
            s += "[" + node.key + "] ";
            return s;
        }
    }

    //method for adding a new node to the Binary Search Tree.
    BSNode add(BSNode node, int key) {
        if (node == null)
            return new BSNode(key);
        if (node.key > key)
            node.left = add(node.left, key);
        else if (node.key < key)
            node.right = add(node.right, key);
        return node;
    }

    BSNode add(int key) {
        return add(root, key);
    }

    BSNode remove(BSNode node, int key) {
        if (node == null)
            return null;
        if (key < node.key)
            node.left = remove(node.left, key);
        else if (key > node.key)
            node.right = remove(node.right, key);
        else {
            // node with one/no child
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            // in case of node with two children, get the smallest node in right subtree (inorder successor)
            node.key = minKey(node.right);
            // Deleting the inorder successor
            node.right = remove(node.right, node.key);
        }
        return node;
    }
    BSNode remove(int key) {
        return remove(root, key);
    }

    //finds the smallest node in the subtree
    int minKey(BSNode node) {
        int min = node.key;
        while (node.left != null) {
            min = node.left.key;
            node = node.left;
        }
        return min;
    }

    //method for removing Kth biggest element. Performs an inorder iteration and removes the Kth element counting from the end
    //mode parameter is used for showing the key of the Kth largest node during the testing (0 - don't show, 1 - show)
    BSNode removeKth(BSNode root, int k) {
        if (getSize(root) < k || k < 1) throw new RuntimeException("invalid K");
        InorderIterator it = new InorderIterator(this.root);
        BSNode curr = null;
        for (int i = 0; i < getSize(root) - k + 1; ++i) {
            curr = it.next();
        }
        assert curr != null;
        remove(root, curr.key);
        return root;
    }

    //method for searching for a node with certain key value:
    //if the search is successful, returns the node with the desired key value, otherwise returns null
    BSNode search(BSNode node, int key) {
        if (node == null)
            return null;
        else if (node.key == key)
            return node;
        else if (key < node.key)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    BSNode search(int key) {
        return search(root, key);
    }

    //method for checking whether the tree contains a node with a certain value:
    //returns true if the search method returned a node (not a null)
    boolean contains(BSNode node, int key) {
        return (this.search(node, key) != null);
    }

    boolean contains(int key) {
        return contains(root, key);
    }
}