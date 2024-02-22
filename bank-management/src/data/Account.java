package data;

public class Account {
    final Integer accountNumber;
    public String owner;
    public Integer balance;

    public Account(Integer accountNumber, String owner, Integer initialBalance){
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }
}
