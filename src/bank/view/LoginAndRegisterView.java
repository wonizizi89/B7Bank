package bank.view;

import bank.controller.Customer;

import java.util.List;
import java.util.Scanner;

public class LoginAndRegisterView {
    Scanner inputSc = new Scanner(System.in);

    public void showUIRegisterCustomer(List<Customer> customers) {
        System.out.println("----------회원가입----------");
        System.out.print("ID을 입력해주세요 : ");
        String newCustomerId = inputSc.next();

        for (Customer cm : customers) {
            while (true) {
                if (cm.getCustomerId().equals(newCustomerId)) {
                    System.out.println("중복된 ID 입니다. 다시 입력해주세요");
                    newCustomerId = inputSc.next();
                } else break;
            }
        }
        System.out.print("비밀번호를 입력해주세요 :");
        String newCustomerPassword = inputSc.next();


//        public void registerCustomer(Customer customer) {
//            customers.add(customer);
//        }
    }
}
