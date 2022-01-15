package com.selfstudy.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebservicesApplication.class, args); 
	} 

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver(); 
		localeResolver.setDefaultLocale(Locale.US);   
		return localeResolver;   
	}

//	@Bean - fir belwo code just add one line in application.propeties -spring.messages.basename=messages
//	public ResourceBundleMessageSource messageSource() {
//
//		ResourceBundleMessageSource messageresource = new ResourceBundleMessageSource();
//
//		messageresource.setBasename("messages");
//
//		return messageresource;
//
//	}

}
