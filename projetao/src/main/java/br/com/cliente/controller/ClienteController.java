package br.com.cliente.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cliente.controller.model.Cliente;
import br.com.cliente.controller.repository.ClienteRepository;


@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {
	
	private  ClienteRepository repository;

	@Autowired
	public ClienteController(ClienteRepository  repository){
		this.repository = repository;
	}
	
	//CRIA CLIENTE
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Cliente salvar(@RequestBody @Valid Cliente cliente){		
		return repository.save(cliente);		
	}
	
	//BUSCA CLIENTE
	@GetMapping("{id}")
	public  Cliente buscaPorId(@PathVariable Integer id){
		return repository
				//FUNÇÃO DO JPA PARA PROCURAR ALGUM VALOR NO BANCO COM OS CRITERIOS.
			   .findById(id)
			   .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
			   
	}
	@GetMapping
	public List<Cliente> obterTodos(){
		return repository.findAll();
		
	}
	
	//DELETA CLIENTE
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Integer id){
		repository
		//PROCURA POR UM ID
		   .findById(id)
		   //Caso ele encontre algum cliente eu consigo deletar o mesmo
		   .map(cliente ->{
			   	repository.delete(cliente);
			   	return Void.TYPE;
		   })
		   .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizaCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
		repository
		.findById(id)
		   //Caso ele encontre algum cliente eu consigo deletar o mesmo
		   .map(cliente ->{
			   clienteAtualizado.setId((cliente.getId()));
			   return repository.save(clienteAtualizado);
			   	
		   })
		   .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
		
		
	}
	
	
}
