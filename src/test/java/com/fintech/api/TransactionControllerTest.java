package com.fintech.api;

import com.fintech.domain.TransactionDto;
import com.fintech.infrastructure.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void createTransaction() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setOperationNumber("12345");
        transactionDto.setSourceChannel("WEB");
        transactionDto.setAmount(new BigDecimal("100.00"));

        Mockito.when(transactionRepository.save(any())).thenReturn(new com.fintech.domain.Transaction());

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"operationNumber\":\"12345\",\"sourceChannel\":\"WEB\",\"amount\":100.00}"))
               .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}