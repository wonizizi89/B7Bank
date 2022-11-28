package bank;


import bank.application.BankApp;
import bank.application.CustomerApp;
import bank.entity.Bank;
import bank.view.LoginAndRegisterView;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        BankApp.registerBank("한국은행", BigDecimal.valueOf(0.01), "101");
        BankApp.registerBank("KB국민은행", BigDecimal.valueOf(0.02), "018");
        BankApp.registerBank("하나은행", BigDecimal.valueOf(0.01), "301");
        BankApp.registerBank("NH농협은행", BigDecimal.valueOf(0.02), "016");
        BankApp.registerBank("우리은행", BigDecimal.valueOf(0.01), "020");
        BankApp.registerBank("신한은행", BigDecimal.valueOf(0.01), "110");
        BankApp.registerBank("카카오뱅크", BigDecimal.valueOf(0.02), "099");

        LoginAndRegisterView.printLogo();

        while (true) {
            LoginAndRegisterView.showBeginningUI();
        }
    }
}
