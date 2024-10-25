package org.example;

import lombok.Data;

@Data
public class MarketChequeData {
    private Long chequeId;
    private String marketName;
    private String chequeNumber;
    private String chequeDate;
    private Double amount;
    private Double tax;
    private Double discount;
    private Double totalAmount;
    private String paymentMethod;
    private Integer customerId;
    private Integer marketLocationId;
    private String chequeStatus;
    private Integer daysToClear;
    private Double cashBackAmount;
    private Integer loyaltyPointsEarned;
}
