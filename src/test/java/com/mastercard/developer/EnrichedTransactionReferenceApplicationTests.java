package com.mastercard.developer;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.executor.EnrichedTransactionExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrichedTransactionReferenceApplicationTests {

	@InjectMocks
	EnrichedTransactionReferenceApplication enrichedTransactionReferenceApplication;

	@Mock
	EnrichedTransactionExecutor enrichedTransactionExecutor;

	@Test
	void testRun() throws ServiceException {
		doNothing().when(enrichedTransactionExecutor).execute();

		enrichedTransactionReferenceApplication.run();

		verify(enrichedTransactionExecutor, atMostOnce()).execute();
	}

	@Test()
	void testRunException() throws ServiceException {
		String message = "Some error occurred";
		doThrow(new ServiceException(message)).when(enrichedTransactionExecutor).execute();

		assertDoesNotThrow(() -> enrichedTransactionReferenceApplication.run());
	}
}
