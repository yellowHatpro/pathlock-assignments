import data.Account;
import data.Bank;
import transactions.BankTransactionSimulation;
import transactions.BankTransactionSimulationImpl;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        bankManagementSimulation();
    }

    public static Bank initialiseBank() {
        HashMap<Integer, Account> accountHashMap = new HashMap<>();
        Account account1 = new Account(1, "Ashutosh Aswal", 500);
        Account account2 = new Account(2, "Yashraj Mukhate", 10000);
        Account account3 = new Account(3, "Dheeraj Sanghi", 800000);
        Account account4 = new Account(4, "Dheeraj Mangi", 800000);
        accountHashMap.put(account1.getAccountNumber(), account1);
        accountHashMap.put(account2.getAccountNumber(), account2);
        accountHashMap.put(account3.getAccountNumber(), account3);
        accountHashMap.put(account4.getAccountNumber(), account4);
        return new Bank(1, "PEC Bank", accountHashMap);
    }

    public static void bankManagementSimulation() {
        Bank bank = initialiseBank();
        BankTransactionSimulationImpl bankTransactionSimulation = new BankTransactionSimulationImpl(bank);
        initialiseTransactions(bankTransactionSimulation);

    }

    public static void initialiseTransactions(BankTransactionSimulation bts) {
        System.out.println("Bank Balance: " + bts.getTotalBankBalance(bts.getBank()));

        bts.transferFund(1, bts.getBank(), 1, 2, 200);
        bts.transferFund(2, bts.getBank(), 1, 3, 500);
        bts.transferFund(3, bts.getBank(), 2, 3, 300);
        bts.transferFund(4, bts.getBank(), 3, 1, 200);

    }
}