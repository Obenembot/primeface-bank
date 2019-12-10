/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.services;

import java.time.LocalDate;
import java.util.List;
import za.co.pvi_bank.exceptions.insufficientFundException.InsufficientFundException;
import za.co.pvi_bank.entities.account.Account;
import za.co.pvi_bank.entities.card.Card;
import za.co.pvi_bank.entities.login_details.LoginDetails;
import za.co.pvi_bank.entities.transactions.Transactions;
import za.co.pvi_bank.exceptions.invalidAccoutNumber.InvalidAccountNumber;
import za.co.pvi_bank.exceptions.invalidAmount.InvalidAmountException;

/**
 *
 * @author embot
 */
public interface PviBankServices {

// start of transactions methods 
    public void addToTransactions(int transactionId, String transactionType, long accountNumber,
            double transactionAmount, double accountBalance, String reference, String localDate);

    public List<Transactions> printStatement(List<Transactions> list, int accountNumber);

    public List<Transactions> printByDate(List<Transactions> list, int months);

    // end of transactions methods 
    // start of account Methods
    public void creatAccount(long accountNumber, 
            double amount,String accountStatus, String accountType, Card card);
    // end of Account Methods

    // start of Card Methods
    public void cardInformation(int pin, String cardType, int cardNumber, Account account);
    // end of Card Methods

    // start of employee methods
    public void registerEmployee(String name, String surname, long phone_number,
            String email_address, String house_address, long id_number,
            String employee_position, String date, LoginDetails logindetails);

    // login Method
    public void setLoginDetails(String user_name, int password);
    // end of loogin method

    // start of customer Methods
    public void registerCustomer(int idNumber, String name, String surname, String gender, 
            String maritalStatus, long phoneNumber, String houseAddress, String
                    email, List<Account> accountList) ;

    public double deposit(double amount, double accountBalance) throws InvalidAmountException;

    public double withdrawal(double amount, double accountbalance) throws InsufficientFundException;

    public double balance(List<Account> accountList, int accountNumber) throws InvalidAccountNumber;

    // end of Customer Methods
}
