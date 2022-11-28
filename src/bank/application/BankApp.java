package bank.application;

import bank.db.BankDB;
import bank.entity.Bank;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class BankApp {
    private static HashMap<String, Bank> banks = new HashMap<>();

    public static void registerBank(String bankName, BigDecimal interestRate, String bankCode) {
        if (banks.containsKey(bankCode)) {
            return;
        }
        Bank bank = new Bank(bankName, interestRate, bankCode);
        banks.put(bankCode, bank);
        BankDB.addBank(bank);
    }
}
