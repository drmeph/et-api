package com.mastercard.developer.executor;

import com.mastercard.developer.example.EnrichedTransactionExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.EnrichedTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.EnrichedTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnrichedTransactionExecutor {
    private EnrichedTransactionService enrichedTransactionService;

    @Autowired
    public EnrichedTransactionExecutor(EnrichedTransactionService enrichedTransactionService) {
        this.enrichedTransactionService = enrichedTransactionService;
    }

    public void execute() throws ServiceException {
        log.info("<<<---- ENRICHED TRANSACTION API EXECUTION STARTED ---->>>");

        searchEnrichedTransaction();

        log.info("<<<---- ENRICHED TRANSACTION API EXECUTION COMPLETED ---->>>");
    }

    private EnrichedTransactionResponse searchEnrichedTransaction() throws ServiceException {
        return enrichedTransactionService.searches(EnrichedTransactionExample.getFirstUseCaseEnrichedTransactionSearch());
    }
}
