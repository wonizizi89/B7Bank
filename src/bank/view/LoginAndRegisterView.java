package bank.view;

import bank.controller.Bank;
import java.util.Scanner;

public class LoginAndRegisterView {
    Scanner inputSc = new Scanner(System.in);

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
