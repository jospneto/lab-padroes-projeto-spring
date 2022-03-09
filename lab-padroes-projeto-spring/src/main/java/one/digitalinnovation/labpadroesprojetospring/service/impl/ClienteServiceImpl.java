package one.digitalinnovation.labpadroesprojetospring.service.impl;
import org.springframework.beans.factory.annotation.Autowired;

import one.digitalinnovation.labpadroesprojetospring.model.Cliente;
import one.digitalinnovation.labpadroesprojetospring.model.ClienteRepository;
import one.digitalinnovation.labpadroesprojetospring.model.Endereco;
import one.digitalinnovation.labpadroesprojetospring.model.EnderecoRepository;
import one.digitalinnovation.labpadroesprojetospring.service.ClienteService;
import one.digitalinnovation.labpadroesprojetospring.service.ViaCepService;

public class ClienteServiceImpl implements ClienteService{
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;

	@Override
	public Iterable<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// TODO Auto-generated method stub
		java.util.Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserirCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		salvarClienteComCep(cliente);
	}

	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void atualizarCliente(Long id, Cliente cliente) {
		// TODO Auto-generated method stub
		java.util.Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if(clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletarCliente(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

}
