package com.chris.Banker_TransactionService.controller;

import com.chris.Banker_TransactionService.model.Transaction;
import com.chris.Banker_TransactionService.service.serviceImpl.TransactionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity makeTransaction(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.makeTransaction(transaction));
    }

    @GetMapping
    public ResponseEntity getTransactionsList(){
        return ResponseEntity.ok(transactionService.viewAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity viewTransaction(@PathVariable long id){
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

//    @DeleteMapping
//    public ResponseEntity deleteTransaction(@RequestParam long id){
//        return ResponseEntity.ok(transactionService.deleteTransaction(id));
//    }
}
