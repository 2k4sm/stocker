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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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
            throw new NotFoundException("No transactions found");
        }
        return transactions;
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new NotFoundException("No transaction found with id " + id);
        }

        return transaction.get();
    }

    public List<Transaction> getAllTransactionsByStockId(Long stockId) {
        Optional<List<Transaction>> transactions = this.transactionRepository.findAllByStockId(stockId);
        if (transactions.isEmpty()) {
            throw new NotFoundException("No transaction found with stock id " + stockId);
        }

        return transactions.get();
    }

    public List<Transaction> getAllTransactionsByTraderId(Long transId){
        Optional<List<Transaction>> transactions = this.transactionRepository.findAllByTraderId(transId);
        if (transactions.isEmpty()) {
            throw new NotFoundException("No transaction found with transaction id " + transId);
        }

        return transactions.get();
    }

    public Transaction createTransaction(TransactionRequestDTO newTransaction) {
        Optional<Stock> stock = this.stockRepository.findById(newTransaction.getStock());
        Optional<Trader> trader = this.traderRepository.findById(newTransaction.getTrader());

        if (stock.isEmpty()) {
            throw new NotFoundException("No stock found with id " + newTransaction.getStock());
        }

        if (trader.isEmpty()) {
            throw new NotFoundException("No trader found with id " + newTransaction.getTrader());
        }

        Transaction transaction = new Transaction();
        transaction.setStock(stock.get());
        transaction.setTrader(trader.get());
        transaction.setQty(newTransaction.getQty());
        transaction.setType(newTransaction.getType());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setDate(new Date());

        return this.transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, TransactionUpdateDTO updateTransaction) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new NotFoundException("No transaction found with id " + id);
        }

        Transaction transactionToUpdate = transaction.get();

        // TODO: handle unknown nulls..
        if (updateTransaction.getStatus() == null){
            throw  new BadRequestException("");
        }

        transactionToUpdate.setStatus(updateTransaction.getStatus());

        return this.transactionRepository.save(transactionToUpdate);
    }

    public Transaction deleteTransaction(Long id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new NotFoundException("No transaction found with id " + id);
        }

        this.transactionRepository.delete(transaction.get());

        return transaction.get();
    }
}
