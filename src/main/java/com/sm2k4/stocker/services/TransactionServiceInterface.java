package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Transaction.TransactionRequestDTO;
import com.sm2k4.stocker.dtos.Transaction.TransactionUpdateDTO;
import com.sm2k4.stocker.models.Transaction;

import java.util.List;

public interface TransactionServiceInterface {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    List<Transaction> getAllTransactionsByStockId(Long stockId);
    List<Transaction> getAllTransactionsByTraderId(Long transId);
    Transaction createTransaction(TransactionRequestDTO newTransaction);
    Transaction updateTransaction(Long id, TransactionUpdateDTO updateTransaction);
    Transaction deleteTransaction(Long id);
}
