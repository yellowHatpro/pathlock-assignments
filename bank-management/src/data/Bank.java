package data;

import java.util.HashMap;

public class Bank {
    public Integer bankId;
    public String name;
    public HashMap<Integer, Account> accounts;

    public Bank(Integer bankId, String name){
        this.bankId = bankId;
        this.name = name;
        this.accounts = new HashMap<>();
    }
    public Bank(Integer bankId, String name, HashMap<Integer, Account> accounts){
        this.bankId = bankId;
        this.name = name;
        this.accounts = accounts;
    }

    public void setAccounts(HashMap<Integer, Account> accounts) {
        this.accounts = accounts;
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }


    public synchronized void transferMoney(Integer fromAccountNumber, Integer toAccountNumber, Integer amount) {
        Account fromAccount = getAccounts().get(fromAccountNumber);
        Account toAccount = getAccounts().get(toAccountNumber);
        Integer fromBalance = fromAccount.getBalance();
        if (fromBalance-amount>0) {
            fromAccount.setBalance(fromBalance - amount);
            Integer toBalance = toAccount.getBalance();
            toAccount.setBalance(toBalance + amount);
        } else {
            System.out.println("Not sufficient balance");
        }
    }
}
