package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class MarketChequeDataLoader {
    public static void loadData(String filePath, BTree tree) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext(); // Skip header row
            String[] line;

            while ((line = reader.readNext()) != null) {
                Long chequeId = Long.valueOf(line[0]);
                String marketName = line[1];
                String chequeNumber = line[2];
                String chequeDate = line[3];
                Double amount = Double.parseDouble(line[4]);
                Double tax = Double.parseDouble(line[5]);
                Double discount = Double.parseDouble(line[6]);
                Double totalAmount = Double.parseDouble(line[7]);
                String paymentMethod = line[8];
                Integer customerId = Integer.parseInt(line[9]);
                Integer marketLocationId = Integer.parseInt(line[10]);
                String chequeStatus = line[11];
                Integer daysToClear = Integer.parseInt(line[12]);
                Double cashBackAmount = Double.parseDouble(line[13]);
                Integer loyaltyPointsEarned = Integer.parseInt(line[14]);

                // Construct the MarketChequeData object
                MarketChequeData record = new MarketChequeData();
                record.setChequeId(chequeId);
                record.setMarketName(marketName);
                record.setChequeNumber(chequeNumber);
                record.setChequeDate(chequeDate);
                record.setAmount(amount);
                record.setTax(tax);
                record.setDiscount(discount);
                record.setTotalAmount(totalAmount);
                record.setPaymentMethod(paymentMethod);
                record.setCustomerId(customerId);
                record.setMarketLocationId(marketLocationId);
                record.setChequeStatus(chequeStatus);
                record.setDaysToClear(daysToClear);
                record.setCashBackAmount(cashBackAmount);
                record.setLoyaltyPointsEarned(loyaltyPointsEarned);

                // Insert into the BTree using chequeId as the key
                tree.insert(chequeId, record);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
