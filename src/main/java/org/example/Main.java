package org.example;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        MarketChequeDataLoader.loadData("D:\\IdeaProjects\\FinalYearProject\\src\\main\\resources\\cheque_data.csv", tree);

//         Example of searching in the tree
        MarketChequeData data = tree.search(50000L);
        if (data != null) {
            System.out.println("Record found: " + data);
        } else {
            System.out.println("Record not found.");
        }
    }
}
