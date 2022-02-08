package br.com.cliente.controller.Util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternacionalizacaoUtil {
	
	
	
	// O Objeto ReloadableResourceBundleMessageSource já aponta para o properties criado 
	//foi criado esse util para trocar as menssagens que são disparadas pelo hibernate
	// isso como saida das apis
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:message");
		messageSource.setDefaultEncoding("ISO-8859-1");
		messageSource.setDefaultLocale(Locale.getDefault());
		return messageSource;
	}
	
	//CARREGA AS INFORMAÇÕES ACIMA PARA SER VALIDADO NAS 
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean (){
		
		LocalValidatorFactoryBean bean  = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
