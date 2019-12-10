/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.manageBeans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import za.co.pvi_bank.entities.account.Account;
import za.co.pvi_bank.entities.card.Card;
import za.co.pvi_bank.entities.person.customer.Customer;
import za.co.pvi_bank.entities.transactions.Transactions;

/**
 *
 * @author embot
 */
@ManagedBean(name = "customer")
public class CreateCustomerAccountController {

    private long customerId;
    private String name;
    private String surname;
    private String gender;
    private long phoneNumber;
    private String maritalStatus;
    private String houseAddress;
    private String email;

    private long accountNumber;
    private String accountType;
    private String accountStatus;
    private double amount;

    private String cardType;
    private long cardNumber;
    private int cardPin;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardPin() {
        return cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }

    Customer customer = new Customer();
    Card card = new Card();
    Account account = new Account();
    Transactions transactions = new Transactions();
    List<Account> accountList = new ArrayList<>();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pviBank");
    EntityManager em = emf.createEntityManager();

    public String createCustomerAccount() {

        try {
            em.getTransaction().begin();

            customer.setIdNumber(customerId);
            customer.setName(name);
            customer.setSurname(surname);
            customer.setGender(gender);
            customer.setHouseAddress(houseAddress);
            customer.setEmail(email);
            customer.setMaritalStatus(maritalStatus);
            customer.setPhoneNumber(phoneNumber);

            String values = String.valueOf(new Random().nextLong()); // Generating ACCOUNT nUMBER
            accountNumber = Long.valueOf(values.substring(1));
            account.setAccountNumber(accountNumber);
            account.setAccountType(accountType);
            account.setAccountStatus(accountStatus);
            account.setBalance(amount);

            card.setCardNumber(cardNumber);
            card.setCardType(cardType);
            card.setCardPin(cardPin);

            accountList.add(account); // for more than one account

            card.setAccount(account);
            account.setCard(card);
            customer.setAccountList(accountList);

            String value = String.valueOf(new Random().nextInt()); // Generating transaction Id
            int transactionId = Integer.valueOf(value.substring(1));

            String date = LocalDateTime.now().toString();// setting the date

            TransactionController tc = new TransactionController();
            transactions = tc.addToTransactions(transactionId, accountType,
                    accountNumber, amount, amount, "deposit", date);

            em.persist(transactions);
            em.persist(customer);
            em.persist(card);
            em.persist(account);
            em.getTransaction().commit();
            System.out.println("DOn \nDone \nDone");
            return "accountCreated.xhtml";
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return null; // page you want to return to
    }

    List<Customer> customers = null;

    public String existingCustomer() {
        try {
            em.getTransaction().begin();

            customers = em.createQuery("SELECT a FROM Customer a").getResultList();
            int a = 0;
            for (a = 0; a < customers.size(); a++) {
                if (customerId == customers.get(a).getIdNumber()) {

                    customer = customers.get(a);

                    String values = String.valueOf(new Random().nextLong()); // Generating ACCOUNT nUMBER
                    accountNumber = Long.valueOf(values.substring(1));

                    account.setAccountNumber(accountNumber);
                    account.setAccountType(accountType);
                    account.setAccountStatus(accountStatus);
                    account.setBalance(amount);

                        values = String.valueOf(new Random().nextLong()); // Generating ACCOUNT nUMBER
                    cardNumber = Long.valueOf(values.substring(1));
                    
                    card.setCardNumber(cardNumber);
                    card.setCardType(cardType);
                    card.setCardPin(cardPin);

                    accountList.add(account); // for more than one account

                    card.setAccount(account);
                    account.setCard(card);
                    customer.setAccountList(accountList);

                     values = String.valueOf(new Random().nextInt()); // Generating transaction Id
                    int transactionId = Integer.valueOf(values.substring(1));

                    String date = LocalDateTime.now().toString();// setting the date

                    TransactionController tc = new TransactionController();
                    transactions = tc.addToTransactions(transactionId, accountType,
                            accountNumber, amount, amount, "deposit", date);

                    em.persist(transactions);
                    em.persist(customer);
                    em.persist(card);
                    em.persist(account);
                    em.getTransaction().commit();
                    System.out.println("DOn \nDone \nDone");
                    return "accountCreated.xhtml";
                }
            }
            if (customers.size() == a) {
                return "customerIdNotFound.xhtml";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        return null; // page to view /////////
    }
}
