package br.com.cliente.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cliente.controller.Util.BigDecimalConverter;
import br.com.cliente.controller.dto.ServicoPrestadoDTO;
import br.com.cliente.controller.model.Cliente;
import br.com.cliente.controller.model.ServicoPrestado;
import br.com.cliente.controller.repository.ClienteRepository;
import br.com.cliente.controller.repository.ServicoPrestadoRepository;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteRespository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter convertDecimal;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvaServico(@RequestBody ServicoPrestadoDTO dto){
		
		//PEGANDO A DATA ATUAL E JÁ FORMATANDO
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		//VERIFICA NO BANCO SE EXSITE O CLIENTE
		//CASO NAO EXISTA JOGA UMA EXECPTION
		 Cliente cliente = 
				 clienteRespository
				 .findById(idCliente)
				 .orElseThrow(() ->
				 	new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));
	
		ServicoPrestado servPrestado = new ServicoPrestado();
		
		servPrestado.setDescricao(dto.getDescricao());
		servPrestado.setData(data);
		servPrestado.setCliente(cliente);
		servPrestado.setValor(convertDecimal.converter(dto.getPreco()));
		
		return repository.save(servPrestado);
		
		
	}
	
	
	@GetMapping
	public List<ServicoPrestado> pesquisa(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			)
			{
		return repository.findByNomeAndMes("%" + nome +"%",  mes);
		
	}
	
}
	
	
	
