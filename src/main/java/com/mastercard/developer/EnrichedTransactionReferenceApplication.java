package com.mastercard.developer;

import com.mastercard.developer.executor.EnrichedTransactionExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class EnrichedTransactionReferenceApplication implements CommandLineRunner {

	private EnrichedTransactionExecutor enrichedTransactionExecutor;

	@Autowired
	public EnrichedTransactionReferenceApplication(EnrichedTransactionExecutor enrichedTransactionExecutor) {
		this.enrichedTransactionExecutor = enrichedTransactionExecutor;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext =
				SpringApplication.run(EnrichedTransactionReferenceApplication.class, args);
		appContext.close();
	}

	@Override
	public void run(String... args) {
		try {
			enrichedTransactionExecutor.execute();
		} catch (Exception ex) {
			log.error("<-- APPLICATION ENDED WITH SOME ERROR --> {}", ex.getMessage());
		}
	}
}
