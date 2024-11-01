package org.example;

public class Main {
    public static void main(String[] args) {

        // Create an instance of ChequeDataIndex for managing multiple B-trees
        ChequeDataIndex index = new ChequeDataIndex();

        // Load data from CSV and measure loading time
        long startTime = System.nanoTime();
        MarketChequeDataLoader.loadData("D:\\IdeaProjects\\FinalYearProject\\src\\main\\resources\\cheque_data.csv", index);
        long endTime = System.nanoTime();
        System.out.println("Data loading time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Calculate the average amount and measure the time taken (calculate from all records)
        long avgStartTime = System.nanoTime();
        double averageAmount = calculateAverageAmount(index);
        long avgEndTime = System.nanoTime();
        System.out.println("Average amount: " + averageAmount);
        System.out.println("Time spent calculating average amount: " + (avgEndTime - avgStartTime) / 1_000_000 + " ms");

        // Sample search operation for specific fields
        Long specificChequeId = 12345L; // Example Cheque ID to search
        long searchStartTime = System.nanoTime();
        MarketChequeData chequeData = index.searchByChequeId(specificChequeId);
        long searchEndTime = System.nanoTime();
        if (chequeData != null) {
            System.out.println("Record found by Cheque ID: " + chequeData);
        } else {
            System.out.println("Record not found for Cheque ID: " + specificChequeId);
        }
        System.out.println("Time spent searching by Cheque ID: " + (searchEndTime - searchStartTime) / 1_000_000 + " ms");

        String specificMarketName = "Market A"; // Example market name to search
        searchStartTime = System.nanoTime();
        chequeData = index.searchByMarketName(specificMarketName);
        searchEndTime = System.nanoTime();
        if (chequeData != null) {
            System.out.println("Record found by Market Name: " + chequeData);
        } else {
            System.out.println("Record not found for Market Name: " + specificMarketName);
        }
        System.out.println("Time spent searching by Market Name: " + (searchEndTime - searchStartTime) / 1_000_000 + " ms");
    }

    // Calculate average amount from all records in the index
    public static double calculateAverageAmount(ChequeDataIndex index) {
        double totalSum = 0.0;
        int totalCount = 0;

        // Loop through all cheque IDs
        for (Long chequeId : index.getAllChequeIds()) {
            MarketChequeData data = index.searchByChequeId(chequeId);
            if (data != null) {
                totalSum += data.getAmount();
                totalCount++;
            }
        }

        return totalCount == 0 ? 0 : totalSum / totalCount; // Prevent division by zero
    }

}
