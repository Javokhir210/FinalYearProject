package org.example;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Measure time for loading data
        long startTime = System.nanoTime();
        MarketChequeDataLoader.loadData("D:\\IdeaProjects\\FinalYearProject\\src\\main\\resources\\cheque_data.csv", tree);
        long endTime = System.nanoTime();
        System.out.println("Data loading time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Measure time for calculating average amount
        long avgStartTime = System.nanoTime();
        double averageAmount = calculateAverageAmount(tree.getRoot());
        long avgEndTime = System.nanoTime();
        System.out.println("Average amount: " + averageAmount);
        System.out.println("Time spent calculating average amount: " + (avgEndTime - avgStartTime) / 1_000_000 + " ms");

        // Measure time for searching in the tree
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

    // Method to calculate average amount
    public static double calculateAverageAmount(AVLTree.TreeNode root) {
        SumCount result = calculateSumAndCount(root);
        return result.count == 0 ? 0 : result.sum / result.count;
    }

    // Helper class to store sum and count
    private static class SumCount {
        double sum;
        int count;

        SumCount(double sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    // Recursive function to calculate sum and count in one pass
    private static SumCount calculateSumAndCount(AVLTree.TreeNode node) {
        if (node == null) {
            return new SumCount(0, 0);
        }

        SumCount leftResult = calculateSumAndCount(node.left);
        SumCount rightResult = calculateSumAndCount(node.right);

        double totalSum = node.data.getAmount() + leftResult.sum + rightResult.sum;
        int totalCount = 1 + leftResult.count + rightResult.count;

        return new SumCount(totalSum, totalCount);
    }
}