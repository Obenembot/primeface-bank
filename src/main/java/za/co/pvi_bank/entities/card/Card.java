/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.entities.card;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import za.co.pvi_bank.entities.account.Account;

/**
 *
 * @author embot
 */
@Entity
@Table
public class Card implements Serializable{

    @Id
    @Column
    private long cardNumber;
    @Column
    private String cardType;
    @Column
    private int cardPin;
    @OneToOne
    private Account account;

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCardPin() {
        return cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.cardNumber ^ (this.cardNumber >>> 32));
        hash = 83 * hash + Objects.hashCode(this.cardType);
        hash = 83 * hash + this.cardPin;
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
        final Card other = (Card) obj;
        if (this.cardNumber != other.cardNumber) {
            return false;
        }
        if (this.cardPin != other.cardPin) {
            return false;
        }
        if (!Objects.equals(this.cardType, other.cardType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Card{" + "cardNumber=" + cardNumber + ", "
                + "cardType=" + cardType + ", cardPin=" + cardPin + ","
                +  '}';
    }
    
    
 
}
