package bank.application;

import bank.controller.Account;
import bank.controller.Bank;
import bank.controller.Customer;
import bank.view.BankingView;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        BankingView bv = new BankingView();
        Customer user1 = new Customer("pororo", "1234", "뽀로로");
        Account ac1 = new Account("뽀로로", "110403000001", "신한은행");
        Account ac2 = new Account("뽀로로", "110403000002", "신한은행");
        Account ac3 = new Account("뽀로로", "110403000003", "신한은행");
        user1.addCustomerAccount(ac1);
        user1.addCustomerAccount(ac2);
        user1.addCustomerAccount(ac3);

        bv.showAccountListUI(user1);
    }
}
