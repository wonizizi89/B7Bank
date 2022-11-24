package program;

public class Account {                                            //계좌생성에 필요한것 ? 이름,계좌번호,잔액,은행명
    private String ownerName;
    private String accountNumber;
    private int balance; // 일단은 int로 해놨으나 돈을 표현하는데 있어서 Decimal 형이 적절하므로 추후 수정
    private String bankName; // 필요?
    
    public Account(String ownerName, String accountNumber, String bankName) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = 0;
//        this.bankName = bankName;
    }
        private String getAccountNumber() {
            return accountNumber;
        }
//        private void setAccountNumber (String accountNumber){
//            this.accountNumber = accountNumber;
//        }
        private String getOwnerName() {
            return ownerName;
        }
//        protected void setOwnerName (String ownerName){
//            this.ownerName = ownerName;
//        }
        private int getBalance() {
            return balance;
        }
//        protected void setBalance (int balance){
//            this.balance = balance;
//        }
        private String getBankName() {
            return bankName;
        }
        public void editMemo(String newOwnerName, String newAccountNumber, String newBankName) {
            this.ownerName = newOwnerName;
            this.accountNumber = newAccountNumber;
            this.bankName = newBankName;
        }
        public int withdraw(int amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("잔액이 모자랍니다.");
        } else {
            this.balance -= amount;
            return amount;
        }
        }

//        protected void setBankName (String bankName){
//            this.bankName = bankName;
//        }


}
