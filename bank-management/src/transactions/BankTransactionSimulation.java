package transactions;

import data.Account;
import data.Bank;

import java.util.HashMap;

public interface BankTransactionSimulation {
    Bank createBankWithAccounts(Integer bankId, String name, HashMap<Integer, Account> accounts);
    void transferFund(Integer transactionNumber, Bank bank, Integer fromAccountId, Integer toAccountId, Integer amount);
    Integer getTotalBankBalance(Bank bank);
    Integer getBalanceOfAccountNumber(Integer accountNumber);
    Bank getBank();
}
