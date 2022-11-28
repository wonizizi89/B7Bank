package bank.view;

import java.util.List;
import java.util.Scanner;

import bank.controller.Account;
import bank.controller.Bank;
import bank.controller.Customer;

public class AccountView {
    public static void showMainAccountUI(Bank bank, Customer customer){
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("<원하시는 기능을 선택해주세요>");
        System.out.println("1. 계좌 선택");
        System.out.println("2. 계좌 생성");
        System.out.println("3. 계좌 삭제");
        System.out.println("4. 로그아웃");
        System.out.println("5. 종료");
        System.out.println("========================================");
        System.out.print("번호 입력 : ");
        String s = scanner.nextLine();

        if(s.equals("1")){
            BankingView.showAccountListUI(bank, customer);
        } else if(s.equals("2")){
            showCreateAccountUI(bank, customer);
        } else if(s.equals("3")){
            showDeleteAccountUI(bank, customer);
        } else if(s.equals("4")){
            System.out.println("로그아웃 되었습니다🙋");
            LoginAndRegisterView.showBeginningUI(bank);
        } else if(s.equals("5")){
            System.out.println("프로그램을 종료합니다🙋");
            System.out.println("🎈같이하는 가치, Seven Bank🎈");
            System.exit(0);
        } else {
            System.out.println("잘못된 입력입니다🥲.");
            System.out.println("번호를 다시 입력해주세요");
            showMainAccountUI(bank, customer);
        }
    }

    public static void showCreateAccountUI(Bank bank, Customer customer) {
        System.out.println("========================================");
        System.out.print("계좌 생성중");
        System.out.print(".");
        System.out.print(".");
        System.out.println(".");
        System.out.println("생성 완료 되었습니다👍");
        Account newAccount = bank.registerAccount(customer.getName());
        customer.addCustomerAccount(newAccount);
        System.out.println(newAccount.getAccountNumberWithHypen());
        showMainAccountUI(bank, customer);
    }

    public static void showDeleteAccountUI(Bank bank, Customer customer) {
        Scanner selectScanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("<삭제할 계좌를 선택해주세요>");
        System.out.println("0. 돌아가기");

        List<Account> accounts = customer.getCustomerAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, accounts.get(i).getAccountNumberWithHypen()));
        }

        System.out.print("번호 입력 : ");
        String selectInput = selectScanner.next();

        for (int i = 0; i < selectInput.length(); i++) {
            char moveChar = selectInput.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showDeleteAccountUI(bank, customer);
                return;
            }
        }

        int selectInt = Integer.parseInt(selectInput);

        if ( selectInt < 0 || selectInt > accounts.size()) {
            System.out.println("잘못된 입력입니다🥲.");
            System.out.println("번호를 다시 입력해주세요");
            showDeleteAccountUI(bank, customer);
            return;
        }

        if (selectInt == 0 ) {
            showMainAccountUI(bank, customer);
        } else {
            Account targetAccount = customer.getAccount(selectInt - 1);
            bank.deleteAccount(targetAccount);
            customer.deleteCustomerAccount(targetAccount);
            System.out.print("계좌 삭제중");
            System.out.print(".");
            System.out.print(".");
            System.out.println(".");
            System.out.println(String.format("계좌가 삭제되었습니다👍", targetAccount.getAccountNumberWithHypen()));
            showMainAccountUI(bank, customer);
        }
    }

    private static void jump() {
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }
}
