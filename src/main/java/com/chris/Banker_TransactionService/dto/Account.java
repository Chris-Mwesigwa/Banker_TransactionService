package com.chris.Banker_TransactionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    private String accountNumber;
    private long accountBalance;
    @Nullable
    private String accountHolderName;
}
