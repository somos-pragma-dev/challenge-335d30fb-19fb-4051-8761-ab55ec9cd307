package com.fintech.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operationNumber;

    @Column(nullable = false)
    private String sourceChannel;

    @Column(nullable = false)
    private BigDecimal amount;

    public Transaction() {}

    public Transaction(String operationNumber, String sourceChannel, BigDecimal amount) {
        this.operationNumber = operationNumber;
        this.sourceChannel = sourceChannel;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}