package bank.view;

import java.util.List;
import java.util.Scanner;

import bank.controller.Account;
import bank.controller.Bank;
import bank.controller.Customer;

public class AccountView {
    static Scanner scanner = new Scanner(System.in);
    public static void showMainAccountUI(Bank bank, Customer customer){
        System.out.println("==========================");
        System.out.println("1. 계좌 선택");
        System.out.println("2. 계좌 생성");
        System.out.println("3. 계좌 삭제");
        System.out.println("4. 로그아웃");
        System.out.println("5. 종료");
        System.out.println("==========================");
        System.out.println("원하는 작업 번호를 입력해주세요 : ");
        String s = scanner.nextLine();
        if(s.equals("1")){
            BankingView.showAccountListUI(bank, customer);
        } else if(s.equals("2")){
            showCreateAccountUI(bank, customer);
        } else if(s.equals("3")){
            showDeleteAccountUI(bank, customer);
        } else if(s.equals("4")){
            LoginAndRegisterView.showUIBeginning();
        } else if(s.equals("5")){
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
        }
    }

    public static void showCreateAccountUI(Bank bank, Customer customer) {
        System.out.println("--------------------------------");
        System.out.println("계좌를 만드는 중입니다.");
        Account newAccount = bank.registerAccount(customer.getName());
        customer.addCustomerAccount(newAccount);
        System.out.println(newAccount.getAccountNumberWithHypen());
        showMainAccountUI(bank, customer);
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
            showMainAccountUI(bank, customer);
        } else {
            Account targetAccount = customer.getAccount(selectInt - 1);
            bank.deleteAccount(targetAccount);
            customer.deleteCustomerAccount(targetAccount);
            System.out.println(String.format("\"%s\" 계좌가 삭제되었습니다.", targetAccount.getAccountNumberWithHypen()));
            System.out.println("이전 뷰로 되돌아감");
        }
    }
}
