package br.com.cliente.controller.Util;

import java.lang.annotation.Target;
import java.math.BigDecimal;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.springframework.stereotype.Component;

@Component
public class BigDecimalConverter {
	
	
	public BigDecimal converter(String valor){
		
		if(valor != null){
			
			valor = valor.replace(".","").replace(",",".");
		}
		
		else{
			valor =null;
		}
		
		return new BigDecimal(valor);
	}

}
