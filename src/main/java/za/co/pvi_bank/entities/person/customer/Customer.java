/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.entities.person.customer;

import java.io.Serializable;
import java.util.List;
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
public class Customer implements Serializable {

    @Id
    @Column
    private long idNumber;
    @Column
    private String name;
    @Column
    private String surname;

    @Column
    private String gender;
    @Column
    private String maritalStatus;
    @Column
    private long phoneNumber;
    @Column
    private String houseAddress;
    @Column
    private String email;
    @OneToOne
    private List<Account> accountList;

    public Customer(int idNumber, String name, String surname, String gender, 
            String maritalStatus, long phoneNumber, String houseAddress, String
                    email, List<Account> accountList) {
        this.idNumber = idNumber;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.phoneNumber = phoneNumber;
        this.houseAddress = houseAddress;
        this.email = email;
        this.accountList = accountList;
    }

    public Customer() {
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.surname);
        hash = 29 * hash + Objects.hashCode(this.maritalStatus);
        hash = 29 * hash + Objects.hashCode(this.houseAddress);
        hash = 29 * hash + Objects.hashCode(this.email);
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
        final Customer other = (Customer) obj;
        if (this.idNumber != other.idNumber) {
            return false;
        }
        if (this.phoneNumber != other.phoneNumber) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "idNumber=" + idNumber + ", name=" + name + 
                ", surname=" + surname + ", gender=" + gender + ", maritalStatus="
                + maritalStatus + ", phoneNumber=" + phoneNumber + 
                ", houseAddress=" + houseAddress + ", email=" + email + '}';
    }

}
