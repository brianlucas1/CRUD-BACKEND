package br.com.cliente.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cliente.controller.model.Cliente;
import br.com.cliente.controller.model.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado,Integer>{

	@Query("SELECT S FROM  ServicoPrestado s join s.cliente c"+
	"where c.nome upper( c.nome ) like upper( :nome ) and MONTH(s.data) =:mes ")
	List<ServicoPrestado> findByNomeAndMes(
			@Param("nome") String nome, @Param("mes") Integer mes);		
	
	
}
