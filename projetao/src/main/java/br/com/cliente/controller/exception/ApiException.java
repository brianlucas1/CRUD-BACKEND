package br.com.cliente.controller.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiException {

	@Getter
	private List<String> erros;
	
	public ApiException(List<String> erros){
		this.erros = erros;
	}
	
	public ApiException(String erros){
		this.erros = Arrays.asList(erros);
	}
	
}
