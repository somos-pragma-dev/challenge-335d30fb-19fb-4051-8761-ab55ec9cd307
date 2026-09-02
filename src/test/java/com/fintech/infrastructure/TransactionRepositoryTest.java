package com.fintech.infrastructure;

import com.fintech.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testFindById() {
        Transaction transaction = new Transaction("12345", "WEB", new BigDecimal("100.00"));
        entityManager.persist(transaction);
        entityManager.flush();

        Optional<Transaction> found = transactionRepository.findById(transaction.getId());
        assertTrue(found.isPresent());
        assertEquals("12345", found.get().getOperationNumber());
    }
}