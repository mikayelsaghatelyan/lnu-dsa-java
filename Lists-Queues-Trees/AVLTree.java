package com.source;

import static java.lang.Math.max;


class AVLTree {
    static class InorderIterator {
        private ObjectStack<AVLNode> nodeStack;

        InorderIterator(AVLNode node) {
            nodeStack = new ObjectStack<>();
            if (node != null)
                process(node);
        }

        private void process(AVLNode node) {
            while (node != null) {
                nodeStack.push(node);
                node = node.left;
            }
        }

        //returns whether there is next smallest number
        boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        //the next smallest number
        AVLNode next() {
            if (hasNext()) {
                AVLNode curr = nodeStack.pop();
                process(curr.right);
                return curr;
            }
            return null;
        }
    }

    static class PreorderIterator {
        private ObjectStack<AVLNode> nodeStack;

        PreorderIterator(AVLNode node) {
            nodeStack = new ObjectStack<>();
            if (node != null)
                process(node);
        }

        private void process(AVLNode node) {
            nodeStack.push(node);
        }

        //whether there is a next smallest number
        boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        //the next smallest number
        AVLNode next() {
            AVLNode node = nodeStack.pop();
            if (node.right != null)
                process(node.right);
            if (node.left != null)
                process(node.left);
            return node;
        }
    }

    static class PostorderIterator {

        private ObjectStack<AVLNode> nodeStack;

        PostorderIterator(AVLNode node) {
            nodeStack = new ObjectStack<>();
            process(node);
        }

        //finds the first leaf, stores the nodes with in-between values
        private void process(AVLNode node) {
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
        AVLNode next() {
            AVLNode node = nodeStack.pop();
            if (!nodeStack.isEmpty()) {
                if (node == nodeStack.peek().left) {
                    process(nodeStack.peek().right);  // find next leaf in right subtree
                }
            }
            return node;
        }
    }

    static class AVLNode {
        int key, height;
        AVLNode left, right;

        AVLNode(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    AVLNode root;

    AVLTree (int key) {
        root = new AVLNode(key);
    }

    int getSize(AVLNode node) {
        if (node == null)
            return 0;
        return getSize(node.left) + getSize(node.right) + 1;
    }

    int getSize() {
        return getSize(root);
    }

    int getHeight(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    //right rotation for balancing the tree
    AVLNode rotateRight(AVLNode x) {
        AVLNode y = x.left;
        AVLNode z = y.right;
        y.right = x;
        x.left = z;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    //left rotation for balancing the tree
    AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode z = y.left;
        y.left = x;
        x.right = z;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    // method for calculating the height difference of left and right subtrees (balance factor) of a node
    int getHeightDifference(AVLNode N) {
        if (N == null)
            return 0;
        return getHeight(N.left) - getHeight(N.right);
    }

    AVLNode add(AVLNode node, int key) {

        // adding the node to the tree
        if (node == null)
            return (new AVLNode(key));
        if (key < node.key)
            node.left = add(node.left, key);
        else if (key > node.key)
            node.right = add(node.right, key);
        else
            return node;

        // balancing the tree
        node.height = 1 + max(getHeight(node.left), getHeight(node.right));
        int heightDiff = getHeightDifference(node);
        if (heightDiff > 1) {
            if (key < node.left.key) {
                return rotateRight(node);
            } else if (key > node.left.key) {
                //left-right rotation
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if (heightDiff < -1) {
            if (key > node.right.key) {
                return rotateLeft(node);
            } else if (key < node.right.key) {
                //right-left rotation
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }
    
    AVLNode add(int key) {
        return add(root, key);
    }

    AVLNode minKey(AVLNode node) {
        AVLNode curr = node;
        while (curr.left != null)
            curr = curr.left;
        return curr;
    }

    // Delete a node
    AVLNode remove(AVLNode node, int key) {

        // Find the node to be deleted and remove it
        if (node == null)
            return node;
        if (key < node.key)
            node.left = remove(node.left, key);
        else if (key > node.key)
            node.right = remove(node.right, key);
        else {
            if ((node.left == null) || (node.right == null)) {
                AVLNode temp_node = null;
                if (temp_node == node.left)
                    temp_node = node.right;
                else
                    temp_node = node.left;
                if (temp_node == null) {
                    temp_node = node;
                    node = null;
                } else
                    node = temp_node;
            } else {
                AVLNode tempNode = minKey(node.right);
                node.key = tempNode.key;
                node.right = remove(node.right, tempNode.key);
            }
        }
        if (node == null)
            return node;

        // Update the balance factor of each node and balance the tree
        node.height = max(getHeight(node.left), getHeight(node.right)) + 1;
        int height_difference = getHeightDifference(node);
        if (height_difference > 1) {
            if (getHeightDifference(node.left) >= 0) {
                return rotateRight(node);
            } else {
                //left-right rotation
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if (height_difference < -1) {
            if (getHeightDifference(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                //right-left rotation
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }
    
    AVLNode remove(int key) {
        return remove(root, key);
    }

    AVLNode search(AVLNode node, int key) {
        if (node == null)
            return null;
        else if (node.key == key)
            return node;
        else if (key < node.key)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    AVLNode search(int key) {
        return search(root, key);
    }

    //method for checking whether the tree contains a node with a certain value:
    //returns true if the search method returned a node (not a null)
    boolean contains(AVLNode node, int key) {
        return (this.search(node, key) != null);
    }

    boolean contains(int key) {
        return contains(root, key);
    }
}
