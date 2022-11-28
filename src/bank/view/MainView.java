package bank.view;

import java.util.Scanner;
import bank.view.BankingView;

public class MainView {
    static Scanner scanner = new Scanner(System.in);
    public static void mainView(){
        System.out.println("==========================");
        System.out.println("1. 기능 선택");
        System.out.println("2. 계좌 생성");
        System.out.println("3. 계좌 삭제");
        System.out.println("4. 로그아웃");
        System.out.println("5. 종료");
        System.out.println("==========================");
        System.out.println("원하는 작업 번호를 입력해주세요 : ");
        String s = scanner.nextLine();
        if(s.equals("1")){
            //뷰4
        } else if(s.equals("2")){
            //뷰6
        } else if(s.equals("3")){
            //뷰7
        } else if(s.equals("4")){
            //뷰1
        } else if(s.equals("5")){
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
        }
    }
}
