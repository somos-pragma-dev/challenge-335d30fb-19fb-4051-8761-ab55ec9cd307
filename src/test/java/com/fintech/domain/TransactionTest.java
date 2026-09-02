package com.fintech.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransaction() {
        Transaction transaction = new Transaction("12345", "WEB", new BigDecimal("100.00"));
        assertEquals("12345", transaction.getOperationNumber());
        assertEquals("WEB", transaction.getSourceChannel());
        assertEquals(new BigDecimal("100.00"), transaction.getAmount());
    }
}