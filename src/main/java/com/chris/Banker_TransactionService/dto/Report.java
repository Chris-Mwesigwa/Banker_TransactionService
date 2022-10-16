package com.chris.Banker_TransactionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private String reportDescription;
    private Date generationDate;
}
