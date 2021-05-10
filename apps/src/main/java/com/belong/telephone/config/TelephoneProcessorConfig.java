package com.belong.telephone.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.belong.telephone.service.impl.CustomerDataBuilderService;

/**
 * Generic Telephone Processor application specific config.
 * 
 * @author jyotikattikar
 *
 */
@Configuration
@PropertySource("classpath:application-local.properties")
public class TelephoneProcessorConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
	public CustomerDataBuilderService getCustomerDataBuilderService() throws FileNotFoundException, IOException {
		CustomerDataBuilderService customerDataBuilderService = new CustomerDataBuilderService();
		customerDataBuilderService.buildCustomerDetails();
		return customerDataBuilderService;
	}
}
