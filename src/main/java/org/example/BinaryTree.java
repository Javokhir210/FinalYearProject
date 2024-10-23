package org.example;

public class BinaryTree {
    private TreeNode root;

    private static class TreeNode {
        String key;
        MarketChequeData data;
        TreeNode left, right;

        TreeNode(String key, MarketChequeData data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Insert a new node in the binary tree
    public void insert(Long key, MarketChequeData data) {
        root = insertRec(root, String.valueOf(key), data);
    }

    private TreeNode insertRec(TreeNode root, String key, MarketChequeData data) {
        if (root == null) {
            return new TreeNode(key, data);
        }

        // Compare strings using compareTo
        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key, data);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key, data);
        }
        return root;
    }

    // Search for a node in the binary tree
    public MarketChequeData search(Long key) {
        return searchRec(root, key);
    }

    private MarketChequeData searchRec(TreeNode root, Long key) {
        if (root == null) {
            return null;
        }

        // Direct comparison for equality and compareTo for less/greater
        if (key.equals(root.key)) {
            return root.data;
        }

        if (key.compareTo(Long.valueOf(root.key)) < 0) {
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }
}
