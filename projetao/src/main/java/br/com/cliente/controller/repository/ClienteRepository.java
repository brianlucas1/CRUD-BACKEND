package br.com.cliente.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cliente.controller.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
	
	
}
