package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class BTree<K extends Comparable<K>> {
    private static final int T = 3; // Minimum degree for the B-tree
    private Node root;

    protected class Node {
        List<K> keys; // List of keys
        List<MarketChequeData> values; // List of associated values
        List<Node> children; // List of child nodes
        boolean isLeaf;

        Node() {
            keys = new ArrayList<>();
            values = new ArrayList<>();
            children = new ArrayList<>();
            isLeaf = true;
        }
    }

    public BTree(int t) {
        // Initialize the root node
        this.root = new Node();
    }

    // Method to get all keys in the B-tree
    public List<K> getKeys() {
        List<K> allKeys = new ArrayList<>();
        getKeysRecursive(root, allKeys);
        return allKeys;
    }

    // Recursive method to collect keys
    private void getKeysRecursive(Node node, List<K> allKeys) {
        if (node != null) {
            for (K key : node.keys) {
                allKeys.add(key);
            }
            for (Node child : node.children) {
                getKeysRecursive(child, allKeys); // Recurse for child nodes
            }
        }
    }

    // Insert a key-value pair into the B-tree
    public void insert(K key, MarketChequeData value) {
        Node r = root;
        if (r.keys.size() == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.isLeaf = false;
            s.children.add(r);
            splitChild(s, 0, r);
            insertNonFull(s, key, value);
        } else {
            insertNonFull(r, key, value);
        }
    }

    // Insert when the node is not full
    private void insertNonFull(Node node, K key, MarketChequeData value) {
        int i = node.keys.size() - 1;
        if (node.isLeaf) {
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            node.keys.add(i + 1, key);
            node.values.add(i + 1, value);
        } else {
            // Find the child node to descend to
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            i++;
            // Check if the child is full before descending
            if (node.children.get(i).keys.size() == 2 * T - 1) {
                splitChild(node, i, node.children.get(i));
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key, value);
        }
    }

    // Split the child at index 'i' of given node
    private void splitChild(Node parent, int index, Node fullChild) {
        Node newChild = new Node();
        newChild.isLeaf = fullChild.isLeaf;

        // Move the last T-1 keys and values from fullChild to newChild
        for (int j = 0; j < T - 1; j++) {
            newChild.keys.add(fullChild.keys.remove(T));  // Ensure fullChild has enough keys
            newChild.values.add(fullChild.values.remove(T));
        }

        // Move the top child reference to newChild
        if (!fullChild.isLeaf) {
            for (int j = 0; j < T; j++) {
                newChild.children.add(fullChild.children.remove(T));
            }
        }

        // Insert the middle key into the parent
        parent.keys.add(index, fullChild.keys.remove(T - 1)); // Move the middle key up
        parent.values.add(index, fullChild.values.remove(T - 1)); // Move the corresponding value up
        parent.children.add(index + 1, newChild); // Link the new child
    }

    // Search for a key in the B-tree and return its associated value
    public MarketChequeData search(K key) {
        return searchRec(root, key);
    }

    private MarketChequeData searchRec(Node node, K key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }
        if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
            return node.values.get(i);
        }
        if (node.isLeaf) {
            return null;
        } else {
            return searchRec(node.children.get(i), key);
        }
    }
}
