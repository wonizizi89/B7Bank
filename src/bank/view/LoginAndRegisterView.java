package bank.view;

import bank.controller.Bank;
import bank.controller.Customer;
import bank.view.MainView;

import java.util.Scanner;

public class LoginAndRegisterView {
    Scanner inputSc = new Scanner(System.in);

    public void showUIBeginning() { //초기 시작화면을 보여주는 UI
        System.out.println("**********Seven Bank 에 오신걸 환영합니다.**********");
        System.out.println("<원하시는 기능을 선택해주세요>");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 종료");
        System.out.print("번호 입력 > ");

        int num = inputSc.nextInt();
        if (num < 1 || num > 3) {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            showUIBeginning();
        } else {
            switch (num) {
                case 1:
                    showUILogin(new Bank());
                    break;
                case 2:
                    showUIRegisterCustomer(new Bank());
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
            }
        }
    }

    public void showUILogin(Bank bank) {
        System.out.println("----------로그인 페이지 입니다----------");
        System.out.print("아이디를 입력해주세요 :");
        String customerID = inputSc.next();
        Customer customer = bank.getCustomerOrNull(customerID);
        if (customer == null) {
            System.out.println("존재하지 않는 아이디입니다.");
            showUILogin(bank);
        }

        System.out.print("패스워드를 입력해주세요 :");
        String password = inputSc.next();

        if (bank.loginCustomer(customer, password)) {
            System.out.println("로그인 되었습니다.");
            MainView.mainView();
        } else {
            System.out.println("비밀번호가 틀립니다.");
            showUILogin(bank);
        }
    }

    public void showUIRegisterCustomer(Bank bank) {
        System.out.println("----------회원가입 페이지 입니다----------");
        System.out.print("ID을 입력해주세요 : ");
        String newCustomerId = inputSc.next();

        if (bank.checkDuplicateID(newCustomerId)) {
            System.out.print("비밀번호를 입력해주세요 :");
            String newCustomerPassword = inputSc.next();
            System.out.print("이름을 입력해주세요 : ");
            String customerName = inputSc.next();
            bank.registerCustomer(newCustomerId, newCustomerPassword, customerName);
        } else {
            System.out.println("중복된 ID 입니다. 다시 입력해주세요");
            showUIRegisterCustomer(bank);
        }

        System.out.println("회원가입이 완료되었습니다!");
        showUILogin(new Bank());
    }
}
