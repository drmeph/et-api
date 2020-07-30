package com.mastercard.developer.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.EnrichedTransactionRequest;
import org.openapitools.client.model.EnrichedTransactionSearchCriteria;
import org.openapitools.client.model.MerchantCriteria;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrichedTransactionExample {

    public static EnrichedTransactionRequest getFirstUseCaseEnrichedTransactionSearch() {
        return new EnrichedTransactionRequest().addSearchCriteriaItem(
                new EnrichedTransactionSearchCriteria().merchantCriteria(
                        new MerchantCriteria()
                                .cardAcceptorName("SHOE STORE 1234")
                                .cardAcceptorLocation("AUSTIN")
                                .cardAcceptorRegionCode("TX")
                )
        );
    }
}
