package program;

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


}
