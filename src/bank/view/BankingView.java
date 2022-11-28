package bank.view;

import bank.controller.Account;
import bank.controller.Bank;
import bank.controller.Customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BankingView {

    // 소유한 계좌 목록을 보여주는 뷰
    public static void showAccountListUI(Bank bank, Customer customer) {
        Scanner moveScanner = new Scanner(System.in);
        List<Account> accounts = customer.getCustomerAccounts();

        System.out.println("========================================");
        System.out.println("<원하시는 계좌를 선택해주세요>");
        System.out.println("0. 돌아가기");

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, accounts.get(i).getAccountNumberWithHypen()));
        }

        System.out.println(String.format("%d. 종료", accounts.size() + 1));
        System.out.print("번호 입력 : ");
        String inputMove = moveScanner.next();


        // 모두 숫자로 이루어져있는지 검사
        // 범위 안에 있는지 검사
        // 은행 업무 선택
        for (int i = 0; i < inputMove.length(); i++) {
            char moveChar = inputMove.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showAccountListUI(bank, customer);
                return;
            }
        }

        int moveInt = Integer.parseInt(inputMove);
        if (moveInt < 0 || moveInt > accounts.size()) {
            System.out.println("잘못된 입력입니다🥲.");
            System.out.println("번호를 다시 입력해주세요");
            showAccountListUI(bank, customer);
            return;
        }

        if (moveInt == 0) {
            AccountView.showMainAccountUI(bank, customer);
        } else if (moveInt == accounts.size() + 1) {
            System.exit(0);
        } else {
            showBankingUI(bank, customer, moveInt);
        }
    }

    // 은행 업무를 선택하게 되는 뷰
    public static void showBankingUI(Bank bank, Customer customer, int index) {
        Scanner moveScanner = new Scanner(System.in);
        index -= 1;
        System.out.println("========================================");
        System.out.println("<원하시는 업무를 선택해주세요>");
        System.out.println(String.format("<현재 계좌 : %s>", customer.getAccount(index).getAccountNumberWithHypen()));
        System.out.println("0. 돌아가기");
        System.out.println("1. 입금");
        System.out.println("2. 출금");
        System.out.println("3. 잔고 확인");
        System.out.println("4. 거래내역 확인");
        System.out.println("5. 종료");
        System.out.print("번호 입력 : ");
        String inputMove = moveScanner.next();

        for (int i = 0; i < inputMove.length(); i++) {
            char moveChar = inputMove.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showAccountListUI(bank, customer);
                return;
            }
        }

        int moveInt = Integer.parseInt(inputMove);
        switch (moveInt) {
            case 0:
                showAccountListUI(bank, customer);
                break;
            case 1:
                showDepositUI(bank, customer, index);
                break;
            case 2:
                showWithdrawUI(bank, customer, index);
                break;
            case 3:
                System.out.println(String.format("💰잔고: %s원", customer.getAccount(index).getBalance()));
                showBankingUI(bank, customer, index + 1);
                break;
            case 4:
                showHistoriesUI(bank, customer, index);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showBankingUI(bank, customer, index + 1);
                break;
        }
    }

    // 입금
    public static void showDepositUI(Bank bank, Customer customer, int index) {
        Scanner amountScanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("<되돌아 가려면 0번을 입력하세요.>");
        System.out.print(" 입금할 금액 : ");
        String inputAmount = amountScanner.next();

        for (int i = 0; i < inputAmount.length(); i++) {
            char moveChar = inputAmount.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showDepositUI(bank, customer, index);
                return;
            }
        }

        BigDecimal amount = new BigDecimal(inputAmount);
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            showBankingUI(bank, customer, index + 1);
            return;
        }

        Account account = customer.getAccount(index);
        account.deposit(amount);
        System.out.println(String.format("💰잔고: %s원", account.getBalance()));
        showBankingUI(bank, customer, index + 1);
    }

    // 출금
    public static void showWithdrawUI(Bank bank, Customer customer, int index) {
        Account account = customer.getAccount(index);

        Scanner amountScanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("<되돌아 가려면 0번을 입력하세요.>");
        System.out.println(String.format("<💰현재 잔고: %s원>", account.getBalance()));
        System.out.print("출금할 금액 : ");
        String inputAmount = amountScanner.next();

        for (int i = 0; i < inputAmount.length(); i++) {
            char moveChar = inputAmount.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showWithdrawUI(bank, customer, index);
                return;
            }
        }

        BigDecimal amount = new BigDecimal(inputAmount);
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            showBankingUI(bank, customer, index + 1);
            return;
        }

        if (account.withdraw(amount).compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("잔액이 부족합니다😮");
            showBankingUI(bank, customer, index + 1);
            return;
        }

        System.out.println(String.format("💰잔고: %s원", account.getBalance()));
        showBankingUI(bank, customer, index + 1);
    }

    // 모든 거래내역을 보는 뷰
    public static void showHistoriesUI(Bank bank, Customer customer, int index) {
        Scanner moveScanner = new Scanner(System.in);
        Account account = customer.getAccount(index);

        System.out.println("========================================");
        System.out.println("<되돌아 가려면 0번을 입력하세요.>");
        System.out.print(account.printAllHistoriesOrNull());
        System.out.print("번호 입력 : ");
        String move = moveScanner.next();

        // 입력 받은 문자열이 숫자가 맞는지 확인!
        for (int i = 0; i < move.length(); i++) {
            char moveChar = move.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showHistoriesUI(bank, customer, index);
            } else if (moveChar == 48) {
                showBankingUI(bank, customer, index + 1);
                System.exit(0);
            } else {
                showHistory(bank, customer, index, Integer.parseInt(move));
            }
        }
    }

    // 특정 거래내역을 상세로 보는 뷰
    public static void showHistory(Bank bank, Customer customer, int accountIndex, int historyIndex) {
        Scanner moveScanner = new Scanner(System.in);
        Account account = customer.getAccount(accountIndex);
        System.out.println("<되돌아 가려면 0번을 입력하세요.>");
        System.out.println("----------------------------------------");
        System.out.println(String.format("%s%s", account.printHistory(--historyIndex), System.lineSeparator()));
        System.out.println("----------------------------------------");
        System.out.print("번호 입력 : ");
        String move = moveScanner.next();
        // 입력 받은 문자열이 0인지 확인!
        for (int i = 0; i < move.length(); i++) {
            char moveChar = move.charAt(i);
            if (moveChar != 48) {
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showHistory(bank, customer, accountIndex, historyIndex + 1);
            } else {
                showHistoriesUI(bank, customer, accountIndex);
            }
        }
    }
}

