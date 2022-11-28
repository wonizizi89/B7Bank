package bank.view;

import bank.controller.Bank;
import bank.controller.Customer;

import java.util.Scanner;

public class LoginAndRegisterView {

    public static void printLogo() { // main 이 시작될 때 한번만 불러준다.
        System.out.println("███████╗███████╗██╗   ██╗███████╗███╗   ██╗    ██████╗  █████╗ ███╗   ██╗██╗  ██╗");
        System.out.println("██╔════╝██╔════╝██║   ██║██╔════╝████╗  ██║    ██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝");
        System.out.println("███████╗█████╗  ██║   ██║█████╗  ██╔██╗ ██║    ██████╔╝███████║██╔██╗ ██║█████╔╝");
        System.out.println("╚════██║██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║    ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗");
        System.out.println("███████║███████╗ ╚████╔╝ ███████╗██║ ╚████║    ██████╔╝██║  ██║██║ ╚████║██║  ██╗");
        System.out.println("╚══════╝╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝    ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝");
    }

    public static void showBeginningUI(Bank bank) { // 초기 시작화면을 보여주는 UI
        Scanner inputSc = new Scanner(System.in);

        System.out.println("********** Seven Bank 에 오신걸 환영합니다. **********");
        System.out.println("<원하시는 기능을 선택해주세요>");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 종료");
        System.out.print("번호 입력 : ");

        String num = inputSc.next();

        switch (num) {
            case "1":
                showLoginUI(bank);
                break;
            case "2":
                showRegisterCustomerUI(bank);
                break;
            case "3":
                System.out.println("프로그램을 종료합니다🙋");
                System.out.println("🎈같이하는 가치, Seven Bank🎈");
                System.exit(0);
                break;
            default:
                System.out.println("잘못된 입력입니다🥲.");
                System.out.println("번호를 다시 입력해주세요");
                showBeginningUI(bank);
                break;
        }
    }

    public static void showLoginUI(Bank bank) {
        Scanner inputSc = new Scanner(System.in);

        System.out.println("========== 로그인 페이지 입니다. ==========");
        System.out.println("<되돌아 가려면 0번을 입력하세요.>");
        System.out.print("ID : ");
        String customerID = inputSc.next();

        if(customerID.equals("0")) {
            showBeginningUI(bank);
        }

        Customer customer = bank.getCustomerOrNull(customerID);
        if (customer == null) {
            System.out.println("존재하지 않는 아이디입니다😮");
            showLoginUI(bank);
        }

        System.out.print("PASSWORD : ");
        String password = inputSc.next();

        if(password.equals("0")) {
            showBeginningUI(bank);
        }

        if (bank.loginCustomer(customer, password)) {
            jump();
            System.out.println("로그인 되었습니다😉");
            AccountView.showMainAccountUI(bank, customer);
        } else {
            System.out.println("비밀번호가 틀립니다😓");
            showLoginUI(bank);
        }
    }

    public static void showRegisterCustomerUI(Bank bank) {
        Scanner inputSc = new Scanner(System.in);

        System.out.println("========== 회원가입 페이지 입니다 ==========");
        System.out.println("<되돌아 가려면 0번을 입력하세요.>");
        System.out.print("ID : ");
        String newCustomerID = inputSc.next();

        if(newCustomerID.equals("0")) {
            showBeginningUI(bank);
        }

        if (bank.checkDuplicateID(newCustomerID)) {
            System.out.print("PASSWORD : ");
            String newCustomerPassword = inputSc.next();

            if(newCustomerPassword.equals("0")) {
                showBeginningUI(bank);
            }

            System.out.print("NAME : ");
            String customerName = inputSc.next();
            bank.registerCustomer(newCustomerID, newCustomerPassword, customerName);

            if(customerName.equals("0")) {
                showBeginningUI(bank);
            }

        } else {
            System.out.println("중복된 ID 입니다🥲");
            System.out.println("다시 입력해주세요.");
            showRegisterCustomerUI(bank);
        }

        System.out.println("회원가입이 완료되었습니다!!😊");
        showLoginUI(bank);
    }

    private static void jump() {
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }
}
