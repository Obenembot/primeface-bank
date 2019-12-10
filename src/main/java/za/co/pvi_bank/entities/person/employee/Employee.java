/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.entities.person.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import za.co.pvi_bank.entities.login_details.LoginDetails;

/**
 *
 * @author embot
 */
@Entity
@Table
public class Employee {

    @Id
    @Column
    private long idNumber; // PRIMARY KEY

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private long phoneNumber; //

    @Column
    private String emailAddress;
    @Column
    private String gender;
    @Column
    private String maritalStatus;
    @Column
    private String houseAddress;

    @Column
    private String employeePosition;

    @Column
    private String date;

    @OneToOne
    private LoginDetails logindetails; /// Has a Relation

    public Employee() {

        this.logindetails = new LoginDetails();
    }

    public Employee(long idNumber, String name, String surname, long phoneNumber,
            String emailAddress, String gender, String maritalStatus,
            String houseAddress, String employeePosition, String date,
            LoginDetails logindetails) {
        this.idNumber = idNumber;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.houseAddress = houseAddress;
        this.employeePosition = employeePosition;
        this.date = date;
        this.logindetails = logindetails;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LoginDetails getLogindetails() {
        return logindetails;
    }

    public void setLogindetails(LoginDetails logindetails) {
        this.logindetails = logindetails;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.idNumber ^ (this.idNumber >>> 32));
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
        final Employee other = (Employee) obj;
        if (this.idNumber != other.idNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "idNumber=" + idNumber + ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", houseAddress=" + houseAddress + ", employeePosition=" + employeePosition + ", date=" + date + '}';
    }
    
    

}
