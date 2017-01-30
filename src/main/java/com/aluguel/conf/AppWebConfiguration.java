package com.aluguel.conf;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/inquilino/form");
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);

		return messageSource;
	}
	
	// Configurando Email
	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername("wsm1986@gmail.com");
		mailSender.setPassword("*well1986*");
		mailSender.setPort(587);

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.host", "smtp.gmail.com");
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.port", "465");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.socketFactory.port", "465");
		mailProperties.put("mail.smtp.socketFactory.fallback", "false");
		mailProperties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}