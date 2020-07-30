package com.mastercard.developer.service;

import com.mastercard.developer.exception.ServiceException;
import org.openapitools.client.model.EnrichedTransactionRequest;
import org.openapitools.client.model.EnrichedTransactionResponse;

public interface EnrichedTransactionService {
    EnrichedTransactionResponse searches(EnrichedTransactionRequest enrichedTransactionRequest) throws ServiceException;
}
