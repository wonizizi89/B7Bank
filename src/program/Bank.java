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

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public void checkAccountByCustomerName(Customer customer) {
        for(Account account : accounts) {
            if(customer.getName().equals(account.getOwnerName())) {
                String bankName = account.getBankName();
                String accountNum = account.getAccountNumber();
                int balance = account.getBalance();
                System.out.printf("은행 : %s, 계좌번호 : %s, 잔고 : %d원",bankName,accountNum,balance);
            }
        }
    }




}

