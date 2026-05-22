package atminterface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Students
 */
import java.util.Date;

public class Transaction {
    private String type;      // "Deposit" or "Withdrawal"
    private double amount;
    private Date timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = new Date();
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public Date getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("%s: $%.2f on %s", type, amount, timestamp);
    }
}