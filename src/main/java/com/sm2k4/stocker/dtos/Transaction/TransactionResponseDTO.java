package com.sm2k4.stocker.dtos.Transaction;

import com.sm2k4.stocker.models.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionResponseDTO {
    private Long id;
    private Stock stockId;
    private Trader traderId;
    private Long qty;
    private Type type;
    private TransactionStatus status;
    private Date date;


}
