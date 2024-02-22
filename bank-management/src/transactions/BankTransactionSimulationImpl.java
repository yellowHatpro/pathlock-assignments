package transactions;

import data.Account;
import data.Bank;

import java.util.HashMap;
import java.util.Map;

public class BankTransactionSimulationImpl implements BankTransactionSimulation{

    Bank bank;

    public BankTransactionSimulationImpl(Bank bank){
        this.bank = bank;
    }

    @Override
    public Bank createBankWithAccounts(Integer bankId,
                                       String name,
                                       HashMap<Integer, Account> accounts) {
        Bank bank = new Bank(bankId, name);
        bank.setAccounts(accounts);
        return bank;
    }

    @Override
    public void transferFund(Integer transactionNumber, Bank bank, Integer fromAccountNumber, Integer toAccountNumber, Integer amount) {
        Transaction transaction = new Transaction(transactionNumber, fromAccountNumber, toAccountNumber, amount, bank);
        Thread newThread = new Thread(transaction, "Thread: transaction " + transactionNumber + " of " + amount);
        newThread.start();
    }

    @Override
    public Integer getTotalBankBalance(Bank bank) {
        HashMap<Integer, Account> accounts = bank.getAccounts();
        Integer totalBankBalance = 0;
        for(Map.Entry<Integer, Account> accountEntry: accounts.entrySet()){
            totalBankBalance+=accountEntry.getValue().balance;
        }
        return totalBankBalance;
    }

    @Override
    public Integer getBalanceOfAccountNumber(Integer accountNumber) {
        return bank.getAccounts().get(accountNumber).getBalance();
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }
}
