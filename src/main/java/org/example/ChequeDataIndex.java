package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChequeDataIndex {
    private BTree<Long> chequeIdTree = new BTree<>(16);
    private BTree<String> marketNameTree = new BTree<>(16); // Index by marketName
    private BTree<String> chequeNumberTree = new BTree<>(16); // Index by chequeNumber
    private BTree<String> chequeDateTree = new BTree<>(16); // Index by chequeDate
    private BTree<Double> amountTree = new BTree<>(16); // Index by amount
    private BTree<Double> taxTree = new BTree<>(16); // Index by tax
    private BTree<Double> discountTree = new BTree<>(16); // Index by discount
    private BTree<Double> totalAmountTree = new BTree<>(16); // Index by totalAmount
    private BTree<String> paymentMethodTree = new BTree<>(16); // Index by paymentMethod
    private BTree<Integer> customerIdTree = new BTree<>(16); // Index by customerId
    private BTree<Integer> marketLocationIdTree = new BTree<>(16); // Index by marketLocationId
    private BTree<String> chequeStatusTree = new BTree<>(16); // Index by chequeStatus
    private BTree<Integer> daysToClearTree = new BTree<>(16); // Index by daysToClear
    private BTree<Double> cashBackAmountTree = new BTree<>(16);
    private BTree<Integer> loyaltyPointsEarnedTree = new BTree<>(16); // Index by loyaltyPointsEarned

    public List<Long> getAllChequeIds() {
        return chequeIdTree.getKeys(); // This will require a method in the BTree class
    }

    // Method to add a record to all the B-trees
    public void addRecord(MarketChequeData data) {
        chequeIdTree.insert(data.getChequeId(), data);
        marketNameTree.insert(data.getMarketName(), data);
        chequeNumberTree.insert(data.getChequeNumber(), data);
        chequeDateTree.insert(data.getChequeDate(), data);
        amountTree.insert(data.getAmount(), data);
        taxTree.insert(data.getTax(), data);
        discountTree.insert(data.getDiscount(), data);
        totalAmountTree.insert(data.getTotalAmount(), data);
        paymentMethodTree.insert(data.getPaymentMethod(), data);
        customerIdTree.insert(data.getCustomerId(), data);
        marketLocationIdTree.insert(data.getMarketLocationId(), data);
        chequeStatusTree.insert(data.getChequeStatus(), data);
        daysToClearTree.insert(data.getDaysToClear(), data);
        cashBackAmountTree.insert(data.getCashBackAmount(), data);
        loyaltyPointsEarnedTree.insert(data.getLoyaltyPointsEarned(), data);
    }

    // Method to query records based on total amount within a specified range
    public List<MarketChequeData> queryByTotalAmountRange(double min, double max) {
        List<MarketChequeData> results = new ArrayList<>();

        // You can iterate through all the keys in the totalAmount tree
        for (Double totalAmountKey : totalAmountTree.getKeys()) {
            if (totalAmountKey >= min && totalAmountKey <= max) {
                MarketChequeData data = totalAmountTree.search(totalAmountKey); // Retrieves the associated MarketChequeData
                if (data != null) {
                    results.add(data);
                }
            }
        }

        // Sort results in ascending order based on total amount
        results.sort((d1, d2) -> Double.compare(d1.getTotalAmount(), d2.getTotalAmount()));

        return results;
    }

    // Search methods for each indexed field
    public MarketChequeData searchByChequeId(Long chequeId) {
        return chequeIdTree.search(chequeId);
    }

    public MarketChequeData searchByMarketName(String marketName) {
        return marketNameTree.search(marketName);
    }

    public MarketChequeData searchByChequeNumber(String chequeNumber) {
        return chequeNumberTree.search(chequeNumber);
    }

    public MarketChequeData searchByChequeDate(String chequeDate) {
        return chequeDateTree.search(chequeDate);
    }

    public MarketChequeData searchByAmount(Double amount) {
        return amountTree.search(amount);
    }

    public MarketChequeData searchByTax(Double tax) {
        return taxTree.search(tax);
    }

    public MarketChequeData searchByDiscount(Double discount) {
        return discountTree.search(discount);
    }

    public MarketChequeData searchByTotalAmount(Double totalAmount) {
        return totalAmountTree.search(totalAmount);
    }

    public MarketChequeData searchByPaymentMethod(String paymentMethod) {
        return paymentMethodTree.search(paymentMethod);
    }

    public MarketChequeData searchByCustomerId(Integer customerId) {
        return customerIdTree.search(customerId);
    }

    public MarketChequeData searchByMarketLocationId(Integer marketLocationId) {
        return marketLocationIdTree.search(marketLocationId);
    }

    public MarketChequeData searchByChequeStatus(String chequeStatus) {
        return chequeStatusTree.search(chequeStatus);
    }

    public MarketChequeData searchByDaysToClear(Integer daysToClear) {
        return daysToClearTree.search(daysToClear);
    }

    public MarketChequeData searchByCashBackAmount(Double cashBackAmount) {
        return cashBackAmountTree.search(cashBackAmount);
    }

    public MarketChequeData searchByLoyaltyPointsEarned(Integer loyaltyPointsEarned) {
        return loyaltyPointsEarnedTree.search(loyaltyPointsEarned);
    }
}
