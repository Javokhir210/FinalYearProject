package org.example;

import java.util.ArrayList;
import java.util.List;

public class BTree {
    private final int T; // Allow T to be flexible, set based on data size
    private BTreeNode root;

    public BTree(int T) {
        this.T = T;
        this.root = new BTreeNode(true, T);
    }

    public BTreeNode getRoot() {
        return root;
    }

    public void insert(Long key, MarketChequeData data) {
        if (root.isFull()) {
            BTreeNode newRoot = new BTreeNode(false, T);
            newRoot.children.add(root);
            newRoot.splitChild(0, root);
            root = newRoot;
        }
        root.insertNonFull(key, data);
    }

    public MarketChequeData search(Long key) {
        return root == null ? null : root.search(key);
    }

    public static class BTreeNode {
        List<Long> keys;
        List<MarketChequeData> data;
        List<BTreeNode> children;
        boolean isLeaf;
        final int T;

        public BTreeNode(boolean isLeaf, int T) {
            this.isLeaf = isLeaf;
            this.T = T;
            this.keys = new ArrayList<>(2 * T - 1);
            this.data = new ArrayList<>(2 * T - 1);
            this.children = new ArrayList<>(2 * T);
        }

        public boolean isFull() {
            return keys.size() == 2 * T - 1;
        }

        public void insertNonFull(Long key, MarketChequeData data) {
            int i = keys.size() - 1;
            if (isLeaf) {
                while (i >= 0 && key < keys.get(i)) {
                    i--;
                }
                keys.add(i + 1, key);
                this.data.add(i + 1, data);
            } else {
                while (i >= 0 && key < keys.get(i)) {
                    i--;
                }
                i++;
                if (children.get(i).isFull()) {
                    splitChild(i, children.get(i));
                    if (key > keys.get(i)) {
                        i++;
                    }
                }
                children.get(i).insertNonFull(key, data);
            }
        }

        public void splitChild(int i, BTreeNode y) {
            BTreeNode z = new BTreeNode(y.isLeaf, T);
            for (int j = 0; j < T - 1; j++) {
                z.keys.add(y.keys.remove(T));
                z.data.add(y.data.remove(T));
            }
            if (!y.isLeaf) {
                for (int j = 0; j < T; j++) {
                    z.children.add(y.children.remove(T));
                }
            }
            keys.add(i, y.keys.remove(T - 1));
            data.add(i, y.data.remove(T - 1));
            children.add(i + 1, z);
        }

        public MarketChequeData search(Long key) {
            int i = 0;
            while (i < keys.size() && key > keys.get(i)) {
                i++;
            }
            if (i < keys.size() && keys.get(i).equals(key)) {
                return data.get(i);
            }
            return isLeaf ? null : children.get(i).search(key);
        }
    }
}


//package org.example;
//
//public class AVLTree {
//    private TreeNode root;
//
//    public TreeNode getRoot() {
//        return root;
//    }
//    public static class TreeNode {
//        Long key;
//        MarketChequeData data;
//        TreeNode left, right;
//        int height;
//
//        TreeNode(Long key, MarketChequeData data) {
//            this.key = key;
//            this.data = data;
//            this.height = 1;
//        }
//    }
//
//    // Utility function to get the height of a node
//    private int height(TreeNode node) {
//        return (node == null) ? 0 : node.height;
//    }
//
//    // Right rotate
//    private TreeNode rightRotate(TreeNode y) {
//        TreeNode x = y.left;
//        TreeNode T2 = x.right;
//
//        // Perform rotation
//        x.right = y;
//        y.left = T2;
//
//        // Update heights
//        y.height = Math.max(height(y.left), height(y.right)) + 1;
//        x.height = Math.max(height(x.left), height(x.right)) + 1;
//
//        return x; // New root
//    }
//
//    // Left rotate
//    private TreeNode leftRotate(TreeNode x) {
//        TreeNode y = x.right;
//        TreeNode T2 = y.left;
//
//        // Perform rotation
//        y.left = x;
//        x.right = T2;
//
//        // Update heights
//        x.height = Math.max(height(x.left), height(x.right)) + 1;
//        y.height = Math.max(height(y.left), height(y.right)) + 1;
//
//        return y; // New root
//    }
//
//    // Get balance factor of a node
//    private int getBalance(TreeNode node) {
//        return (node == null) ? 0 : height(node.left) - height(node.right);
//    }
//
//    // Insert a node in AVL tree
//    public void insert(Long key, MarketChequeData data) {
//        root = insertRec(root, key, data);
//    }
//
//    private TreeNode insertRec(TreeNode node, Long key, MarketChequeData data) {
//        // Perform the normal BST insertion
//        if (node == null) {
//            return new TreeNode(key, data);
//        }
//
//        int compareResult = key.compareTo(node.key);
//
//        if (compareResult < 0) {
//            node.left = insertRec(node.left, key, data);
//        } else if (compareResult > 0) {
//            node.right = insertRec(node.right, key, data);
//        } else {
//            // Duplicate keys not allowed
//            return node;
//        }
//
//        // Update height of this ancestor node
//        node.height = 1 + Math.max(height(node.left), height(node.right));
//
//        // Get the balance factor of this ancestor node to check whether it has become unbalanced
//        int balance = getBalance(node);
//
//        // If the node becomes unbalanced, then perform the necessary rotations
//
//        // Left Left Case
//        if (balance > 1 && key.compareTo(node.left.key) < 0) {
//            return rightRotate(node);
//        }
//
//        // Right Right Case
//        if (balance < -1 && key.compareTo(node.right.key) > 0) {
//            return leftRotate(node);
//        }
//
//        // Left Right Case
//        if (balance > 1 && key.compareTo(node.left.key) > 0) {
//            node.left = leftRotate(node.left);
//            return rightRotate(node);
//        }
//
//        // Right Left Case
//        if (balance < -1 && key.compareTo(node.right.key) < 0) {
//            node.right = rightRotate(node.right);
//            return leftRotate(node);
//        }
//
//        return node; // Return the (unchanged) node pointer
//    }
//
//    // Search for a node in the AVL tree
//    public MarketChequeData search(Long key) {
//        return searchRec(root, key);
//    }
//
//    private MarketChequeData searchRec(TreeNode node, Long key) {
//        if (node == null) {
//            return null; // Not found
//        }
//
//        int compareResult = key.compareTo(node.key);
//
//        if (compareResult == 0) { // Key is equal to node.key
//            return node.data; // Found
//        } else if (compareResult < 0) { // Key is less than node.key
//            return searchRec(node.left, key);
//        } else { // Key is greater, search in the right subtree
//            return searchRec(node.right, key);
//        }
//    }
//}
