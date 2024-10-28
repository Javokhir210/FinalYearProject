package org.example;

public class Main {
    public static void main(String[] args) {
        // Set T to 16 for handling approximately 10 million entries efficiently
        int T = 16;
        BTree tree = new BTree(T);

        // Load data and measure time taken
        long startTime = System.nanoTime();
        MarketChequeDataLoader.loadData("D:\\IdeaProjects\\FinalYearProject\\src\\main\\resources\\cheque_data.csv", tree);
        long endTime = System.nanoTime();
        System.out.println("Data loading time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Calculate the average amount and measure time taken
        long avgStartTime = System.nanoTime();
        double averageAmount = calculateAverageAmount(tree.getRoot());
        long avgEndTime = System.nanoTime();
        System.out.println("Average amount: " + averageAmount);
        System.out.println("Time spent calculating average amount: " + (avgEndTime - avgStartTime) / 1_000_000 + " ms");

        // Search for a specific key and measure time taken
        long searchStartTime = System.nanoTime();
        MarketChequeData data = tree.search(50000L);
        long searchEndTime = System.nanoTime();
        if (data != null) {
            System.out.println("Record found: " + data);
        } else {
            System.out.println("Record not found.");
        }
        System.out.println("Search time: " + (searchEndTime - searchStartTime) / 1_000_000 + " ms");
    }

    // Calculate the average amount for all nodes in the B-tree
    public static double calculateAverageAmount(BTree.BTreeNode root) {
        SumCount result = calculateSumAndCount(root);
        return result.count == 0 ? 0 : result.sum / result.count;
    }

    private static class SumCount {
        double sum;
        int count;

        SumCount(double sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    private static SumCount calculateSumAndCount(BTree.BTreeNode node) {
        if (node == null) {
            return new SumCount(0, 0);
        }

        SumCount result = new SumCount(0, 0);

        for (int i = 0; i < node.keys.size(); i++) {
            result.sum += node.data.get(i).getAmount();
            result.count++;
        }

        // Recursively calculate the sum and count for child nodes
        if (!node.isLeaf) {
            for (BTree.BTreeNode child : node.children) {
                SumCount childResult = calculateSumAndCount(child);
                result.sum += childResult.sum;
                result.count += childResult.count;
            }
        }

        return result;
    }
}


//package org.example;
//
//public class Main {
//    public static void main(String[] args) {
//        AVLTree tree = new AVLTree();
//
//        long startTime = System.nanoTime();
//        MarketChequeDataLoader.loadData("D:\\IdeaProjects\\FinalYearProject\\src\\main\\resources\\cheque_data.csv", tree);
//        long endTime = System.nanoTime();
//        System.out.println("Data loading time: " + (endTime - startTime) / 1_000_000 + " ms");
//
//        long avgStartTime = System.nanoTime();
//        double averageAmount = calculateAverageAmount(tree.getRoot());
//        long avgEndTime = System.nanoTime();
//        System.out.println("Average amount: " + averageAmount);
//        System.out.println("Time spent calculating average amount: " + (avgEndTime - avgStartTime) / 1_000_000 + " ms");
//
//        long searchStartTime = System.nanoTime();
//        MarketChequeData data = tree.search(50000L);
//        long searchEndTime = System.nanoTime();
//        if (data != null) {
//            System.out.println("Record found: " + data);
//        } else {
//            System.out.println("Record not found.");
//        }
//        System.out.println("Search time: " + (searchEndTime - searchStartTime) / 1_000_000 + " ms");
//    }
//
//    public static double calculateAverageAmount(AVLTree.TreeNode root) {
//        SumCount result = calculateSumAndCount(root);
//        return result.count == 0 ? 0 : result.sum / result.count;
//    }
//
//    private static class SumCount {
//        double sum;
//        int count;
//
//        SumCount(double sum, int count) {
//            this.sum = sum;
//            this.count = count;
//        }
//    }
//
//    private static SumCount calculateSumAndCount(AVLTree.TreeNode node) {
//        if (node == null) {
//            return new SumCount(0, 0);
//        }
//
//        SumCount leftResult = calculateSumAndCount(node.left);
//        SumCount rightResult = calculateSumAndCount(node.right);
//
//        double totalSum = node.data.getAmount() + leftResult.sum + rightResult.sum;
//        int totalCount = 1 + leftResult.count + rightResult.count;
//
//        return new SumCount(totalSum, totalCount);
//    }
//}