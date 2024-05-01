package com.sm2k4.stocker.services;

import com.sm2k4.stocker.dtos.Transaction.TransactionRequestDTO;
import com.sm2k4.stocker.dtos.Transaction.TransactionUpdateDTO;
import com.sm2k4.stocker.exceptions.GeneralExceptions.BadRequestException;
import com.sm2k4.stocker.exceptions.GeneralExceptions.NotFoundException;
import com.sm2k4.stocker.models.Stock;
import com.sm2k4.stocker.models.Trader;
import com.sm2k4.stocker.models.Transaction;
import com.sm2k4.stocker.models.TransactionStatus;
import com.sm2k4.stocker.repositories.StockRepository;
import com.sm2k4.stocker.repositories.TraderRepository;
import com.sm2k4.stocker.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService  implements TransactionServiceInterface {
    private final TransactionRepository transactionRepository;
    private final StockRepository stockRepository;
    private final TraderRepository traderRepository;

    public TransactionService(TransactionRepository transactionRepository, StockRepository stockRepository, TraderRepository traderRepository) {
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
        this.traderRepository = traderRepository;
    }

    public List<Transaction> getAllTransactions(){
        List<Transaction> transactions = this.transactionRepository.findAll();

        if (transactions.isEmpty()) {
            log.warn("No transactions found");
            throw new NotFoundException("No transactions found");
        }

        log.info("Fetched {} transactions", transactions.size());
        return transactions;
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            log.warn("No transaction found with id {}", id);
            throw new NotFoundException("No transaction found with id " + id);
        }

        log.info("Fetched transaction with id {}", id);
        return transaction.get();
    }

    public List<Transaction> getAllTransactionsByStockId(Long stockId) {
        Optional<List<Transaction>> transactions = this.transactionRepository.findAllByStockId(stockId);
        if (transactions.isEmpty()) {
            log.warn("No transactions found with stock id {}", stockId);
            throw new NotFoundException("No transaction found with stock id " + stockId);
        }

        log.info("Fetched transactions with stock id {}", stockId);
        return transactions.get();
    }

    public List<Transaction> getAllTransactionsByTraderId(Long transId){
        Optional<List<Transaction>> transactions = this.transactionRepository.findAllByTraderId(transId);
        if (transactions.isEmpty()) {
            log.warn("No transactions found with trader id {}", transId);
            throw new NotFoundException("No transaction found with transaction id " + transId);
        }

        log.info("Fetched transactions with trader id {}", transId);
        return transactions.get();
    }

    public Transaction createTransaction(TransactionRequestDTO newTransaction) {
        Optional<Stock> stock = this.stockRepository.findById(newTransaction.getStock());
        Optional<Trader> trader = this.traderRepository.findById(newTransaction.getTrader());

        if (stock.isEmpty()) {
            log.warn("No stock found with id {}", newTransaction.getStock());
            throw new NotFoundException("No stock found with id " + newTransaction.getStock());
        }

        if (trader.isEmpty()) {
            log.warn("No trader found with id {}", newTransaction.getTrader());
            throw new NotFoundException("No trader found with id " + newTransaction.getTrader());
        }

        Transaction transaction = new Transaction();
        transaction.setStock(stock.get());
        transaction.setTrader(trader.get());
        transaction.setQty(newTransaction.getQty());
        transaction.setType(newTransaction.getType());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setDate(new Date());

        log.info("Saving transaction {}", transaction);
        return this.transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, TransactionUpdateDTO updateTransaction) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            log.warn("No transaction found with id {}", id);
            throw new NotFoundException("No transaction found with id " + id);
        }

        Transaction transactionToUpdate = transaction.get();

        if (updateTransaction.getStatus() == null){
            log.warn("No transaction status found.");
            throw  new BadRequestException("Null transaction status");
        }

        transactionToUpdate.setStatus(updateTransaction.getStatus());

        log.info("Updating transaction with id {}", transactionToUpdate.getId());
        return this.transactionRepository.save(transactionToUpdate);
    }

    public Transaction deleteTransaction(Long id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            log.info("No transaction found with id {}", id);
            throw new NotFoundException("No transaction found with id " + id);
        }

        this.transactionRepository.delete(transaction.get());

        log.info("Deleted transaction with id {}", id);
        return transaction.get();
    }
}
