package bank.application;


import bank.entity.Bank;
import bank.view.LoginAndRegisterView;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Main {
    static List<Bank> banks = new ArrayList<>();
    public static void main(String[] args) {
        LoginAndRegisterView.printLogo();
        Bank bank = new Bank("세븐뱅크", BigDecimal.valueOf(0.01), "018");
        bank.registerCustomer("pororo", "1234", "뽀로로");
        bank.registerCustomer("lupi", "1234", "루피");
        bank.registerCustomer("krong", "1234", "크롱");

        while (true) {
            LoginAndRegisterView.showBeginningUI(bank);
        }
    }
}
