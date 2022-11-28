package bank.application;

import bank.db.BankDB;
import bank.entity.Account;
import bank.entity.Bank;
import bank.entity.Customer;

import java.util.HashMap;

public class AccountApp {
    private static HashMap<String, Account> accounts = new HashMap<>();

    public static Account registerAccount(String ownerId, int bankIndex) {
        Customer customer = CustomerApp.getCustomerOrNull(ownerId);
        Bank bank = BankDB.getBankByIndex(bankIndex);

        String bankNumber = createBankNumber(bank.getBankCode());

        if (accounts.containsKey(bankNumber)) {
            bankNumber = createBankNumber(bank.getBankCode());
        }

        Account newAccount = new Account(customer.getName(), ownerId, bankNumber, bank.getBankName(),
                bank.getInterestRate());
        accounts.put(bankNumber, newAccount);
        customer.addCustomerAccount(newAccount);
        return newAccount;
    }

    public static void unregisterAccount(String ownerName, int accountIndex) {
        Customer customer = CustomerApp.getCustomerOrNull(ownerName);
        Account targetAccount = customer.getAccount(accountIndex);
        customer.deleteCustomerAccount(targetAccount);
        accounts.remove(targetAccount);
    }

    public static Account getAccountByBankNumberOrNull(String bankNumber) {
        return accounts.get(bankNumber);
    }

    private static String createBankNumber(String bankCode) {
        StringBuilder bankNumBuilder = new StringBuilder();
        bankNumBuilder.append(bankCode);
        bankNumBuilder.append(String.format("%03d", (int)(Math.random() * 100)));
        bankNumBuilder.append(String.format("%06d", (int)(Math.random() * 100000)));
        return bankNumBuilder.toString();
    }

}
