package org.example;

import lombok.Data;

@Data
public class MarketChequeData {
    private Long chequeId;              // SERIAL PRIMARY KEY, corresponds to cheque_id
    private String marketName;          // TEXT, corresponds to market_name
    private String chequeNumber;        // VARCHAR(10), corresponds to cheque_number
    private String chequeDate;          // DATE, corresponds to cheque_date
    private Double amount;              // NUMERIC(10, 2), corresponds to amount
    private Double tax;                 // NUMERIC(5, 2), corresponds to tax
    private Double discount;            // NUMERIC(5, 2), corresponds to discount
    private Double totalAmount;         // NUMERIC(10, 2), corresponds to total_amount
    private String paymentMethod;       // VARCHAR(10), corresponds to payment_method
    private Integer customerId;         // INT, corresponds to customer_id
    private Integer marketLocationId;   // INT, corresponds to market_location_id
    private String chequeStatus;        // VARCHAR(20), corresponds to cheque_status
    private Integer daysToClear;        // INT, corresponds to days_to_clear
    private Double cashBackAmount;      // NUMERIC(5, 2), corresponds to cash_back_amount
    private Integer loyaltyPointsEarned;// INT, corresponds to loyalty_points_earned
}
