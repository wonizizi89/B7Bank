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
            ViewMethod.jump();
            BankingView.showAccountListUI(bank, customer);
        } else if(s.equals("2")){
            ViewMethod.jump();
            showCreateAccountUI(bank, customer);
        } else if(s.equals("3")){
            ViewMethod.jump();
            showDeleteAccountUI(bank, customer);
        } else if(s.equals("4")){
            ViewMethod.jump();
            System.out.println("로그아웃 되었습니다🙋");
            LoginAndRegisterView.showBeginningUI(bank);
        } else if(s.equals("5")){
            ViewMethod.jump();
            ViewMethod.printExitMessage();
            System.exit(0);
        } else {
            ViewMethod.jump();
            ViewMethod.printWrongTypingMessage();
            showMainAccountUI(bank, customer);
        }
    }

    public static void showCreateAccountUI(Bank bank, Customer customer) {
        System.out.print("계좌 생성중");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("showCreateAccountUI[line, 50] : InterruptException!");
            }
            System.out.print(".");
        }
        ViewMethod.jump();
        System.out.println("----------------------------------------");
        System.out.printf("%s생성 완료 되었습니다👍%s", System.lineSeparator(), System.lineSeparator());
        Account newAccount = bank.registerAccount(customer.getName());
        customer.addCustomerAccount(newAccount);
        System.out.printf("🤑%s%s",newAccount.getAccountNumberWithHypen(), System.lineSeparator());
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
                ViewMethod.jump();
                ViewMethod.printWrongTypingMessage();
                showDeleteAccountUI(bank, customer);
                return;
            }
        }

        int selectInt = Integer.parseInt(selectInput);

        if ( selectInt < 0 || selectInt > accounts.size()) {
            ViewMethod.jump();
            ViewMethod.printWrongTypingMessage();
            showDeleteAccountUI(bank, customer);
            return;
        }

        if (selectInt == 0 ) {
            ViewMethod.jump();
            showMainAccountUI(bank, customer);
        } else {
            Account targetAccount = customer.getAccount(selectInt - 1);
            bank.deleteAccount(targetAccount);
            customer.deleteCustomerAccount(targetAccount);
            ViewMethod.jump();
            System.out.print("계좌 삭제중");
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("showCreateAccountUI[line, 50] : InterruptException!");
                }
                System.out.print(".");
            }
            ViewMethod.jump();
            System.out.println("----------------------------------------");
            System.out.printf("%s계좌가 삭제되었습니다👍%s", System.lineSeparator(), System.lineSeparator());
            showMainAccountUI(bank, customer);
        }
    }


}
