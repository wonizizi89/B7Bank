package bank.entity;

import java.util.ArrayList;
import java.util.List;

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

    public List<Account> getCustomerAccounts() {
        return customerAccounts;
    }

    public void addCustomerAccount(Account account) {
        customerAccounts.add(account);
    }

    public void deleteCustomerAccount(Account account) {
        customerAccounts.remove(account);
    }

    public Account getAccount(int index) {
        return customerAccounts.get(index);
    }
}
