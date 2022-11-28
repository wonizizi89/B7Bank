package bank.application;

import bank.controller.Account;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        Account account = new Account("고양이", "11111-1111", "카카오뱅크");
        account.deposit(BigDecimal.valueOf(10000.0));
        System.out.println(account.getBalance());
        account.deposit(BigDecimal.valueOf(100000.0));
        System.out.println(account.getBalance());
        account.withdraw(BigDecimal.valueOf(2000));
        System.out.println(account.getBalance());

    }
}
