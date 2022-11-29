package bank.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;


public class Bank {
    private String bankName;
    private String bankCode;
    private List<Account> bankAccounts;
    private List<Customer> customers;
    private BigDecimal interestRate;


    public Bank(String bankName, BigDecimal interestRate, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.bankAccounts = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.interestRate = interestRate;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

}
