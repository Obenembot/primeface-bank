/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.controllers.employee_manager_beans;

import java.time.LocalDate;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import za.co.pvi_bank.entities.login_details.LoginDetails;
import za.co.pvi_bank.entities.person.employee.Employee;

/**
 *
 * @author embot
 */
@ManagedBean(name = "empRegistration")
public class EmployeeController {

    private long idNumber = 0;

    private String name;

    private String surname;

    private long phoneNumber; //

    private String emailAddress;

    private String gender;

    private String maritalStatus;

    private String houseAddress;

    private String employeePosition;

    private String date;

    private String username;
    private int password = 0;

    private Employee employeeList = new Employee();

    public Employee getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Employee employee) {
        this.employeeList = employee;
    }

    public EmployeeController() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    private LoginDetails logindetail = new LoginDetails();

    public LoginDetails getLogindetail() {
        return logindetail;
    }

    public void setLogindetails(LoginDetails logindetails) {
        this.logindetail = logindetails;
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

    Employee employee = new Employee();
    LoginDetails loginDetails = new LoginDetails();
    String pUnit = "pviBank";

    EntityManagerFactory emf = null;
    EntityManager em = null;

    public String registerEmployee() {

        try {
            emf = Persistence.createEntityManagerFactory(pUnit);
            em = emf.createEntityManager();
            em.getTransaction().begin();

            employee.setIdNumber(idNumber);
            employee.setName(name);
            employee.setSurname(surname);
            employee.setGender(gender);
            employee.setEmailAddress(emailAddress);
            employee.setEmployeePosition(employeePosition);
            employee.setMaritalStatus(maritalStatus);
            employee.setDate(LocalDate.now().toString());
            employee.setPhoneNumber(phoneNumber);
            employee.setHouseAddress(houseAddress);

            loginDetails.setUserName(username);
            loginDetails.setPassword(password);

            loginDetails.setEmployee(employee);
            employee.setLogindetails(loginDetails);

            em.persist(employee);
            em.persist(loginDetails);
            em.getTransaction().commit();

            employeeList = employee;
            System.out.println("Done........... \n DOne...... \nDone.........");
            return "employeeAccountCreated.xhtml"; // insert LoginDetails page
        } catch (Exception e) {
            em.getTransaction().rollback();

        }

        return null;
    }

}
