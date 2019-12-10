/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.entities.account;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import za.co.pvi_bank.entities.card.Card;

/**
 *
 * @author embot
 */
@Entity
@Table
public class Account implements Serializable {

    @Column
    @Id
    private long accountNumber;
    @Column
    private String accountType;
    @Column
    private double balance;
    @Column
    private String accountStatus;
    @OneToOne
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    
    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.accountNumber ^ (this.accountNumber >>> 32));
        hash = 67 * hash + Objects.hashCode(this.accountType);
        hash = 67 * hash + Objects.hashCode(this.accountStatus);
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
        final Account other = (Account) obj;
        if (this.accountNumber != other.accountNumber) {
            return false;
        }
        if (!Objects.equals(this.accountType, other.accountType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account{" + "accountNumber=" + accountNumber + ", accountType="
                + accountType + ", balance=" + balance + ", accountStatus=" + 
                accountStatus +  '}';
    }

  

}
