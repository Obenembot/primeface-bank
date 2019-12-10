/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.account_services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.pvi_bank.exceptions.insufficientFundException.InsufficientFundException;
import za.co.pvi_bank.entities.account.Account;
import za.co.pvi_bank.entities.card.Card;
import za.co.pvi_bank.entities.login_details.LoginDetails;
import za.co.pvi_bank.entities.person.customer.Customer;
import za.co.pvi_bank.entities.person.employee.Employee;
import za.co.pvi_bank.entities.transactions.Transactions;
import za.co.pvi_bank.exceptions.invalidAccoutNumber.InvalidAccountNumber;
import za.co.pvi_bank.exceptions.invalidAmount.InvalidAmountException;
import za.co.pvi_bank.services.PviBankServices;

/**
 *
 * @author embot
 */
public class AccountServices implements PviBankServices {

    Transactions transactions = new Transactions();
    Account account = new Account();
    Card card = new Card();
    Customer customer = null;
    LoginDetails loginDetails = new LoginDetails();
    Employee employee = new Employee();

    List<Account> accountList = new ArrayList<>();

    @Override
    public void addToTransactions(int transactionId, String transactionType,
            long accountNumber, double transactionAmount, double accountBalance,
            String reference, String localDate) {

        transactions.setTransactionId(transactionId);
        transactions.setTransactionType(transactionType);
        transactions.setAccountNumber(accountNumber);
        transactions.setAccountBalance(accountBalance);
        transactions.setTransactionAmount(transactionAmount);
        transactions.setReference(reference);
        transactions.setLocalDate(localDate.toString());
    }

    @Override
    public List<Transactions> printStatement(List<Transactions> list, int accountNumber) {

        List<Transactions> lists = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getAccountNumber() == accountNumber) {

                lists.add(list.get(i));
            }
        }
        return lists;
    }

    @Override
    public List<Transactions> printByDate(List<Transactions> list, int months) {

        List<Transactions> lists = new ArrayList<>();

        LocalDate local = LocalDate.now();
        LocalDate local1 = LocalDate.now();
        local = local.minusMonths(months);
        int num = 0;

        for (int a = 0; a < list.size(); a++) {

            if (local.isAfter(local1)) {
                break;
            } else {

                lists.add(list.get(a));
                local = local.plusDays(++num);
            }
        }
        return lists;
    }

    @Override
    public void registerCustomer(int idNumber, String name, String surname, String gender,
            String maritalStatus, long phoneNumber, String houseAddress, String email, 
            List<Account> accountList) {

        accountList.add(account); // note should be taken here

        customer = new Customer(idNumber, name, surname, gender, maritalStatus,
                phoneNumber, houseAddress, email, accountList);
    }

    @Override
    public void creatAccount(long accountNumber, double amount, String accountStatus, String accountType, Card card) {
        account.setAccountNumber(accountNumber);
        account.setBalance(amount);
        account.setAccountStatus(accountStatus);
        account.setAccountType(accountType);
        account.setCard(card);
    }

    @Override
    public void cardInformation(int pin, String cardType, int cardNumber, Account account) {
        card.setAccount(account);
        card.setCardPin(pin);
        card.setCardNumber(cardNumber);
        card.setCardType(cardType);
    }

    @Override
    public void registerEmployee(String name, String surname, long phoneNumber,
            String emailAddress, String house_address, long idNumber,
            String employee_position, String date, LoginDetails logindetails) {

        employee = new Employee(idNumber, name, surname, phoneNumber, emailAddress,
                name, emailAddress, house_address, employee_position, date, logindetails);

    }

    @Override
    public void setLoginDetails(String user_name, int password) {

      //  loginDetails = new LoginDetails(user_name, password);
        loginDetails.setEmployee(employee);

    }

    @Override
    public double deposit(double amount, double accountBalance) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException();
        } else if (amount > 0.1) {

            accountBalance = accountBalance + amount;
        }
        return accountBalance;

    }

    @Override
    public double withdrawal(double amount, double accountbalance){

        if (amount > 0.1 && accountbalance > amount) {

            accountbalance = accountbalance - amount;

        } else if (accountbalance <= amount || amount <= 0) {

        
            try {
                throw new InsufficientFundException();
            } catch (InsufficientFundException ex) {
                Logger.getLogger(AccountServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
        return accountbalance;

    }

    @Override
    public double balance(List<Account> accountList, int accountNumber) throws InvalidAccountNumber {
        
        for (int i = 0; i < accountList.size(); i++) {

            if (accountList.get(i).getAccountNumber() == accountNumber) {

                return accountList.get(i).getBalance();

            } else if (accountList.size() == 0) {
                try {
                    throw new InvalidAccountNumber();
                } catch (InvalidAccountNumber e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    
    // wasnot use in the project 
}
