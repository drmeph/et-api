package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.EnrichedTransactionService;
import com.mastercard.developer.util.GsonProvider;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.EnrichedTransactionApi;
import org.openapitools.client.model.EnrichedTransactionRequest;
import org.openapitools.client.model.EnrichedTransactionResponse;
import org.openapitools.client.model.EnrichedTransactionSearchResult;
import org.openapitools.client.model.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@Service
public class EnrichedTransactionServiceImpl implements EnrichedTransactionService {

    private EnrichedTransactionApi enrichedTransactionApi;

    @Autowired
    public EnrichedTransactionServiceImpl(ApiClient apiClient) {
        log.info("-->> INITIALIZING Enriched Transaction API");
        enrichedTransactionApi = new EnrichedTransactionApi(apiClient);
    }

    @Override
    public EnrichedTransactionResponse searches(EnrichedTransactionRequest enrichedTransactionRequest)
            throws ServiceException {
        try {
            log.info("<-- CALLING ENRICHED TRANSACTION SEARCHES ENDPOINT -->");
            EnrichedTransactionResponse enrichedTransactionResponse =
                    enrichedTransactionApi.search(enrichedTransactionRequest);
            assertNotNull(enrichedTransactionResponse, "Missing object 'enrichedTransactionResponse' when calling search");
            List<EnrichedTransactionSearchResult> searchResults = enrichedTransactionResponse.getSearchResults();
            assertNotNull(searchResults, "Missing Enriched Transaction search results items");
            log.info("<-- ENRICHED TRANSACTION FOUND SUCCESSFULLY -->");

            return enrichedTransactionResponse;
        } catch (ApiException e) {
            log.error("<<-- ACCOUNT SEARCH FAILED -->>");
            throw new ServiceException(e.getMessage(), deserializeErrors(e.getResponseBody()));
        }
    }

    private Errors deserializeErrors(String body) {
        return GsonProvider.gson().deserialize(body, Errors.class);
    }
}
