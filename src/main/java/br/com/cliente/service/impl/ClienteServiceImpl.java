package br.com.cliente.service.impl;

import br.com.cliente.controller.dto.filtro.ClienteFiltro;
import br.com.cliente.entity.Cliente;
import br.com.cliente.repository.ClienteRepository;
import br.com.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) { //na classe dservice, implementamos os contratos da nossa interface
        return clienteRepository.save(cliente);
    }

    @Override
    public Page<Cliente> listaCliente(ClienteFiltro clienteFiltro, Pageable pageable) {

        Cliente cliente = Cliente.builder()
                .id(clienteFiltro.getId())
                .nome(clienteFiltro.getNome())
                .email(clienteFiltro.getEmail())
                .cpf(clienteFiltro.getCpf())
                .build();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(cliente, exampleMatcher);

        return clienteRepository.findAll(example, pageable);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void removerPorId(Long id) {
        clienteRepository.deleteById(id);
    }
}
