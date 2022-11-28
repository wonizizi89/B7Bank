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

        System.out.println("--------------------------------");
        List<Account> accounts = customer.getCustomerAccounts();
        System.out.println("0. 돌아가기");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, accounts.get(i).getAccountNumberWithHypen()));
        }
        System.out.println("exit. 종료");
        System.out.print(" > ");
        String inputMove = moveScanner.next();

        // exit 이거나 숫자 (0~계좌의 개수) 인지 확인하는 로직 필요!
        // exit 인지 먼저 확인
        // 모두 숫자로 이루어져있는지 검사
        // 범위 안에 있는지 검사
        // 은행 업무 선택
        if (inputMove.equals("exit")) {
            System.exit(0);
        }

        for (int i = 0; i < inputMove.length(); i++) {
            char moveChar = inputMove.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println(" 잘 못 입력되었습니다.");
                showAccountListUI(bank, customer);
                return;
            }
        }

        int moveInt = Integer.parseInt(inputMove);
        if (moveInt < 0 || moveInt > accounts.size()) {
            System.out.println("잘 못 입력되었습니다.");
            showAccountListUI(bank, customer);
            return;
        }

        if (moveInt == 0) {
            AccountView.showMainAccountUI(bank, customer);
        } else {
            showBankingUI(bank, customer, moveInt);
        }
    }

    // 은행 업무를 선택하게 되는 뷰
    public static void showBankingUI(Bank bank, Customer customer, int index) {
        Scanner moveScanner = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println(String.format("계좌 정보: %s", customer.getAccount(index).getAccountNumberWithHypen()));
        System.out.println("0. 돌아가기");
        System.out.println("1. 입금");
        System.out.println("2. 출금");
        System.out.println("3. 잔고 확인");
        System.out.println("exit. 종료");
        System.out.print(" > "); //어떤 작업을 할지 번호 기입란 표기 예) > 1
        String inputMove = moveScanner.next(); //메뉴1,2,3 중 입력된 값 input에 저장, String형 숫자 기입

        if (inputMove.equals("exit")) {
            System.exit(0);
        }

        for (int i = 0; i < inputMove.length(); i++) {
            char moveChar = inputMove.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println(" 잘 못 입력되었습니다.");
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
                showDepositeUI(bank, customer, index);
                break;
            case 2:
                showWithdrawUI(bank, customer, index);
                break;
            case 3:
                System.out.println(String.format("잔고: %s원", customer.getAccount(index).getBalance()));
                showBankingUI(bank, customer, index);
                break;
            default:
                System.out.println(" 잘 못 입력되었습니다.");
                showBankingUI(bank, customer, index);
                break;
        }
    }

    // 입금
    public static void showDepositeUI(Bank bank, Customer customer, int index) {
        Scanner amountScanner = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("0. 돌아가기");
        System.out.println("입금할 금액을 입력해주세요");
        System.out.print(" > ");
        String inputAmount = amountScanner.next();

        for (int i = 0; i < inputAmount.length(); i++) {
            char moveChar = inputAmount.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println(" 잘 못 입력되었습니다.");
                showDepositeUI(bank, customer, index);
                return;
            }
        }

        BigDecimal amount = new BigDecimal(inputAmount);
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            showBankingUI(bank, customer, index);
            return;
        }

        Account account = customer.getAccount(index);
        account.deposit(amount);
        System.out.println(String.format("잔고: %s원", account.getBalance()));
        showBankingUI(bank, customer, index);
    }

    // 출금
    public static void showWithdrawUI(Bank bank, Customer customer, int index) {
        Account account = customer.getAccount(index);

        Scanner amountScanner = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println(String.format("잔고: %s원", account.getBalance()));
        System.out.println("0. 돌아가기");
        System.out.println("출금할 금액을 입력해주세요");
        System.out.print(" > ");
        String inputAmount = amountScanner.next();

        for (int i = 0; i < inputAmount.length(); i++) {
            char moveChar = inputAmount.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println(" 잘 못 입력되었습니다.");
                showWithdrawUI(bank, customer, index);
                return;
            }
        }

        BigDecimal amount = new BigDecimal(inputAmount);
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            showBankingUI(bank, customer, index);
            return;
        }

        if (account.withdraw(amount).compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("잔액이 부족합니다.");
            showBankingUI(bank, customer, index);
            return;
        }

        System.out.println(String.format("잔고: %s원", account.getBalance()));
        showBankingUI(bank, customer, index);
    }

    // 모든 거래내역을 보는 뷰
    public static void showHistoriesUI(Account account) {
        Scanner moveScanner = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println("0. 돌아가기");
        System.out.print(account.printAllHistoriesOrNull());
        System.out.println("--------------------------------");
        System.out.println("상세 거래 내역을 보려면 번호를 입력해주세요.");
        String move = moveScanner.next();

        // 입력 받은 문자열이 숫자가 맞는지 확인!
        for (int i = 0; i < move.length(); i++) {
            char moveChar = move.charAt(i);
            if (moveChar < 48 || moveChar > 57) {
                System.out.println("잘 못 입력하셨습니다!");
                showHistoriesUI(account);
            } else if (moveChar == 48) {
                System.out.println("이전 단계로 돌아가게 됩니다.");
                System.exit(0);
            } else {
                showHistory(account, Integer.parseInt(move));
            }
        }
    }

    // 특정 거래내역을 상세로 보는 뷰
    public static void showHistory(Account account, int index) {
        Scanner moveScanner = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println(String.format("%s%s", account.printHistory(index), System.lineSeparator()));
        System.out.println("0. 돌아가기");
        System.out.println("--------------------------------");
        String move = moveScanner.next();
        // 입력 받은 문자열이 0인지 확인!
        for (int i = 0; i < move.length(); i++) {
            char moveChar = move.charAt(i);
            if (moveChar != 48) {
                System.out.println("잘 못 입력하셨습니다!");
                showHistory(account, Integer.parseInt(move));
            } else {
                showHistoriesUI(account);
            }
        }
    }
}

