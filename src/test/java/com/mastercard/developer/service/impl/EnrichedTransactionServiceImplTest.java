package com.mastercard.developer.service.impl;

import com.mastercard.developer.example.EnrichedTransactionExample;
import okhttp3.Call;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.EnrichedTransactionResponse;

import java.lang.reflect.Type;
import java.util.HashMap;

import static com.mastercard.developer.response.MockEnrichedTransactionResponse.getEnrichedTransactionResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrichedTransactionServiceImplTest {

    @InjectMocks
    EnrichedTransactionServiceImpl enrichedTransactionService;

    @Mock
    private ApiClient apiClient;

    @BeforeEach
    void setUp() throws ApiException {
        when(apiClient.buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(),
                any(), any())).thenReturn(mock(Call.class));
    }

    @Test
    void testSearch() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(200,
                new HashMap<>(), getEnrichedTransactionResponse()));

        EnrichedTransactionResponse enrichedTransactionResponse = enrichedTransactionService.searches(
                EnrichedTransactionExample.getFirstUseCaseEnrichedTransactionSearch()
        );

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(enrichedTransactionResponse),
                () -> assertNotNull(enrichedTransactionResponse.getSearchResults()),
                () -> assertEquals(1, enrichedTransactionResponse.getSearchResults().size())
        );
        enrichedTransactionResponse.getSearchResults().forEach(searchResult -> assertAll(() -> assertNotNull(searchResult),
                () -> assertNotNull(searchResult.getResultStatus()),
                () -> assertNotNull(searchResult.getResultStatus().getCode()),
                () -> assertNotNull(searchResult.getResultStatus().getMessage()),
                () -> assertNotNull(searchResult.getMerchantResult()),
                () -> assertNotNull(searchResult.getMerchantResult().getAddress()),
                () -> assertNotNull(searchResult.getMerchantResult().getAddress().getLine1()),
                () -> assertNotNull(searchResult.getMerchantResult().getAddress().getCity()),
                () -> assertNotNull(searchResult.getMerchantResult().getAddress().getCountryCode()),
                () -> assertNotNull(searchResult.getMerchantResult().getAddress().getCountrySubdivisionCode()),
                () -> assertNotNull(searchResult.getMerchantResult().getAddress().getPostalCode()),
                () -> assertNotNull(searchResult.getMerchantResult().getMerchantName()),
                () -> assertNotNull(searchResult.getMerchantResult().getPhone()),
                () -> assertNotNull(searchResult.getMerchantResult().getMerchantCategoryCode()),
                () -> assertNotNull(searchResult.getMerchantResult().getMerchantCategoryDescription())
        ));
    }
}