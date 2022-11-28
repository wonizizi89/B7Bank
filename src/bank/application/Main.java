package bank.application;


import bank.controller.Account;
import bank.controller.Bank;
import bank.controller.Customer;
import bank.view.BankingView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Bank> banks = new ArrayList<>();
    public static void main(String[] args) {
//        LoginAndRegisterView.printLogo();
//        Bank bank = new Bank("세븐뱅크");
//        bank.registerCustomer("pororo", "1234", "뽀로로");
//        bank.registerCustomer("lupi", "1234", "루피");
//        bank.registerCustomer("krong", "1234", "크롱");
//
//        while (true) {
//            LoginAndRegisterView.showBeginningUI(bank);
//        }

        {
            Bank bank1 = new Bank("국민은행", "018");
            Bank bank2 = new Bank("신한은행", "110");
            Customer user1 = new Customer("pororo", "1234", "뽀로로");
            Customer user2 = new Customer("lupi", "1234", "루피");

            bank1.addCustomer(user1);
            bank2.addCustomer(user2);

            Account ac1 = new Account("뽀로로", "110403000001", "신한은행");
            Account ac2 = new Account("뽀로로", "110403000002", "신한은행");
            Account ac3 = new Account("뽀로로", "110403000003", "신한은행");

            ac1.deposit(BigDecimal.valueOf(10000));
            ac2.deposit(BigDecimal.valueOf(10000));

            bank1.addAccount(ac1);
            bank1.addAccount(ac2);
            user1.addCustomerAccount(ac1);
            user1.addCustomerAccount(ac2);

            System.out.println("myAccount 잔고 : " + ac1.getBalance());
            System.out.println("yourAccount 잔고 : " + ac2.getBalance());

            BankingView.showTransferUI(bank1, user1, 0);

            System.out.println("myAccount 잔고 : " + ac1.getBalance());
            System.out.println("yourAccount 잔고 : " + ac2.getBalance());

            // 거래 내역 남기기
            // 입력이 잘못됐을 때
        }
    }
}
