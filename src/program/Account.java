package program;

public class Account {
    private String ownerName;
    private String accountNumber;
    private int balance; // 일단은 int로 해놨으나 돈을 표현하는데 있어서 Decimal 형이 적절하므로 추후 수정
    private String bankName; // 필요?

    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public String getBankName() {
        return bankName;
    }

    public Account(String ownerName, String accountNumber, String bankName) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.bankName = bankName;
    }
}
