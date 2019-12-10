/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.entities.login_details;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import za.co.pvi_bank.entities.person.employee.Employee;

/**
 *
 * @author embot
 */
@Entity
@Table
public class LoginDetails implements Serializable {

    @Id
    @Column(name = "password")
    private int password;

    @Column(name = "user_name")
    private String userName;

    @OneToOne
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public LoginDetails(String user_name, int password, Employee employee) {
        super();
        this.userName = user_name;
        this.password = password;
        this.employee = employee;
    }

    public LoginDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.password;
        hash = 47 * hash + Objects.hashCode(this.userName);
        hash = 47 * hash + Objects.hashCode(this.employee);
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
        final LoginDetails other = (LoginDetails) obj;
        if (this.password != other.password) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoginDetails{" + "password=" + password + ", userName=" + userName + ", employee=" + employee + '}';
    }

    
    
}
