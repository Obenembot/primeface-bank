/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.entities.transactions;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author embot
 */
@Entity
@Table
public class Transactions implements Serializable {

    @Id
    @Column(name = "transaction_Id")
    private long transactionId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "account_balance")
    private double accountBalance;

    @Column(name = "reference")
    private String reference;

    @Column(name = "transaction_date")
    private String localDate;

    public Transactions(long transactionId, String transactionType, long accountNumber, 
            double transactionAmount, double accountBalance, String reference, String localDate) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.accountBalance = accountBalance;
        this.reference = reference;
        this.localDate = localDate;
    }

    public Transactions() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.transactionId ^ (this.transactionId >>> 32));
        hash = 67 * hash + Objects.hashCode(this.transactionType);
        hash = 67 * hash + (int) (this.accountNumber ^ (this.accountNumber >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transactions other = (Transactions) obj;
        if (this.transactionId != other.transactionId) {
            return false;
        }
        if (this.accountNumber != other.accountNumber) {
            return false;
        }
        if (Double.doubleToLongBits(this.accountBalance) !=
                Double.doubleToLongBits(other.accountBalance)) {
            return false;
        }
        if (!Objects.equals(this.transactionType, other.transactionType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transactions{" + "transactionId=" + transactionId + 
                ", transactionType=" + transactionType + ", accountNumber="
                + accountNumber + ", transactionAmount=" + transactionAmount + 
                ", accountBalance=" + accountBalance + ", reference=" + reference 
                + ", localDate=" + localDate + '}';
    }

    
}
