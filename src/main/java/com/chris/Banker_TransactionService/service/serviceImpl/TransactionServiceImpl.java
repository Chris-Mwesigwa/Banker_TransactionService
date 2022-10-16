package com.chris.Banker_TransactionService.service.serviceImpl;

import com.chris.Banker_TransactionService.dto.Account;
import com.chris.Banker_TransactionService.dto.Report;
import com.chris.Banker_TransactionService.model.Transaction;
import com.chris.Banker_TransactionService.repository.TransactionRepository;
import com.chris.Banker_TransactionService.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WebClient webClient;
    private String baseUrl1 = "http://localhost:8080";
    private String baseUrl2 = "http://localhost:9000";

    public TransactionServiceImpl(TransactionRepository transactionRepository, WebClient webClient) {
        this.transactionRepository = transactionRepository;
        this.webClient = webClient;
    }

    @Override
    public Transaction makeTransaction(Transaction transaction) {

        //Transaction body creation by the object
        Report transactionReport = new Report("Successfully made a "
                +transaction.getTransactionType()
                + " of amount "+ transaction.getAmount()+ " on account "+transaction.getAccountNumber()
                ,null);

        if(transaction != null){
            addTransactionBalanceUpdate(transaction.getAccountNumber(), transaction.getAmount());

            transactionMadeReport(transactionReport);

        }

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransaction(long id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public List<Transaction> viewAllTransactions() {

        return transactionRepository.findAll();
    }

    @Override
    public String deleteTransaction(long id) {
        transactionRepository.deleteById(id);
        return "Transaction deleted successfully";
    }

//    @Override
//    public Long checkBalance(String accountNumber) {
//        return this.webClient.get().uri("/?= " +accountNumber)
//                .retrieve().bodyToMono(?);
//    }

    public Account addTransactionBalanceUpdate(String accNumber, long amt){

        String url = baseUrl1 +"/accounts/webT/?accountNumber=" +accNumber + "&amount="+amt;
        try {
            Account account = new Account(accNumber, amt, null);

            Account accRept = this.webClient.put()
                    .uri(url)
                    .retrieve().bodyToMono(Account.class).block();

            log.info("Created deposit of "+ amt);

            return accRept;
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return null;
    }


    public Report transactionMadeReport(Report report){

        String url = baseUrl2+ "/reports";
        report.setGenerationDate(new Date());
        try{
            Report reportCreated = this.webClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(report)
                    .retrieve()
                    .bodyToMono(Report.class)
                    .block();
        }catch (Exception ex){
            log.info(String.valueOf(ex));
        }
        return null;
    }

}
