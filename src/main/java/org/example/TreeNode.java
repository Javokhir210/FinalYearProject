package org.example;

public class TreeNode {
    String key;
    MarketChequeData data;
    TreeNode left, right;

    public TreeNode(String key, MarketChequeData data) {
        this.key = key;
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
