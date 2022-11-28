package bank.view;

import bank.controller.Account;
import bank.controller.Customer;
import bank.controller.Bank;

import java.util.List;
import java.util.Scanner;

public class ManageAccountView {

    public static void showCreateAccountUI(Bank bank, Customer customer) {
        System.out.println("--------------------------------");
        System.out.println("계좌를 만드는 중입니다.");
        Account newAccount = bank.registerAccount(customer.getName());
        customer.addCustomerAccount(newAccount);
        System.out.println(newAccount.getAccountNumberWithHypen());
        MainView.showMainUI(bank, customer);
    }

    public static void showDeleteAccountUI(Bank bank, Customer customer) {
        Scanner selectScanner = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println("0. 돌아가기");
        
        List<Account> accounts = customer.getCustomerAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, accounts.get(i).getAccountNumberWithHypen()));
        }

        System.out.print("삭제할 계좌를 선택해주세요 > ");
        String selectInput = selectScanner.next();

        for (int i = 0; i < selectInput.length(); i++) {
            char moveChar = selectInput.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println(" 잘 못 입력되었습니다.");
                showDeleteAccountUI(bank, customer);
                return;
            }
        }

        int selectInt = Integer.parseInt(selectInput);

        if ( selectInt < 0 || selectInt > accounts.size()) {
            System.out.println("잘 못 입력되었습니다.");
            showDeleteAccountUI(bank, customer);
            return;
        }

        if (selectInt == 0 ) {
            MainView.showMainUI(bank, customer);
        } else {
            Account targetAccount = customer.getAccount(selectInt - 1);
            bank.deleteAccount(targetAccount);
            customer.deleteCustomerAccount(targetAccount);
            System.out.println(String.format("\"%s\" 계좌가 삭제되었습니다.", targetAccount.getAccountNumberWithHypen()));
            System.out.println("이전 뷰로 되돌아감");
        }
    }
}
