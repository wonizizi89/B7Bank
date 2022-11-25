package program;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

public class History {
    private OffsetDateTime transactionDate;
    private String accountNumber;
    private TradeType type;
    private BigDecimal amount;
    private BigDecimal balance;
    private String traderName;

    public History(OffsetDateTime transactionDate, String accountNumber, TradeType type, BigDecimal amount, BigDecimal balance, String traderName) {
            this.transactionDate = transactionDate;
            this.accountNumber = accountNumber;
            this.type = type;
            this.amount = amount;
            this.balance = balance;
            this.traderName = traderName;
    }

    public String getTransactionDate() {
        return this.transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTransactionTime() {
        return this.transactionDate.format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TradeType getType() {
        return type;
    }

    public String getTypeByString() {
        String type = "오류";
        switch (this.type) {
            case DEPOSIT:
                type = "입금";
                break;
            case WITHDRAW:
                type = "출금";
                break;
            case TRANSFER:
                type = "송금";
                break;
            default:
                assert (false);
                break;
        }

        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getTraderName() {
        return traderName;
    }
}
