package com.sm2k4.stocker.dtos.Transaction;

import com.sm2k4.stocker.models.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {
    private Long stock;
    private Long trader;
    private Long qty;
    private TransactionStatus status;
    private Type type;
}
