package org.example;

public class AVLTree {
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }
    public static class TreeNode {
        Long key;
        MarketChequeData data;
        TreeNode left, right;
        int height;

        TreeNode(Long key, MarketChequeData data) {
            this.key = key;
            this.data = data;
            this.height = 1;
        }
    }

    // Utility function to get the height of a node
    private int height(TreeNode node) {
        return (node == null) ? 0 : node.height;
    }

    // Right rotate
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // New root
    }

    // Left rotate
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // New root
    }

    // Get balance factor of a node
    private int getBalance(TreeNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Insert a node in AVL tree
    public void insert(Long key, MarketChequeData data) {
        root = insertRec(root, key, data);
    }

    private TreeNode insertRec(TreeNode node, Long key, MarketChequeData data) {
        // Perform the normal BST insertion
        if (node == null) {
            return new TreeNode(key, data);
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = insertRec(node.left, key, data);
        } else if (compareResult > 0) {
            node.right = insertRec(node.right, key, data);
        } else {
            // Duplicate keys not allowed
            return node;
        }

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node to check whether it has become unbalanced
        int balance = getBalance(node);

        // If the node becomes unbalanced, then perform the necessary rotations

        // Left Left Case
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Return the (unchanged) node pointer
    }

    // Search for a node in the AVL tree
    public MarketChequeData search(Long key) {
        return searchRec(root, key);
    }

    private MarketChequeData searchRec(TreeNode node, Long key) {
        if (node == null) {
            return null; // Not found
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult == 0) { // Key is equal to node.key
            return node.data; // Found
        } else if (compareResult < 0) { // Key is less than node.key
            return searchRec(node.left, key);
        } else { // Key is greater, search in the right subtree
            return searchRec(node.right, key);
        }
    }
}
