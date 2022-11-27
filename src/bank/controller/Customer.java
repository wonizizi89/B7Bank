package bank.controller;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String password;
    private String name;
    private List<Account> customerAccounts;

    public Customer(String customerId, String password, String name) {
        this.customerId = customerId;
        this.password = password;
        this.name = name;
        this.customerAccounts = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return customerAccounts;
    }

}
