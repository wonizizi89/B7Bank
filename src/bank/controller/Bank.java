package bank.controller;

import java.util.List;
import java.util.ArrayList;


public class Bank {
    private String bankName;
    private List<Account> accounts;
    private List<Customer> customers;

    public Bank(String bankName) {
        this.accounts = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }
    public void reviseAccount(Customer customer) {
    }
    public void deleteAccount(Customer customer) {
        for(Account account : accounts) {
            if(customer.getName().equals(account.getOwnerName())) {
                accounts.remove(customer);
            }
        }
    }


    public void registerAccount(Account ac1) {
    }
}

