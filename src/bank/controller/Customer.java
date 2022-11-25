package bank.controller;

import java.util.HashMap;

public class Customer {
    private String name;
    private HashMap<String, String> accounts;
    private String customerId; // 동명 이인일 수 있어서 이걸로 식별

    public String getName() {
        return name;
    }

    public HashMap<String, String> getAccounts() {
        return accounts;
    }

    public String getCustomerId() {
        return customerId;
    }


    public Customer(String name, String customerId) {
        this.name = name;
        this.accounts = new HashMap<>();
        this.customerId = customerId;
    }

}
