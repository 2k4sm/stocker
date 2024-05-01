package com.sm2k4.stocker.dtos.Transaction;

import com.sm2k4.stocker.models.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionUpdateDTO {
    private TransactionStatus status;
}
