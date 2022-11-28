package bank.view;

import java.util.Scanner;

import bank.controller.Bank;
import bank.controller.Customer;

public class MainView {
    static Scanner scanner = new Scanner(System.in);
    public static void showMainUI(Bank bank, Customer customer){
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
            ManageAccountView.showCreateAccountUI(bank, customer);
        } else if(s.equals("3")){
            ManageAccountView.showDeleteAccountUI(bank, customer);
        } else if(s.equals("4")){
            LoginAndRegisterView.showUIBeginning();
        } else if(s.equals("5")){
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
        }
    }
}
