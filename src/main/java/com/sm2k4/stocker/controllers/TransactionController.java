package com.sm2k4.stocker.controllers;

import com.sm2k4.stocker.dtos.Transaction.TransactionRequestDTO;
import com.sm2k4.stocker.dtos.Transaction.TransactionResponseDTO;
import com.sm2k4.stocker.dtos.Transaction.TransactionUpdateDTO;
import com.sm2k4.stocker.models.Transaction;
import com.sm2k4.stocker.services.TransactionService;
import com.sm2k4.stocker.services.TransactionServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionServiceInterface transactionService;

    public TransactionController(TransactionServiceInterface transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public List<TransactionResponseDTO> getTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        List<TransactionResponseDTO> response = new ArrayList<>();

        for (Transaction transaction : transactions) {
            response.add(transaction.mapToTransactionResponse());
        }

        return response;
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return transaction.mapToTransactionResponse();
    }

    @GetMapping("/stocks")
    public List<TransactionResponseDTO> getTransactionByStock(@RequestParam("stock") Long  stockId) {
        List<Transaction> transactions = transactionService.getAllTransactionsByStockId(stockId);
        List<TransactionResponseDTO> response = new ArrayList<>();

        for (Transaction transaction : transactions) {
            response.add(transaction.mapToTransactionResponse());
        }
        return response;
    }

    @GetMapping("/traders")
    public List<TransactionResponseDTO> getTransactionByTrader(@RequestParam("trader") Long traderId) {
        List<Transaction> transaction = transactionService.getAllTransactionsByTraderId(traderId);
        List<TransactionResponseDTO> response = new ArrayList<>();

        for (Transaction transaction1 : transaction) {
            response.add(transaction1.mapToTransactionResponse());
        }
        return response;
    }

    @PostMapping("")
    public TransactionResponseDTO createTransaction(@RequestBody TransactionRequestDTO newTransaction) {
        Transaction transaction = transactionService.createTransaction(newTransaction);
        return transaction.mapToTransactionResponse();
    }

    @PutMapping("/{id}")
    public TransactionResponseDTO updateTransaction(@PathVariable Long id, @RequestBody TransactionUpdateDTO updateTransaction) {
        Transaction transaction = transactionService.updateTransaction(id, updateTransaction);
        return transaction.mapToTransactionResponse();
    }

    @DeleteMapping("/{id}")
    public TransactionResponseDTO deleteTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.deleteTransaction(id);
        return transaction.mapToTransactionResponse();
    }

}
