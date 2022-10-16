package com.chris.Banker_TransactionService.service;

import com.chris.Banker_TransactionService.model.Transaction;

import java.util.List;

public interface TransactionService {

    public Transaction makeTransaction(Transaction transaction);
    public Transaction getTransaction(long id);
    public List<Transaction> viewAllTransactions();
    public String deleteTransaction(long id);
    //public Long checkBalance(String accountNumber);
}
