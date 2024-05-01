package com.sm2k4.stocker.models;

import com.sm2k4.stocker.dtos.Transaction.TransactionRequestDTO;
import com.sm2k4.stocker.dtos.Transaction.TransactionResponseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Stock stockId;
    @ManyToOne
    private Trader traderId;
    @Column(nullable = false)
    private Long qty;

    private TransactionStatus status;

    @Column(nullable = false)
    private Type type;
    @Column(nullable = false)
    private Date date;

    public TransactionResponseDTO mapToTransactionResponse() {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setId(id);
        transactionResponseDTO.setStockId(stockId);
        transactionResponseDTO.setTraderId(traderId);
        transactionResponseDTO.setQty(qty);
        transactionResponseDTO.setStatus(status);
        transactionResponseDTO.setType(type);
        transactionResponseDTO.setDate(date);
        return transactionResponseDTO;
    }
}
