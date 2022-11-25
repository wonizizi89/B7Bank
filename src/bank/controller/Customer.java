package bank.controller;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String name;
    private List<Account> accounts;
    private String customerId; // 동명 이인일 수 있어서 이걸로 식별


    public Customer(String name, String customerId) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getCustomerId() {
        return customerId;
    }
}
