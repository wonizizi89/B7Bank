package bank.view;

import bank.controller.Bank;
import bank.controller.Customer;

import java.util.Scanner;

public class LoginAndRegisterView {
    Scanner inputSc = new Scanner(System.in);

    public void showUILogin(Bank bank) {
        System.out.println("----------고객 로그인 페이지 입니다----------");
        System.out.print("아이디를 입력해주세요 :");
        String customerID = inputSc.next();
        Customer customer = bank.getCustomerOrNull(customerID);
        if (customer == null) {
            System.out.println("존재하지 않는 아이디입니다.");
            showUILogin(bank);
        }

        System.out.print("패스워드를 입력해주세요 :");
        String password = inputSc.next();

        if(bank.loginCustomer(customer, password)) {
            System.out.println("로그인 되었습니다.");
        } else {
            System.out.println("비밀번호가 틀립니다.");
        }
    }

    public void showUIRegisterCustomer(Bank bank) {
        System.out.println("----------회원가입----------");
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
    }
}
