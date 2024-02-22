package transactions;

import data.Account;
import data.Bank;

public class Transaction implements Runnable{

    Integer transactionNumber;

    Integer fromAccountNumber;
    Integer toAccountNumber;
    Integer amount;

    Bank bank;

    public Transaction(Integer transactionNumber, Integer fromAccountNumber, Integer toAccountNumber, Integer amount, Bank bank) {
        this.transactionNumber = transactionNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.bank = bank;
    }

    @Override
    public void run() {
        Account fromAccount = bank.getAccounts().get(fromAccountNumber);
        Account toAccount = bank.getAccounts().get(toAccountNumber);
        System.out.println("Payment of amount "+amount+" starting in thread: "+Thread.currentThread().getName());
        System.out.println("Bank Balance of Sender" + fromAccount.owner +"("+fromAccountNumber+")" +" Before Transaction "+transactionNumber+": is "+ fromAccount.getBalance());
        System.out.println("Bank Balance of Receiver" + toAccount.owner +"("+toAccountNumber+")" +" Before Transaction "+transactionNumber+": is "+ toAccount.getBalance());
        bank.transferMoney(fromAccountNumber, toAccountNumber, amount);
        System.out.println("Payment of amount "+amount+" successfully made in thread: "+Thread.currentThread().getName());
        System.out.println("Bank Balance of Sender" + fromAccount.owner +"("+fromAccountNumber+")" +" After Transaction "+transactionNumber+": is "+ fromAccount.getBalance());
        System.out.println("Bank Balance of Receiver" + toAccount.owner +"("+toAccountNumber+")" +" After Transaction "+transactionNumber+": is "+ toAccount.getBalance());
    }
}
