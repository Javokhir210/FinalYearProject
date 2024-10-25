package org.example;

public class TreeNode {
    Long key;
    MarketChequeData data;
    TreeNode left, right;
    int height;

    public TreeNode(Long key, MarketChequeData data) {
        this.key = key;
        this.data = data;
        this.height = 1;
    }
}
