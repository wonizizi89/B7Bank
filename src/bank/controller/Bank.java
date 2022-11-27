package bank.controller;

import java.util.List;
import java.util.ArrayList;


public class Bank {
    private String bankName;
    private List<Account> bankAccounts;
    private List<Customer> customers;

    public Bank() {
    }

    public Bank(String bankName) {
        this.bankName = bankName;
        this.bankAccounts = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public boolean checkDuplicateID(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerID)) {
                return false;
            }
        }

        return true;
    }

    public void registerCustomer(String customerID, String password, String customerName) {
        Customer newCustomer = new Customer(customerID, password, customerName);
        customers.add(newCustomer);
    }

    public void reviseAccount(Customer customer) {
    }

    public void deleteAccount(Customer customer) {
        for (Account account : bankAccounts) {
            if (customer.getName().equals(account.getOwnerName())) {
                bankAccounts.remove(customer);
            }
        }
    }

    public void findEveryAccountFromCustomerId(Customer customer) { //모든 계좌 정보 조회 기능
        String customerId = customer.getCustomerId();
        List<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public void registerAccount(String ownerName) {
        String bankNumber = createBankNumber();

        for (Account Account : bankAccounts) {
            if (Account.getAccountNumber().equals(bankNumber)) {
                bankNumber = createBankNumber();
            }
        }

        Account newAccount = new Account(ownerName, bankNumber, this.bankName);
        bankAccounts.add(newAccount);
    }

    private String createBankNumber() {
        StringBuilder bankNumBuilder = new StringBuilder();
        bankNumBuilder.append("110");
        bankNumBuilder.append((Math.random() * 100));
        bankNumBuilder.append((Math.random() * 100000));
        return bankNumBuilder.toString();
    }

}

