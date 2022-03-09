package one.digitalinnovation.labpadroesprojetospring.service;

import one.digitalinnovation.labpadroesprojetospring.model.Cliente;

public interface ClienteService {
	
	Iterable<Cliente> buscarTodos();
	
	Cliente buscarPorId(Long id);
	
	void inserirCliente(Cliente cliente);
	
	void atualizarCliente(Long id, Cliente cliente);
	
	void deletarCliente(Long id);
}
