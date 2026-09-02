package com.fintech.api;

import com.fintech.domain.Transaction;
import com.fintech.domain.TransactionDto;
import com.fintech.infrastructure.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Operation(summary = "Crea una nueva transacción")
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = convertToEntity(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return new ResponseEntity<>(convertToDto(savedTransaction), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene todas las transacciones")
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return new ResponseEntity<>(transactions.stream().map(this::convertToDto).toList(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una transacción por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(value -> new ResponseEntity<>(convertToDto(value), HttpStatus.OK))
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualiza una transacción por ID")
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            Transaction updatedTransaction = convertToEntity(transactionDto);
            updatedTransaction.setId(id);
            return new ResponseEntity<>(convertToDto(transactionRepository.save(updatedTransaction)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Elimina una transacción por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Transaction convertToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setOperationNumber(transactionDto.getOperationNumber());
        transaction.setSourceChannel(transactionDto.getSourceChannel());
        transaction.setAmount(transactionDto.getAmount());
        return transaction;
    }

    private TransactionDto convertToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setOperationNumber(transaction.getOperationNumber());
        transactionDto.setSourceChannel(transaction.getSourceChannel());
        transactionDto.setAmount(transaction.getAmount());
        return transactionDto;
    }
}