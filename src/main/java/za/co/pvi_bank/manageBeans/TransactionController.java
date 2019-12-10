/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.manageBeans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import za.co.pvi_bank.entities.account.Account;
import za.co.pvi_bank.entities.transactions.Transactions;
import za.co.pvi_bank.exceptions.insufficientFundException.InsufficientFundException;
import za.co.pvi_bank.exceptions.invalidAmount.InvalidAmountException;

/**
 *
 * @author embot
 */
@ManagedBean(name = "transactions")
public class TransactionController {

    private double amount;
    private long accountNumber;
    private String reference;
    private int numberOfMonths;
    private List<Account> list = new ArrayList<Account>();
    private Account account = new Account();
    private List<Transactions> transactiontList = new ArrayList<>();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Transactions> getTransactiontList() {
        return transactiontList;
    }

    public void setTransactiontList(List<Transactions> transactiontList) {
        this.transactiontList = transactiontList;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pviBank");
    EntityManager em = emf.createEntityManager();

    List< Account> accountList = null;
    Transactions transactions = new Transactions();

    public String deposit() {
        em.getTransaction().begin();
        accountList = (List<Account>) em.createQuery("SELECT a FROM Account a").getResultList();

        if (this.amount < 1) {
            try {
                throw new InvalidAmountException();
            } catch (InvalidAmountException ex) {
                message = "invalid Amount";
                return "deposit.xhtml";
            }
        }
        int a = 0;
        for (a = 0; a < accountList.size(); a++) {
            if (accountList.get(a).getAccountNumber() == accountNumber) {
                try {
                    accountList.get(a).setBalance(accountList.get(a).getBalance() + amount);

                    double balance = accountList.get(a).getBalance(); // getting balance to set to transaction
                    String date = LocalDateTime.now().toString();

                    String value = String.valueOf(new Random().nextInt()); // Generating transaction Id
                    int transactionId = Integer.valueOf(value.substring(1));

                    transactions = this.addToTransactions(transactionId, "deposit", accountNumber, amount, balance, reference, date);

                    em.persist(accountList.get(a));
                    em.persist(transactions);
                    em.getTransaction().commit();
                    return "transactionCompleted.xhtml";
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    e.getStackTrace();
                }
            } // end of Checking Account Number

        }
        if (accountList.size() == a) {
            message = "Account Number Doest Exist";
            return "deposit.xhtml";
        }
        return null; // page to see Your REsults Of Deosit
    }

    public String withdrawal() {
        em.getTransaction().begin();
        accountList = (List<Account>) em.createQuery("SELECT a FROM Account a").getResultList();

        if (this.amount > 0) {
            for (int a = 0; a < accountList.size(); a++) {

                if (accountList.get(a).getAccountNumber() == accountNumber) {
                    if (accountList.get(a).getBalance() <= this.amount) {
                        try {
                            throw new InsufficientFundException();
                        } catch (InsufficientFundException ex) {
                            message = "Insufficient Amount";
                            return "withdrawal.xhtml";
                        }
                    } else {
                        try {
                            accountList.get(a).setBalance(accountList.get(a).getBalance() - this.amount);

                            double balance = accountList.get(a).getBalance(); // getting balance to set to transaction
                            String date = LocalDateTime.now().toString();

                            String value = String.valueOf(new Random().nextInt()); // Generating transaction Id
                            int transactionId = Integer.valueOf(value.substring(1));

                            transactions = this.addToTransactions(transactionId, "withdrawal", accountNumber, amount, balance, reference, date);

                            em.persist(transactions);
                            em.persist(accountList.get(a));
                            em.getTransaction().commit();
                            return "transactionCompleted.xhtml";
                        } catch (Exception e) {
                            em.getTransaction().rollback();
                            message = e.getMessage();
                            return "withdrawal.xhtml";
                        }
                    }
                }
            } // end of Lof
        } else {
            message = "invalid AMount";
            return "withdrawal.xhtml";
        }

        return null;// Page You want to  View or Details of Transactions

    }

    public String balance() {

        em.getTransaction().begin();
        accountList = (List<Account>) em.createQuery("SELECT a FROM Account a").getResultList();
        int i = 0;
        for (i = 0; i < accountList.size(); i++) {

            if (accountList.get(i).getAccountNumber() == this.accountNumber) {

                list.add(accountList.get(i));
                account = accountList.get(i);

                return "checkBalance.xhtml";

            }

        }
        if (accountList.size() == i) {
            message = "Account Number Not Found";
            return "balance.xhtml"; // "checkBalance.xhtml";// Page to Say the Account Doesnt Exist
        }
        return null; // 
    }

    public Transactions addToTransactions(int transactionId, String transactionType,
            long accountNumber, double transactionAmount, double accountBalance,
            String reference, String localDate) {
        Transactions transactions = new Transactions();

        transactions.setTransactionId(transactionId);
        transactions.setTransactionType(transactionType);
        transactions.setAccountNumber(accountNumber);
        transactions.setAccountBalance(accountBalance);
        transactions.setTransactionAmount(transactionAmount);
        transactions.setReference(reference);
        transactions.setLocalDate(localDate);
        return transactions;
    }

    public String printStatement() {
        transactiontList = new ArrayList<>();

        em.getTransaction().begin();
        List<Transactions> transactions1 = (List<Transactions>) em.createQuery("SELECT a FROM Transactions a").getResultList();

        int i = 0;
        for (i = 0; i < transactions1.size(); i++) {

            if (transactions1.get(i).getAccountNumber() == accountNumber) {

                transactiontList.add(transactions1.get(i));

            }
        }
        if (transactiontList != null) {
            return "printStatement.xhtml";

        } else {

            message = "Account Not Found";
            return "transactions.xhtml";
        }

    }

}
