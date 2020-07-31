package com.mastercard.developer.response;

import org.openapitools.client.model.Address;
import org.openapitools.client.model.EnrichedTransactionResponse;
import org.openapitools.client.model.EnrichedTransactionSearchResult;
import org.openapitools.client.model.MerchantResult;
import org.openapitools.client.model.ResultStatus;

import java.util.List;

import static java.util.Collections.singletonList;

public class MockEnrichedTransactionResponse {
    public static final String CODE = "MERCHANT_FOUND";
    public static final String MESSAGE = "Merchant results provided.";

    public static List<EnrichedTransactionSearchResult> getSearchResults() {
        return singletonList(
                new EnrichedTransactionSearchResult()
                .resultStatus(
                        new ResultStatus()
                        .code(CODE)
                        .message(MESSAGE)
                )
                .merchantResult(
                        new MerchantResult()
                        .merchantName("The Shoe Store")
                        .address(
                                new Address()
                                .line1("100 Main St")
                                .city("Austin")
                                .countryCode("USA")
                                .countrySubdivisionCode("TX")
                                .postalCode("78701")
                        )
                        .phone("512-555-1111")
                        .merchantCategoryCode("5661")
                        .merchantCategoryDescription("Shoe Stores")
                )
        );
    }

    public static EnrichedTransactionResponse getEnrichedTransactionResponse() {
        return new EnrichedTransactionResponse()
                .searchResults(getSearchResults());
    }
}
