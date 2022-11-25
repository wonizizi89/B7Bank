package bank.application;

import bank.controller.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import bank.controller.ETradeType;
import bank.controller.History;
import bank.view.BankingView;

public class Main {
    public static void main(String[] args) {
        Account ac1 = new Account("뽀로로", "110-403-1", "신한은행");
        Account ac2 = new Account("크롱", "110-403-2", "신한은행");
        Account ac3 = new Account("루피", "110-403-3", "신한은행");

        // ETradeType type, BigDecimal amount, BigDecimal balance, String traderName
        ac1.addHistory(ETradeType.DEPOSIT, BigDecimal.valueOf(-10_000.50d), BigDecimal.valueOf(10_000d), "백승범");

        BankingView bv = new BankingView();
        bv.showHistoriesUI(ac1);
    }
}
