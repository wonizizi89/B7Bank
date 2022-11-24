package program;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class History {
    private OffsetDateTime transactionDate;
    private String accountNumber;
    private TradeType type; // 0: 입금, 1: 출금, 3: 송금
    private BigDecimal amount; // 마찬가지로 int로 했지만 후에 Decimal 등 적절한 형태로 변환
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
